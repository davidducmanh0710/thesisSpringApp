package com.thesisSpringApp.repository;

import java.util.List;

import com.thesisSpringApp.pojo.User;

public interface UserRepository {
	List<User> getAllUsers();

	void saveUser(User user);

	User getUserById(int id);
}
