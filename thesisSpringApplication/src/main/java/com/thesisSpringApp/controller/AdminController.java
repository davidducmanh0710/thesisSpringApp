package com.thesisSpringApp.controller;

import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.thesisSpringApp.formatters.FormatterColumn;
import com.thesisSpringApp.pojo.Faculty;
import com.thesisSpringApp.pojo.Role;
import com.thesisSpringApp.pojo.User;
import com.thesisSpringApp.service.FacultyService;
import com.thesisSpringApp.service.RoleService;
import com.thesisSpringApp.service.StatsService;
import com.thesisSpringApp.service.ThesisService;
import com.thesisSpringApp.service.UserService;

@Controller
@ControllerAdvice
public class AdminController {

	private UserService userService;
	private RoleService roleService;
	private FacultyService facultyService;
	private ThesisService thesisService;
	private StatsService statsService;

	@Autowired
	public AdminController(UserService userService, RoleService roleService,
			FacultyService facultyService, ThesisService thesisService, StatsService statsService) {
		super();
		this.userService = userService;
		this.roleService = roleService;
		this.facultyService = facultyService;
		this.thesisService = thesisService;
		this.statsService = statsService;
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

	@GetMapping("/admin/stats")
	public String adminStatsView(Model model, @RequestParam Map<String, String> params) {
		String year = params.getOrDefault("year", "2024");

		if (year.isEmpty() || year == null)
			year = "2024";

		List<Object[]> theses = statsService.statsThesisByYear(Integer.parseInt(year));
		model.addAttribute("theses", theses);
		List<Object[]> theses2 = statsService
				.statsFrequencyJoinedThesisByYear(Integer.parseInt(year));
		model.addAttribute("theses2", theses2);

		return "stats";
	}

	@ModelAttribute
	public void commonAttr(Model model) {
		model.addAttribute("roles", roleService.getAllRoles());
		model.addAttribute("faculties", facultyService.findAllFaculties());
	}

	@GetMapping("/admin/addUser")
	public String adminAddUserView(Model model) {

		model.addAttribute("user", new User());

		return "addOrUpdateUser";
	}

	@GetMapping("/admin/updateUser/{userId}")
	public String adminUpdateUserView(@PathVariable("userId") int userId, Model model) {
		User user = userService.getUserById(userId);
		model.addAttribute("user", user);
		return "addOrUpdateUser";
	}

	@PostMapping(value = "/admin/add/user")
	public String adminAddUser(Model model, @ModelAttribute(value = "user") @Valid User user,
			BindingResult result)
			throws MessagingException {

		Role role = roleService.getRoleById(user.getRoleId().getId());
		Faculty faculty = facultyService.findFacultyById(user.getFacultyId().getId());


		if (!result.hasErrors() && user.getId() == null) {
			try {
				user.setFacultyId(faculty);
				user.setRoleId(role);
				userService.saveInitUserAndSendMail(user);

			} catch (Exception ex) {
				model.addAttribute("errMsg", ex.toString());
			}
		} else if (!result.hasErrors() && user.getId() > 0) {

			User updateUser = userService.getUserById(user.getId());
			updateUser.setFacultyId(faculty);
			updateUser.setRoleId(role);
			updateUser.setEmail(user.getEmail());
			updateUser.setUseruniversityid(user.getUseruniversityid());
			updateUser.setFirstName(user.getFirstName());
			updateUser.setLastName(user.getLastName());
			updateUser.setBirthday(user.getBirthday());
			updateUser.setGender(user.getGender());
			userService.saveUser(updateUser);
		}

		return "redirect:/admin";

	}

	@GetMapping("/admin/deleteUser/{userId}")
	public String deleteUser(@PathVariable("userId") int id) {
		User user = userService.getUserById(id);
		userService.deleteUser(user);
		return "redirect:/admin";
	}

}
