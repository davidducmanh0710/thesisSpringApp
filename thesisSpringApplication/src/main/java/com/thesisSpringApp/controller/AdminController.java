package com.thesisSpringApp.controller;

import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.thesisSpringApp.service.FacultyService;
import com.thesisSpringApp.service.RoleService;
import com.thesisSpringApp.service.UserService;

@Controller
public class AdminController {

	private UserService userService;
	private RoleService roleService;
	private User user;
	private FacultyService facultyService;

	@Autowired
	public AdminController(UserService userService, RoleService roleService, User user,
			FacultyService facultyService) {
		super();
		this.userService = userService;
		this.roleService = roleService;
		this.user = user;
		this.facultyService = facultyService;
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
		List<Role> roles = roleService.getAllRoles();
		List<Faculty> faculties = facultyService.findAllFaculties();
		model.addAttribute("user", this.user);
		model.addAttribute("roles", roles);
		model.addAttribute("faculties", faculties);
		return "addUser";
	}


	@PostMapping(value = "/admin/add/user")
	public String adminAddUser(Model model, @ModelAttribute(value = "user") User user,
//			@RequestParam("birthdayName") Date birthday ,
			@RequestParam("roleIdName") int roleId, @RequestParam("facultyIdName") int facultyId)
			throws MessagingException {

//		user.setBirthday(birthday);

		Role role = roleService.getRoleById(roleId);
		user.setRoleId(role);
		Faculty faculty = facultyService.findFacultyById(facultyId);
		user.setFacultyId(faculty);

		userService.saveInitUserAndSendMail(user);

		return "redirect:/admin";
	}

}
