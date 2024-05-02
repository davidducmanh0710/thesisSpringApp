package com.thesisSpringApp.repository;

import com.thesisSpringApp.pojo.Thesis;
import com.thesisSpringApp.pojo.ThesisUser;
import com.thesisSpringApp.pojo.User;

public interface ThesisUserRepository {
	void saveThesisUser(ThesisUser thesisUser, Thesis thesis, User user);
}
