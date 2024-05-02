package com.thesisSpringApp.repository.repositoryimpl;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.thesisSpringApp.pojo.CommitteeUser;
import com.thesisSpringApp.repository.CommitteeUserRepository;

@Repository
@Transactional
public class CommitteeUserRepositoryImpl implements CommitteeUserRepository {

	@Autowired
	private LocalSessionFactoryBean factoryBean;

	@Override
	public void saveCommitteeUser(CommitteeUser committeeUser) {
		Session session = factoryBean.getObject().getCurrentSession();
		if (committeeUser.getId() != null && committeeUser.getId() > 0)
			session.update(committeeUser);
		else
			session.save(committeeUser);
	}

}
