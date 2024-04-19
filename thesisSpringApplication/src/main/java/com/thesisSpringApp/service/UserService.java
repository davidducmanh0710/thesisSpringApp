package com.thesisSpringApp.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

import com.thesisSpringApp.pojo.User;

public interface UserService extends UserDetails {
	List<User> getAllUsers();

	void saveUser(User user);

	void saveInitUser(User user);

	User getUserById(int id);

	void setCloudinaryField(User user);
}
