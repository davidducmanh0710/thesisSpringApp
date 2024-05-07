package com.thesisSpringApp.repository;

import java.util.List;

import com.thesisSpringApp.pojo.Role;
import com.thesisSpringApp.pojo.User;

public interface UserRepository {
	List<User> getAllUsers();

	void saveUser(User user);

	User getUserById(int id);

	List<User> getUserByRole(Role role);

	User getUserByUsername(String username);

	User getUserByEmail(String email);

	User getUserByUniversityId(String uId);

	List<User> getUsersByThesisId(int thesisId);
}
