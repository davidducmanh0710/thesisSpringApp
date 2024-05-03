package com.thesisSpringApp.service;

import com.thesisSpringApp.pojo.Committee;
import com.thesisSpringApp.pojo.CommitteeUser;

import java.util.List;

public interface CommitteeService {
	void saveCommittee(Committee committee);
	Committee getCommitteeById(int id);
	List<Committee> getAllCommittee();
	List<CommitteeUser> getAllUsersOfCommittee(int committeeId);
}
