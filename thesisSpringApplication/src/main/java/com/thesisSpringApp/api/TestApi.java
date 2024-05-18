package com.thesisSpringApp.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestApi {

	@GetMapping("/")
	@CrossOrigin
	public ResponseEntity<List<String>> test() {
		List<String> l = new ArrayList<>();
		String s = "test";
		l.add(s);

		return new ResponseEntity<>(l, HttpStatus.OK);
	}
}
