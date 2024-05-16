package com.thesisSpringApp.service.serviceimpl;

import com.thesisSpringApp.pojo.CommitteeUser;
import com.thesisSpringApp.pojo.ThesisCommitteeRate;
import com.thesisSpringApp.service.ThesisCommitteeRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thesisSpringApp.pojo.Committee;
import com.thesisSpringApp.repository.CommitteeRepository;
import com.thesisSpringApp.service.CommitteeService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommitteeServiceImpl implements CommitteeService {
	@Autowired
	private CommitteeRepository committeeRepository;
	@Autowired
	private ThesisCommitteeRateService thesisCommitteeRateService;

	@Override
	public void saveCommittee(Committee committee) {
		committeeRepository.saveCommittee(committee);
	}

	@Override
	public Committee getCommitteeById(int id) {
		return committeeRepository.getCommitteeById(id);

	}

	@Override
	public List<Committee> getAllCommittee() {
		return committeeRepository.getAllCommittee();
	}

	@Override
	public List<Committee> getCommiteesForThesis() {
		List<Committee> committees = committeeRepository.getAllCommittee();

		committees = committees.stream().filter(committee -> {
			List<ThesisCommitteeRate> thesisCommitteeRates = thesisCommitteeRateService.getThesisCommitteeRatesByCommitteeId(committee.getId());

			if (thesisCommitteeRates.stream().filter(thesisCommitteeRate -> thesisCommitteeRate.getStatusId().getId().equals(2)).count() >= 5)
				return false;
			return true;
		}).collect(Collectors.toList());

		return committees;
	}

	@Override
	public Committee getCommitteeOfThesis(int thesisId) {
		ThesisCommitteeRate thesisCommitteeRate = thesisCommitteeRateService.getThesisCommitteeRateByThesisId(thesisId);

		Committee committee = null;
		if (thesisCommitteeRate != null) {
			committee = thesisCommitteeRate.getCommitteeId();
		}
		return committee;
	}

}
