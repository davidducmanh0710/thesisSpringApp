package com.thesisSpringApp.repository.repositoryimpl;

import javax.persistence.Query;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.thesisSpringApp.pojo.Committee;
import com.thesisSpringApp.repository.CommitteeRepository;

@Repository
@Transactional
public class CommitteeRepositoryImpl implements CommitteeRepository {

	@Autowired
	private LocalSessionFactoryBean factoryBean;

	@Override
	public void saveCommittee(Committee committee) {
		Session session = factoryBean.getObject().getCurrentSession();
		if (committee.getId() != null && committee.getId() > 0)
			session.update(committee);
		else
			session.save(committee);
	}

	@Override
	public Committee getCommitteeById(int id) {
		Session session = factoryBean.getObject().getCurrentSession();
		Query query = session.createNamedQuery("Committee.findById");
		query.setParameter("id", id);

		return (Committee) query.getSingleResult();
	}

}
