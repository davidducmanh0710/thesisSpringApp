package com.thesisSpringApp.service;

import com.thesisSpringApp.pojo.Committee;

import java.util.List;
import java.util.Map;

public interface CommitteeService {
	void saveCommittee(Committee committee);

	Committee getCommitteeById(int id);

	List<Committee> getAllCommittee(Map<String, String> params);

	List<Committee> getCommitteesForThesis();

	Committee getCommitteeOfThesis(int thesisId);

	int totalCommitteePages();
}
