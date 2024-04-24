package com.thesisSpringApp.service.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thesisSpringApp.pojo.Committee;
import com.thesisSpringApp.repository.CommitteeRepository;
import com.thesisSpringApp.service.CommitteeService;

@Service
public class CommitteeServiceImpl implements CommitteeService {

	@Autowired
	private CommitteeRepository committeeRepository;

	@Override
	public void saveCommittee(Committee committee) {
		committeeRepository.saveCommittee(committee);
	}

	@Override
	public Committee getCommitteeById(int id) {
		return committeeRepository.getCommitteeById(id);

	}

}
