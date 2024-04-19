package com.thesisSpringApp.service.serviceimpl;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.thesisSpringApp.pojo.User;
import com.thesisSpringApp.repository.UserRepository;
import com.thesisSpringApp.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private Environment env;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public List<User> getAllUsers() {
		return userRepository.getAllUsers();
	}

	@Override
	public void saveUser(User user) {
		userRepository.saveUser(user);
	}

	public static String generateRandomString(int length) {
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		Random random = new Random();
		StringBuilder stringBuilder = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			int randomIndex = random.nextInt(characters.length());
			char randomChar = characters.charAt(randomIndex);
			stringBuilder.append(randomChar);
		}
		return stringBuilder.toString();
	}


	@Override
	public void saveInitUser(User user) {

		user.setAvatar(env.getProperty("user.avatar.default"));

		String userName = user.getRoleId().getName();
		userName = userName.startsWith("ROLE_") ? userName.substring(5) : userName;

		user.setPassword(passwordEncoder.encode((generateRandomString(7))));

		user.setActive(false);

		userName = "THESIS" + userName + user.getUseruniversityid();
		user.setUsername(userName);

		userRepository.saveUser(user);
	}


}
