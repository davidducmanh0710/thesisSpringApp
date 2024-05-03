package com.thesisSpringApp.service.serviceimpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thesisSpringApp.pojo.Thesis;
import com.thesisSpringApp.repository.ThesisRepository;
import com.thesisSpringApp.service.ThesisService;

@Service
public class ThesisServiceImpl implements ThesisService {

	@Autowired
	private ThesisRepository thesisRepository;

	@Override
	public void addNewThesis(Thesis thesis) {
		Date date = new Date();
		thesis.setCreateDate(date);
		thesis.setUpdateDate(date);
		thesis.setActive(false);

		thesisRepository.saveThesis(thesis);
	}

	@Override
	public List<Thesis> getAllThesis() {
		return this.thesisRepository.getAllThesis();
	}
}
