package com.thesisSpringApp.api;

import java.util.ArrayList;
import java.util.List;

import com.thesisSpringApp.Dto.*;
import com.thesisSpringApp.pojo.*;
import com.thesisSpringApp.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/theses")
public class ThesisApiController {

	private ThesisService thesisService;
	private ThesisUserService thesisUserService;
	private UserService userService;
	private CommitteeService committeeService;
	private ThesisStatusService thesisStatusService;
	private CommitteeUserService committeeUserService;
	private ThesisCommitteeRateService thesisCommitteeRateService;
	private RoleService roleService;
	private CriteriaService criteriaService;
	private ScoreService scoreService;

	@Autowired
	public ThesisApiController(ThesisService thesisService, ThesisUserService thesisUserService,
			UserService userService, CommitteeService committeeService,
			CommitteeUserService committeeUserService,
			ThesisStatusService thesisStatusService, ThesisCommitteeRateService thesisCommitteeRateService,
		    RoleService roleService, CriteriaService criteriaService, ScoreService scoreService) {
		super();
		this.thesisService = thesisService;
		this.thesisUserService = thesisUserService;
		this.userService = userService;
		this.committeeService = committeeService;
		this.thesisStatusService = thesisStatusService;
		this.committeeUserService = committeeUserService;
		this.thesisCommitteeRateService = thesisCommitteeRateService;
		this.roleService = roleService;
		this.criteriaService = criteriaService;
		this.scoreService = scoreService;
	}

	@PostMapping(path = "/")
	@CrossOrigin
	public ResponseEntity<Thesis> addNewThesis(@RequestBody ThesisDTO thesisDTO) {
		Thesis thesis = new Thesis();
		String name = thesisDTO.getName();
		thesis.setName(name);
		thesisService.addNewThesis(thesis);

		List<Integer> userIds = thesisDTO.getUserIds();

		for (int i = 0; i < userIds.size(); i++)
			thesisUserService.addNewThesisUser(new ThesisUser(), thesis,
					userService.getUserById(userIds.get(i)));

		return new ResponseEntity<>(thesis, HttpStatus.CREATED);
	}

	@GetMapping("/")
	@CrossOrigin
	public ResponseEntity<List<Thesis>> list() {
		List<Thesis> theses = this.thesisService.getAllThesis();

		return new ResponseEntity<>(theses, HttpStatus.OK);
	}

	public ThesisDetailDTO responseThesisDetail(Thesis thesis) {
		ThesisDetailDTO thesisDetailDTO = new ThesisDetailDTO();
		thesisDetailDTO.setThesis(thesis);

		List<ThesisUser> thesisUserList = thesisUserService.getUserByThesis(thesis);

		List<User> lecturers = new ArrayList<>();
		List<User> students = new ArrayList<>();

		for (ThesisUser thesisUser : thesisUserList) {
			if (thesisUser.getUserId().getRoleId().getName().equals("ROLE_GIANGVIEN"))
				lecturers.add(thesisUser.getUserId());
			else
				students.add(thesisUser.getUserId());
		}
		thesisDetailDTO.setLecturers(lecturers);
		thesisDetailDTO.setStudents(students);

		Committee committee = committeeService.getCommitteeOfThesis(thesis.getId());
		thesisDetailDTO.setCommittee(committee);

		return thesisDetailDTO;
	}

	@GetMapping(path = "/{thesisId}/", produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin
	public ResponseEntity<ThesisDetailDTO> retrieve(@PathVariable(value = "thesisId") int thesisId) {
		Thesis thesis = thesisService.getThesisById(thesisId);

		ThesisDetailDTO thesisDetailDTO = responseThesisDetail(thesis);

		return new ResponseEntity<>(thesisDetailDTO, HttpStatus.OK);
	}

	@PatchMapping(path = "/committee/", consumes = {
			MediaType.APPLICATION_JSON_VALUE })
	@CrossOrigin
	public ResponseEntity<ThesisDetailDTO> addCommittee(
			@RequestBody ThesisCommitteeDTO thesisCommitteeDTO) {

		Thesis thesis = thesisService.getThesisById(thesisCommitteeDTO.getThesisId());
		Committee committee = committeeService
				.getCommitteeById(thesisCommitteeDTO.getCommitteeId());

		ThesisStatus thesisStatus = thesisStatusService.getThesisStatusById(2);

		ThesisCommitteeRate thesisCommitteeRate = thesisCommitteeRateService
				.getThesisCommitteeRateByThesisId(thesis.getId());

		if (thesisCommitteeRate == null)
			thesisCommitteeRate = new ThesisCommitteeRate(committee, thesis, thesisStatus);
		else
			thesisCommitteeRate.setCommitteeId(committee);

		thesisCommitteeRateService.addCommitteeToThesis(thesisCommitteeRate);

		ThesisDetailDTO thesisDetailDTO = responseThesisDetail(thesis);

		return new ResponseEntity<>(thesisDetailDTO, HttpStatus.OK);
	}

	@PostMapping(path = "/scores/", consumes = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin
	@ResponseStatus(HttpStatus.CREATED)
	public void addScore(@RequestBody ScoreDTO scoreDTO) {
		Thesis thesis = thesisService.getThesisById(scoreDTO.getThesisId());
		CommitteeUser committeeUser = committeeUserService.getCommitteeUser(scoreDTO.getUserId(), scoreDTO.getCommitteeId());
		List<CriteriaDTO> criteriaDTOList = scoreDTO.getScores();

		for (CriteriaDTO criteriaDTO : criteriaDTOList) {
			Criteria criteria = criteriaService.getCriteriaById(criteriaDTO.getCriteriaId());
			Score score = scoreService.getScore(thesis.getId(), committeeUser.getId(), criteria.getId());

			if (score == null)
				score = new Score(thesis, committeeUser, criteria, criteriaDTO.getScore());
			else
				score.setScore(criteriaDTO.getScore());

			scoreService.saveAndUpdateScore(score);
		}
	}
}