package com.thesisSpringApp.formatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.thesisSpringApp.pojo.Role;
import com.thesisSpringApp.service.RoleService;
import com.thesisSpringApp.service.serviceimpl.FacultyService;

@Controller
public class FormatterColumn {

	private RoleService roleService;
	private FacultyService facultyService;

	@Autowired
	public FormatterColumn(RoleService roleService) {
		super();
		this.roleService = roleService;
	}

	public String roleParentIdFormatter(int id) {
		if (id > 0) {
			Role role = roleService.findRoleById(id);
			return role.getName();
		}
		return "None Parent";

	}

}
