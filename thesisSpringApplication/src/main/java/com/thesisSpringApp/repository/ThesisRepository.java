package com.thesisSpringApp.repository;

import com.thesisSpringApp.pojo.Thesis;

import java.util.List;

public interface ThesisRepository {
	void saveThesis(Thesis thesis);
	Thesis getThesisById(int id);
	List<Thesis> getAllThesis();
}
