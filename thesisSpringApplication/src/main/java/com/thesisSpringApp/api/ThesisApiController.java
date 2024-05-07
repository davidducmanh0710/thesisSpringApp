package com.thesisSpringApp.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.thesisSpringApp.Dto.ThesisDTO;
import com.thesisSpringApp.pojo.Thesis;
import com.thesisSpringApp.pojo.ThesisUser;
import com.thesisSpringApp.service.ThesisService;
import com.thesisSpringApp.service.ThesisUserService;
import com.thesisSpringApp.service.UserService;

@RestController
@RequestMapping("/api/theses")
public class ThesisApiController {

	private ThesisService thesisService;
	private ThesisUserService thesisUserService;
	private UserService userService;

	@Autowired
	public ThesisApiController(ThesisService thesisService, ThesisUserService thesisUserService,
			UserService userService) {
		super();
		this.thesisService = thesisService;
		this.thesisUserService = thesisUserService;
		this.userService = userService;
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

		return new ResponseEntity<>(thesis, HttpStatus.OK);
	}

	@GetMapping("/")
	@CrossOrigin
	public ResponseEntity<List<Thesis>> list() {
		List<Thesis> theses = this.thesisService.getAllThesis();

		return new ResponseEntity<>(theses, HttpStatus.OK);
	}

	@GetMapping(path = "/{thesisId}")
	@CrossOrigin
	public ResponseEntity<Thesis> retrieve(@PathVariable(value = "thesisId") int thesisId) {
		Thesis thesis = thesisService.getThesisById(thesisId);

		return new ResponseEntity<>(thesis, HttpStatus.OK);
	}
}
