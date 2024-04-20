package com.thesisSpringApp.repository;

import java.util.List;

import com.thesisSpringApp.pojo.Role;

public interface RoleRepository {
	Role findRoleById(int id);

	Role findRoleByName(String name);

	List<Role> findAllRoles();
}
