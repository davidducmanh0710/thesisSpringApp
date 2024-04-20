package com.thesisSpringApp.repository;

import com.thesisSpringApp.pojo.Thesis;

public interface ThesisRepository {
	void saveThesis(Thesis thesis);

	Thesis getThesisById(int id);
}
