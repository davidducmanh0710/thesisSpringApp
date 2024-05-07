package com.thesisSpringApp.service.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thesisSpringApp.pojo.Thesis;
import com.thesisSpringApp.pojo.ThesisUser;
import com.thesisSpringApp.pojo.User;
import com.thesisSpringApp.repository.ThesisUserRepository;
import com.thesisSpringApp.service.ThesisUserService;

@Service
public class ThesisUserServiceImpl implements ThesisUserService {

	@Autowired
	private ThesisUserRepository thesisUserRepository;

	@Override
	public void addNewThesisUser(ThesisUser thesisUser, Thesis thesis, User user) {
		thesisUserRepository.saveThesisUser(thesisUser, thesis, user);
	}

	@Override
	public List<ThesisUser> getUserByThesis(Thesis thesis) {
		return thesisUserRepository.getUserByThesis(thesis);
	}

}
