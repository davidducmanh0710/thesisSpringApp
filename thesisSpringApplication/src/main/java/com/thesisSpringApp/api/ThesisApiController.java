package com.thesisSpringApp.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thesisSpringApp.Dto.ThesisCommitteeDTO;
import com.thesisSpringApp.Dto.ThesisDTO;
import com.thesisSpringApp.Dto.ThesisDetailDTO;
import com.thesisSpringApp.pojo.Committee;
import com.thesisSpringApp.pojo.CommitteeUser;
import com.thesisSpringApp.pojo.Thesis;
import com.thesisSpringApp.pojo.ThesisStatus;
import com.thesisSpringApp.pojo.ThesisUser;
import com.thesisSpringApp.service.CommitteeService;
import com.thesisSpringApp.service.CommitteeUserService;
import com.thesisSpringApp.service.ThesisService;
import com.thesisSpringApp.service.ThesisStatusService;
import com.thesisSpringApp.service.ThesisUserService;
import com.thesisSpringApp.service.UserService;

@RestController
@RequestMapping("/api/theses")
public class ThesisApiController {

	private ThesisService thesisService;
	private ThesisUserService thesisUserService;
	private UserService userService;
	private CommitteeService committeeService;
	private ThesisStatusService thesisStatusService;
	private CommitteeUserService committeeUserService;

	@Autowired
	public ThesisApiController(ThesisService thesisService, ThesisUserService thesisUserService,
			UserService userService, CommitteeService committeeService,
			CommitteeUserService committeeUserService,
			ThesisStatusService thesisStatusService) {
		super();
		this.thesisService = thesisService;
		this.thesisUserService = thesisUserService;
		this.userService = userService;
		this.committeeService = committeeService;
		this.thesisStatusService = thesisStatusService;
		this.committeeUserService = committeeUserService;
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

	@GetMapping(path = "/{thesisId}", produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin
	public ResponseEntity<Thesis> retrieve(@PathVariable(value = "thesisId") int thesisId) {
		Thesis thesis = thesisService.getThesisById(thesisId);

		return new ResponseEntity<>(thesis, HttpStatus.OK);
	}

	@PostMapping(path = "/addCommittee", consumes = {
			MediaType.APPLICATION_JSON_VALUE })
	@CrossOrigin
	public ResponseEntity<ThesisDetailDTO> addCommittee(
			@RequestBody ThesisCommitteeDTO thesisCommitteeDTO) {

		
		Thesis thesis = thesisService.getThesisById(thesisCommitteeDTO.getThesisId());
		Committee committee = committeeService
				.getCommitteeById(thesisCommitteeDTO.getCommitteeId());

		ThesisStatus thesisStatus = thesisStatusService.getThesisStatusById(2);

//		ThesisCommitteeRate thesisCommitteeRate = new ThesisCommitteeRate(committee,
//				thesis, thesisStatus);

		ThesisDetailDTO thesisDetailDTO = new ThesisDetailDTO();
		thesisDetailDTO.setCommittee(committee);
		thesisDetailDTO.setThesis(thesis);
		thesisDetailDTO.setCommitteeUser(new ArrayList<>());
		thesisDetailDTO.setThesisUser(new ArrayList<>());

		List<CommitteeUser> committeeUsers = committeeUserService
				.getCommitteeUserByCommittee(committee);

		for (int i = 0; i < committeeUsers.size(); i++)
			thesisDetailDTO.getCommitteeUser().add(committeeUsers.get(i).getUserId());
		
		List<ThesisUser> thesisUsers = thesisUserService.getUserByThesis(thesis);

		for (int i = 0; i < thesisUsers.size(); i++)
			thesisDetailDTO.getThesisUser().add(thesisUsers.get(i).getUserId());

		return new ResponseEntity<ThesisDetailDTO>(thesisDetailDTO, HttpStatus.OK);
	}

}
