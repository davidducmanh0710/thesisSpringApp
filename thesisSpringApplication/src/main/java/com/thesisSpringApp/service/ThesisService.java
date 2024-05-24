package com.thesisSpringApp.service;

import java.util.List;

import com.thesisSpringApp.pojo.Thesis;

public interface ThesisService {
	void saveAndUpdateThesis(Thesis thesis);

	Thesis getThesisById(int id);

	List<Thesis> getAllThesis();


}
