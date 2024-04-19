package com.thesisSpringApp.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thesisSpringApp.pojo.User;
import com.thesisSpringApp.service.UserService;

@RestController
@RequestMapping("/api/users/")
public class UserApiController {

	private UserService userService;

	@Autowired
	public UserApiController(UserService userService) {
		super();
		this.userService = userService;
	}

	@GetMapping("/all")
	@CrossOrigin
	public ResponseEntity<List<User>> getUsers() {
		return new ResponseEntity<>(
				this.userService.getAllUsers(),
				HttpStatus.OK);
	}

	@PostMapping("/changePassAndUploadAvatar")
	@CrossOrigin
	public ResponseEntity<User> changePassAndUploadAvatar() {
		return new ResponseEntity<>(
				HttpStatus.OK);
	}

}
