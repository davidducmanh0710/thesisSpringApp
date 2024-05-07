package com.thesisSpringApp.repository.repositoryimpl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.thesisSpringApp.pojo.CommitteeUser;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.thesisSpringApp.pojo.Role;
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
		if (user.getId() != null && user.getId() > 0)
			session.update(user);
		else
			session.save(user);
	}

	@Override
	public User getUserById(int id) {
		Session session = factory.getObject().getCurrentSession();
		Query query = session.getNamedQuery("User.findById");
		query.setParameter("id", id);
		return (User) query.getSingleResult();
	}

	@Override
	public List<User> getUserByRole(Role role) {
		Session session = factory.getObject().getCurrentSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
		Root rUser = criteriaQuery.from(User.class);

		criteriaQuery.select(rUser);

		List<Predicate> predicates = new ArrayList<>();

		if (role != null) {
			predicates.add(criteriaBuilder.equal(rUser.get("roleId"), role.getId()));
		}

		criteriaQuery.where(predicates.toArray(Predicate[]::new)); // nhung dieu kien where

		Query query = session.createQuery(criteriaQuery);

		return query.getResultList();

	}

	@Override
	public User getUserByUsername(String username) {
		Session session = factory.getObject().getCurrentSession();
		Query query = session.getNamedQuery("User.findByUsername");
		query.setParameter("username", username);
		return (User) query.getSingleResult();
	}

	@Override
	public User getUserByEmail(String email) {
		Session session = factory.getObject().getCurrentSession();
		Query query = session.getNamedQuery("User.findByEmail");
		query.setParameter("email", email);
		return (User) query.getSingleResult();
	}

	@Override
	public User getUserByUniversityId(String uId) {
		Session session = factory.getObject().getCurrentSession();
		Query query = session.getNamedQuery("User.findByUseruniversityid");
		query.setParameter("useruniversityid", uId);
		return (User) query.getSingleResult();
	}

	@Override
	public List<User> getUsersByThesisId(int thesisId) {
		Session session = factory.getObject().getCurrentSession();

		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
		Root<User> root = criteriaQuery.from(User.class);

		criteriaQuery.select(root);
		criteriaQuery.where(criteriaBuilder.equal(root.get("thesisId"), thesisId));

		Query query = session.createQuery(criteriaQuery);

		return (List<User>) query.getResultList();
	}

}
