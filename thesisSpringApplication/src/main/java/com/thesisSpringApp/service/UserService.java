package com.thesisSpringApp.service;

import java.util.List;

import javax.mail.MessagingException;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.thesisSpringApp.pojo.Role;
import com.thesisSpringApp.pojo.User;

public interface UserService extends UserDetailsService {
	List<User> getAllUsers();

	void saveUser(User user);

	void saveInitUserAndSendMail(User user) throws MessagingException;

	User getUserById(int id);

	void setCloudinaryField(User user);

	List<User> getUserByRoleName(Role role);
}
