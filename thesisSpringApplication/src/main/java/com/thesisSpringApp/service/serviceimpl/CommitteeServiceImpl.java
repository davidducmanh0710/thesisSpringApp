package com.thesisSpringApp.service.serviceimpl;

import com.thesisSpringApp.pojo.CommitteeUser;
import com.thesisSpringApp.pojo.ThesisCommitteeRate;
import com.thesisSpringApp.service.ThesisCommitteeRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.thesisSpringApp.pojo.Committee;
import com.thesisSpringApp.repository.CommitteeRepository;
import com.thesisSpringApp.service.CommitteeService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@PropertySource("classpath:config.properties")
public class CommitteeServiceImpl implements CommitteeService {
	@Autowired
	private CommitteeRepository committeeRepository;
	@Autowired
	private ThesisCommitteeRateService thesisCommitteeRateService;
	@Autowired
	private Environment env;

	@Override
	public void saveCommittee(Committee committee) {
		committeeRepository.saveCommittee(committee);
	}

	@Override
	public Committee getCommitteeById(int id) {
		return committeeRepository.getCommitteeById(id);

	}

	@Override
	public List<Committee> getAllCommittee(Map<String, String> params) {
		return committeeRepository.getAllCommittee(params);
	}

	@Override
	public List<Committee> getCommitteesForThesis() {
		Map<String, String> params = new HashMap<>();
		List<Committee> committees = committeeRepository.getAllCommittee(params);

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

	@Override
	public int totalCommitteePages() {
		Map<String, String> params = new HashMap<>();
		return (int) Math.ceil((double) committeeRepository.getAllCommittee(params).size() / Integer.parseInt(env.getProperty("committees.pageSize").toString()));
	}

}
