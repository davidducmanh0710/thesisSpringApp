package com.thesisSpringApp.repository.repositoryimpl;

import javax.persistence.Query;

import com.thesisSpringApp.pojo.Committee;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.thesisSpringApp.pojo.Thesis;
import com.thesisSpringApp.repository.ThesisRepository;

import java.util.List;

@Repository
@Transactional
public class ThesisRepositoryImpl implements ThesisRepository {

	@Autowired
	private LocalSessionFactoryBean factory;

	@Override
	public void saveThesis(Thesis thesis) {
		Session session = factory.getObject().getCurrentSession();
		if (thesis.getId() != null && thesis.getId() > 0)
			session.update(thesis);
		else
			session.save(thesis);
	}

	@Override
	public Thesis getThesisById(int id) {
		Session session = factory.getObject().getCurrentSession();
		Query query = session.createNamedQuery("Thesis.findById");

		query.setParameter("id", id);

		return (Thesis) query.getSingleResult();
	}

	@Override
	public List<Thesis> getAllThesis() {
		Session session = factory.getObject().getCurrentSession();
		Query query = session.getNamedQuery("Thesis.findAll");
		return query.getResultList();
	}
}
