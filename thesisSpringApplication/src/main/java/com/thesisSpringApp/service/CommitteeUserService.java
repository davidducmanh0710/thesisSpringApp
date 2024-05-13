package com.thesisSpringApp.service;

import java.util.List;

import com.thesisSpringApp.pojo.Committee;
import com.thesisSpringApp.pojo.CommitteeUser;

public interface CommitteeUserService {
	void saveCommitteeUser(CommitteeUser committeeUser);

	List<CommitteeUser> getCommitteeUserByCommittee(Committee committee);

}
