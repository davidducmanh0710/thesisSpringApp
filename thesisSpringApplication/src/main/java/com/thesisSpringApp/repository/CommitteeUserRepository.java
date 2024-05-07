package com.thesisSpringApp.repository;

import java.util.List;

import com.thesisSpringApp.pojo.Committee;
import com.thesisSpringApp.pojo.CommitteeUser;

public interface CommitteeUserRepository {
	void saveCommitteeUser(CommitteeUser committeeUser);

	List<CommitteeUser> getCommitteeUserByCommittee(Committee committee);
}
