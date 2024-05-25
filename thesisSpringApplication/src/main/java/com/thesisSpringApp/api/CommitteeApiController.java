package com.thesisSpringApp.api;

import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;

import com.thesisSpringApp.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.thesisSpringApp.Dto.CloseCommitteeDTO;
import com.thesisSpringApp.Dto.CommitteeDetailDTO;
import com.thesisSpringApp.Dto.CommitteeUserDetailDTO;
import com.thesisSpringApp.Dto.CommitteeUserDto;
import com.thesisSpringApp.Dto.NewCommitteeDto;
import com.thesisSpringApp.pojo.Committee;
import com.thesisSpringApp.pojo.CommitteeUser;
import com.thesisSpringApp.pojo.Score;
import com.thesisSpringApp.pojo.Thesis;
import com.thesisSpringApp.pojo.ThesisCommitteeRate;
import com.thesisSpringApp.pojo.ThesisStatus;
import com.thesisSpringApp.pojo.User;
import com.thesisSpringApp.repository.CommitteeUserRepository;

@RestController
@RequestMapping("/api/committees/")
public class CommitteeApiController {

	private CommitteeService committeeService;
	private UserService userService;
	private CommitteeUserService committeeUserService;
	private MailSenderService mailSenderService;
	private Environment env;
	private ThesisService thesisService;
	private ThesisCommitteeRateService thesisCommitteeRateService;
	private ScoreService scoreService;
	private ThesisStatusService thesisStatusService;
	private CriteriaService criteriaService;

	@Autowired
	public CommitteeApiController(CommitteeService committeeService, UserService userService,
		CommitteeUserService committeeUserService, MailSenderService mailSenderService,
  		Environment env, ThesisService thesisService, ThesisCommitteeRateService thesisCommitteeRateService,
  		ScoreService scoreService, ThesisStatusService thesisStatusService,
	  	CriteriaService criteriaService) {
		super();
		this.committeeService = committeeService;
		this.userService = userService;
		this.committeeUserService = committeeUserService;
		this.mailSenderService = mailSenderService;
		this.env = env;
		this.thesisService = thesisService;
		this.thesisCommitteeRateService = thesisCommitteeRateService;
		this.scoreService = scoreService;
		this.thesisStatusService = thesisStatusService;
		this.criteriaService = criteriaService;
	}

	@PostMapping(path = "/", consumes = {
			MediaType.APPLICATION_JSON_VALUE,
	})
	@CrossOrigin
	public ResponseEntity<Committee> addNewCommittee(@RequestBody NewCommitteeDto newCommitteeDto)
			throws MessagingException {

		if (newCommitteeDto.getCommitteeUserDtos().isEmpty() || newCommitteeDto.getCommitteeUserDtos().size() > 5) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		Committee committee = new Committee();
		String committeeName = newCommitteeDto.getName();
		committee.setName(committeeName);
		committee.setActive(true);
		committeeService.saveCommittee(committee);

		List<CommitteeUserDto> committeeUserDtos = newCommitteeDto.getCommitteeUserDtos();

		for (CommitteeUserDto c : committeeUserDtos) {
			CommitteeUser cmU = new CommitteeUser();
			cmU.setRole(c.getRoleName());

			User user = userService.getUserById(c.getUserId());

			if (c.getRoleName().equals("Phản biện"))
//				mailSenderService.sendEmail(env.getProperty("spring.mail.username"), user);
				System.out.println("Đã gửi mail");

			cmU.setUserId(user);
			cmU.setCommitteeId(committee);

			committeeUserService.saveCommitteeUser(cmU);

		}

		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	public List<CommitteeDetailDTO> responseCommitteeDetail() {
		List<Committee> committees = this.committeeService.getAllCommittee();

		List<CommitteeDetailDTO> committeeList = new ArrayList<>();

		for (Committee c : committees) {
			CommitteeDetailDTO committee = new CommitteeDetailDTO();
			committee.setId(c.getId());
			committee.setName(c.getName());
			committee.setActive(c.getActive());

			List<CommitteeUserDetailDTO> memberList = new ArrayList<>();

			for (CommitteeUser m: committeeUserService.getAllUsersOfCommittee(c.getId())) {
				CommitteeUserDetailDTO member = new CommitteeUserDetailDTO();
				member.setRole(m.getRole());
				member.setUser(m.getUserId());

				memberList.add(member);
			}
			committee.setMembers(memberList);
			committeeList.add(committee);
		}

		return committeeList;
	}

	@GetMapping("/")
	@CrossOrigin
	public ResponseEntity<List<CommitteeDetailDTO>> list() {
		List<CommitteeDetailDTO> committeeList = responseCommitteeDetail();

		return new ResponseEntity<>(committeeList, HttpStatus.OK);
	}

	@GetMapping("/active/")
	public ResponseEntity<List<Committee>> listCommitteeForThesis() {
		List<Committee> committees = committeeService.getCommiteesForThesis();

		return new ResponseEntity<>(committees, HttpStatus.OK);
	}

	@PatchMapping("/{committeeId}/close/")
	@CrossOrigin
	public ResponseEntity<List<CommitteeDetailDTO>> closeCommittee(
			@PathVariable(value = "committeeId") int committeeId) {

		if (committeeId < 1) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		Committee committee = committeeService.getCommitteeById(committeeId);
		committee.setActive(!committee.getActive());

		if (!committee.getActive()) {
			List<ThesisCommitteeRate> thesisCommitteeRateList = thesisCommitteeRateService.getThesisCommitteeRatesByCommitteeId(committee.getId());
			for (ThesisCommitteeRate t : thesisCommitteeRateList) {
				// Xem khóa luận đã được chấm chưa
				if (!t.getStatusId().getId().equals(3)) {
					Thesis thesis = t.getThesisId();
					List<Score> scores = scoreService.getScoresByThesisId(thesis.getId());
					int committeeUserCount = committeeUserService.getAllUsersOfCommittee(committee.getId()).size();
					int criteriaCount = criteriaService.getCriteriaList().size();

					// Tính điểm
					float score = 0;
					for (Score s : scores) {
						score += s.getScore();
					}
					score = score / (committeeUserCount * criteriaCount);

					// Lưu các thay đổi
					thesis.setScore(score);
					thesis.setActive(false);
					thesisService.saveAndUpdateThesis(thesis);

					ThesisStatus thesisStatus = thesisStatusService.getThesisStatusById(3);
					t.setStatusId(thesisStatus);
					thesisCommitteeRateService.saveAndUpdateThesisCommitteeRate(t);

					// Thông báo qua email cho sinh viên

				}
			}
		}

		committeeService.saveCommittee(committee);

		List<CommitteeDetailDTO> committeeList = responseCommitteeDetail();

		return new ResponseEntity<>(committeeList, HttpStatus.OK);
	}
}
