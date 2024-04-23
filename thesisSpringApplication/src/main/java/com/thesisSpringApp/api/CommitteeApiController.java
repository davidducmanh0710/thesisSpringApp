package com.thesisSpringApp.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thesisSpringApp.pojo.Committee;

@RestController
@RequestMapping("/api/committee/")
public class CommitteeApiController {

	@PostMapping("/add")
	public ResponseEntity<Committee> addNewCommittee() {
		return new ResponseEntity<Committee>(HttpStatus.OK);
	}
}
