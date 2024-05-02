package com.thesisSpringApp.service;

import com.thesisSpringApp.pojo.Thesis;
import com.thesisSpringApp.pojo.ThesisUser;
import com.thesisSpringApp.pojo.User;

public interface ThesisUserService {
	void addNewThesisUser(ThesisUser thesisUser, Thesis thesis, User user);
}
