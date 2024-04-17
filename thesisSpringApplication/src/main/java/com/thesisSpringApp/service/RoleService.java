package com.thesisSpringApp.service;

import java.util.List;

import com.thesisSpringApp.pojo.Role;

public interface RoleService {

	Role findRoleById(int id);

	List<Role> findAllRoles();
}
