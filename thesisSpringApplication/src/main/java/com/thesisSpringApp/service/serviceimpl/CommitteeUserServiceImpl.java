package com.thesisSpringApp.service.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thesisSpringApp.pojo.Committee;
import com.thesisSpringApp.pojo.CommitteeUser;
import com.thesisSpringApp.repository.CommitteeUserRepository;
import com.thesisSpringApp.service.CommitteeUserService;

@Service
public class CommitteeUserServiceImpl implements CommitteeUserService {

	@Autowired
	private CommitteeUserRepository committeeUserRepository;

	@Override
	public void saveCommitteeUser(CommitteeUser committeeUser) {
		committeeUserRepository.saveCommitteeUser(committeeUser);
	}

	@Override
	public List<CommitteeUser> getCommitteeUserByCommittee(Committee committee) {
		return committeeUserRepository.getCommitteeUserByCommittee(committee);
	}

}
