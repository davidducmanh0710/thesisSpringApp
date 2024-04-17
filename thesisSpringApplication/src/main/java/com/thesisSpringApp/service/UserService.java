package com.thesisSpringApp.service;

import java.util.List;

import com.thesisSpringApp.pojo.User;

public interface UserService {
	List<User> getAllUsers();

	void saveUser(User user);
}
