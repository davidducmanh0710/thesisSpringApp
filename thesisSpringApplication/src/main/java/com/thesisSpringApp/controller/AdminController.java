package com.thesisSpringApp.controller;

import java.util.List;
import java.util.Random;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.thesisSpringApp.formatter.FormatterColumn;
import com.thesisSpringApp.pojo.Faculty;
import com.thesisSpringApp.pojo.Role;
import com.thesisSpringApp.pojo.User;
import com.thesisSpringApp.service.MailSenderService;
import com.thesisSpringApp.service.RoleService;
import com.thesisSpringApp.service.UserService;
import com.thesisSpringApp.service.serviceimpl.FacultyService;

@Controller
public class AdminController {

	private UserService userService;
	private RoleService roleService;
	private User user;
	private FacultyService facultyService;
	private MailSenderService mailSenderService;
	private Environment env;

	@Autowired
	public AdminController(UserService userService, RoleService roleService, User user,
			FacultyService facultyService, MailSenderService mailSenderService, Environment env) {
		super();
		this.userService = userService;
		this.roleService = roleService;
		this.user = user;
		this.facultyService = facultyService;
		this.mailSenderService = mailSenderService;
		this.env = env;
	}

	@ModelAttribute("formatterColumn")
	public FormatterColumn formatterColumnFunct() {
		return new FormatterColumn(this.roleService); // nhớ inject vào mới xài đc
	}

	@GetMapping("/admin")
	public String adminIndexView(Model model) {
		List<User> users = userService.getAllUsers();
		model.addAttribute("users", users);
		return "admin";
	}

	@GetMapping("/admin/addUser")
	public String adminAddUserView(Model model) {
		List<Role> roles = roleService.findAllRoles();
		List<Faculty> faculties = facultyService.findAllFaculties();
		model.addAttribute("user", this.user);
		model.addAttribute("roles", roles);
		model.addAttribute("faculties", faculties);
		return "addUser";
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

	@PostMapping(value = "/admin/add/user")
	public String adminAddUser(Model model, @ModelAttribute(value = "user") User user,
			@RequestParam("roleIdName") int roleId, @RequestParam("facultyIdName") int facultyId)
			throws MessagingException {


		Role role = roleService.findRoleById(roleId);
		user.setRoleId(role);
		Faculty faculty = facultyService.findFacultyById(facultyId);
		user.setFacultyId(faculty);


		user.setAvatar(env.getProperty("user.avatar.default"));

		String userName = user.getRoleId().getName();
		userName = userName.startsWith("ROLE_") ? userName.substring(5) : userName;

		user.setUsername(userName);

		user.setPassword(generateRandomString(7));

		user.setActive(false);

		userService.saveUser(user); // save để lấy id


		userName = "THESIS" + userName + user.getId();
		user.setUsername(userName);

		userService.saveUser(user);

		mailSenderService.sendEmail(env.getProperty("spring.mail.username"), user);

		return "redirect:/admin";
	}

}
