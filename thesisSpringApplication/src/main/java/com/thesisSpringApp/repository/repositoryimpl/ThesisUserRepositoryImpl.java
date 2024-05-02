package com.thesisSpringApp.repository.repositoryimpl;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.thesisSpringApp.pojo.Thesis;
import com.thesisSpringApp.pojo.ThesisUser;
import com.thesisSpringApp.pojo.User;
import com.thesisSpringApp.repository.ThesisUserRepository;

@Repository
@Transactional
public class ThesisUserRepositoryImpl implements ThesisUserRepository {

	@Autowired
	private LocalSessionFactoryBean factory;

	@Override
	public void saveThesisUser(ThesisUser thesisUser, Thesis thesis, User user) {
		Session session = factory.getObject().getCurrentSession();
		thesisUser.setThesisId(thesis);
		thesisUser.setUserId(user);

		if (thesisUser.getId() != null && thesisUser.getId() > 0)
			session.update(thesisUser);
		else
			session.save(thesisUser);
	}

}
