package com.thesisSpringApp.repository.repositoryimpl;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.thesisSpringApp.pojo.User;
import com.thesisSpringApp.repository.UserRepository;

@Repository
@Transactional
public class UserRepositoryImpl implements UserRepository {

	@Autowired
	private LocalSessionFactoryBean factory;

	@Override
	public List<User> getAllUsers() {
		Session session = factory.getObject().getCurrentSession();
		Query query = session.getNamedQuery("User.findAll");
		return query.getResultList();
	}

	@Override
	public void saveUser(User user) {
		Session session = factory.getObject().getCurrentSession();
		if (user.getId() != null)
			session.update(user);
		else
			session.save(user);
	}

}
