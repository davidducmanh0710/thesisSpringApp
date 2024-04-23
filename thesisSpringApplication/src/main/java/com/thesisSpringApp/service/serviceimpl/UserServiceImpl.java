package com.thesisSpringApp.service.serviceimpl;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.thesisSpringApp.pojo.Role;
import com.thesisSpringApp.pojo.User;
import com.thesisSpringApp.repository.UserRepository;
import com.thesisSpringApp.service.MailSenderService;
import com.thesisSpringApp.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private static final long serialVersionUID = 1L;
	private UserRepository userRepository;
	private Environment env;
	private PasswordEncoder passwordEncoder;
	private Cloudinary cloudinary;
	private MailSenderService mailSenderService;

	@Autowired
	public UserServiceImpl(UserRepository userRepository, Environment env,
			PasswordEncoder passwordEncoder, Cloudinary cloudinary,
			MailSenderService mailSenderService) {
		super();
		this.userRepository = userRepository;
		this.env = env;
		this.passwordEncoder = passwordEncoder;
		this.cloudinary = cloudinary;
		this.mailSenderService = mailSenderService;
	}

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
	public void saveInitUserAndSendMail(User user) throws MessagingException {

		user.setAvatar(env.getProperty("user.avatar.default"));

		String userName = user.getRoleId().getName();
		userName = userName.startsWith("ROLE_") ? userName.substring(5) : userName;
		userName = "THESIS" + userName + user.getUseruniversityid();
		user.setUsername(userName);

		String password = generateRandomString(7);
		user.setPassword(password);

		user.setActive(true);

		mailSenderService.sendEmail(env.getProperty("spring.mail.username"), user);
		
		user.setPassword(passwordEncoder.encode(password));

		userRepository.saveUser(user);
	}

	@Override
	public User getUserById(int id) {

		return userRepository.getUserById(id);
	}

	@Override
	public void setCloudinaryField(User user) {
		if (!user.getFile().isEmpty()) {
			try {
				Map res = this.cloudinary.uploader().upload(user.getFile().getBytes(),
						ObjectUtils.asMap("resource_type", "auto"));
				user.setAvatar(res.get("secure_url").toString());
			} catch (IOException ex) {
				Logger.getLogger(UserServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
			}
			userRepository.saveUser(user);
		}
	}

	@Override
	public List<User> getUserByRoleName(Role role) {

		return userRepository.getUserByRole(role);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}


}
