package com.thesisSpringApp.service;

import com.thesisSpringApp.pojo.Thesis;

import java.util.List;

public interface ThesisService {
	void addNewThesis(Thesis thesis);
	List<Thesis> getAllThesis();
}
