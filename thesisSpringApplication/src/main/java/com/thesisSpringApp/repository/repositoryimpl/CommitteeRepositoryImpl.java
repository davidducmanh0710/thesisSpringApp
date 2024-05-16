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
import java.util.stream.Collectors;

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
}
