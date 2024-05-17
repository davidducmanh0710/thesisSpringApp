package com.thesisSpringApp.service;

import com.thesisSpringApp.pojo.Thesis;

import java.util.List;

public interface ThesisService {
	void saveAndUpdateThesis(Thesis thesis);

	Thesis getThesisById(int id);

	List<Thesis> getAllThesis();
}
