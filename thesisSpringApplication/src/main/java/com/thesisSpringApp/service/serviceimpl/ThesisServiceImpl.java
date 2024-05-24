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
	public void saveAndUpdateThesis(Thesis thesis) {

        Date date = new Date();
        if (thesis.getId() != null && thesis.getId() > 0) {
            thesis.setUpdateDate(date);
		} else {
            thesis.setCreateDate(date);
			thesis.setUpdateDate(date);
			thesis.setActive(true);
		}

		thesisRepository.saveAndUpdateThesis(thesis);
	}

	@Override
	public Thesis getThesisById(int id) {
		return this.thesisRepository.getThesisById(id);
	}

	@Override
	public List<Thesis> getAllThesis() {
		return this.thesisRepository.getAllThesis();
	}


}
