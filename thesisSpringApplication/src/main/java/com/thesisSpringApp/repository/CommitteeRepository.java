package com.thesisSpringApp.repository;

import com.thesisSpringApp.pojo.Committee;

public interface CommitteeRepository {
	void saveCommittee(Committee committee);

	Committee getCommitteeById(int id);
}
