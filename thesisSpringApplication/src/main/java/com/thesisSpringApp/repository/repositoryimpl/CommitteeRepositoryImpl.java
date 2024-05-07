package com.thesisSpringApp.repository.repositoryimpl;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.thesisSpringApp.pojo.CommitteeUser;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.thesisSpringApp.pojo.Committee;
import com.thesisSpringApp.repository.CommitteeRepository;

import java.util.List;

@Repository
@Transactional
public class CommitteeRepositoryImpl implements CommitteeRepository {

	@Autowired
	private LocalSessionFactoryBean factory;

	@Override
	public void saveCommittee(Committee committee) {
		Session session = factory.getObject().getCurrentSession();
		if (committee.getId() != null && committee.getId() > 0)
			session.update(committee);
		else
			session.save(committee);
	}

	@Override
	public Committee getCommitteeById(int id) {
		Session session = factory.getObject().getCurrentSession();
		Query query = session.createNamedQuery("Committee.findById");
		query.setParameter("id", id);

		return (Committee) query.getSingleResult();
	}

	@Override
	public List<Committee> getAllCommittee() {
		Session session = factory.getObject().getCurrentSession();
		Query query = session.createNamedQuery("Committee.findAll");

		return (List<Committee>) query.getResultList();
	}

	@Override
	public List<CommitteeUser> getAllUsersOfCommittee(int committeeId) {
		Session session = factory.getObject().getCurrentSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<CommitteeUser> criteriaQuery = criteriaBuilder.createQuery(CommitteeUser.class);
		Root<CommitteeUser> root = criteriaQuery.from(CommitteeUser.class);

		criteriaQuery.select(root);
		criteriaQuery.where(criteriaBuilder.equal(root.get("committeeId"), committeeId));

		Query query = session.createQuery(criteriaQuery);

		return (List<CommitteeUser>) query.getResultList();
	}

	@Override
	public Committee getCommitteeByThesisId(int thesisId) {
		Session session = factory.getObject().getCurrentSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<Committee> criteriaQuery = criteriaBuilder.createQuery(Committee.class);
		Root<Committee> root = criteriaQuery.from(Committee.class);

		criteriaQuery.select(root);
//		criteriaQuery.where(criteriaBuilder.equal(root.get()))

		Query query = session.createQuery(criteriaQuery);
		return (Committee) query.getSingleResult();
	}

}
