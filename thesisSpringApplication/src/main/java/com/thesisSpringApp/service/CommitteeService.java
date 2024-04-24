package com.thesisSpringApp.service;

import com.thesisSpringApp.pojo.Committee;

public interface CommitteeService {
	void saveCommittee(Committee committee);

	Committee getCommitteeById(int id);
}
