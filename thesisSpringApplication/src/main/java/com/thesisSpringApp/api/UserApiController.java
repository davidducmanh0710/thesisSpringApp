package com.thesisSpringApp.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.thesisSpringApp.pojo.User;
import com.thesisSpringApp.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserApiController {

	private UserService userService;
	private PasswordEncoder passwordEncoder;

	@Autowired
	public UserApiController(UserService userService, PasswordEncoder passwordEncoder) {
		super();
		this.userService = userService;
		this.passwordEncoder = passwordEncoder;
	}

	@PostMapping(path = "/users", consumes = {
			MediaType.APPLICATION_JSON_VALUE,
			MediaType.MULTIPART_FORM_DATA_VALUE
	})

	@GetMapping("/all")
	@CrossOrigin
	public ResponseEntity<List<User>> getUsers() {
		return new ResponseEntity<>(
				this.userService.getAllUsers(),
				HttpStatus.OK);
	}

	@PostMapping("/{userId}/setInitAcc")
	@CrossOrigin
	public ResponseEntity<User> changePassAndUploadAvatar(
			@PathVariable int userId,
			@RequestParam("password") String password,
			@RequestPart("avatar") MultipartFile file) {
		User user = userService.getUserById(userId);

		user.setPassword(passwordEncoder.encode(password));
		user.setFile(file);
		userService.saveUser(user);
		userService.setCloudinaryField(user);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}


}
