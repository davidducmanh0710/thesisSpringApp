package com.thesisSpringApp.service.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thesisSpringApp.pojo.Role;
import com.thesisSpringApp.repository.RoleRepository;
import com.thesisSpringApp.service.RoleService;

@Service
public class RoleServiceimpl implements RoleService {

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public Role findRoleById(int id) {
		return roleRepository.findRoleById(id);
	}

	@Override
	public List<Role> findAllRoles() {
		return roleRepository.findAllRoles();
	}

}
