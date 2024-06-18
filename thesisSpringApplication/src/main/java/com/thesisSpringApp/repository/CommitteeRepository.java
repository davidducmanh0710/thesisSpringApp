package com.thesisSpringApp.repository;

import com.thesisSpringApp.pojo.Committee;
import com.thesisSpringApp.pojo.CommitteeUser;

import java.util.List;
import java.util.Map;

public interface CommitteeRepository {
	void saveCommittee(Committee committee);

	Committee getCommitteeById(int id);

	List<Committee> getAllCommittee(Map<String, String> params);

}
