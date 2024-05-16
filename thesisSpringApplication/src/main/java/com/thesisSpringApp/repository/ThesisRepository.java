package com.thesisSpringApp.repository;

import java.util.List;

import com.thesisSpringApp.pojo.Committee;
import com.thesisSpringApp.pojo.Thesis;

public interface ThesisRepository {
	void saveThesis(Thesis thesis);

	Thesis getThesisById(int id);

	List<Thesis> getAllThesis();
}
