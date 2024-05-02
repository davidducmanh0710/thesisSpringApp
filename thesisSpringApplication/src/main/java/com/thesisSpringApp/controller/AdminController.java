package com.thesisSpringApp.controller;

import java.util.List;

import javax.mail.MessagingException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.thesisSpringApp.formatters.FormatterColumn;
import com.thesisSpringApp.pojo.Faculty;
import com.thesisSpringApp.pojo.Role;
import com.thesisSpringApp.pojo.User;
import com.thesisSpringApp.service.FacultyService;
import com.thesisSpringApp.service.RoleService;
import com.thesisSpringApp.service.UserService;

@Controller
@ControllerAdvice
public class AdminController {

	private UserService userService;
	private RoleService roleService;
	private FacultyService facultyService;

	@Autowired
	public AdminController(UserService userService, RoleService roleService,
			FacultyService facultyService) {
		super();
		this.userService = userService;
		this.roleService = roleService;
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

	@ModelAttribute
	public void commonAttr(Model model) {
		model.addAttribute("roles", roleService.getAllRoles());
		model.addAttribute("faculties", facultyService.findAllFaculties());
	}

	@GetMapping("/admin/addUser")
	public String adminAddUserView(Model model) {

		model.addAttribute("user", new User());

		return "addUser";
	}

	@PostMapping(value = "/admin/add/user")
	public String adminAddUser(Model model, @ModelAttribute(value = "user") @Valid User user,
			BindingResult result)
			throws MessagingException {

		if (!result.hasErrors()) {
			try {

				Role role = roleService.getRoleById(user.getRoleId().getId());
				Faculty faculty = facultyService.findFacultyById(user.getFacultyId().getId());
				user.setFacultyId(faculty);
				user.setRoleId(role);
				userService.saveInitUserAndSendMail(user);

				return "redirect:/admin";

			} catch (Exception ex) {
				model.addAttribute("errMsg", ex.toString());
			}

		}
		return "addUser";

	}

}
