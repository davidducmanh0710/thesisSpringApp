package com.thesisSpringApp.repository.repositoryimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.thesisSpringApp.pojo.Thesis;
import com.thesisSpringApp.repository.ThesisRepository;

@Repository
@Transactional
@PropertySource("classpath:config.properties")
public class ThesisRepositoryImpl implements ThesisRepository {

	@Autowired
	private LocalSessionFactoryBean factory;
	@Autowired
	private Environment env;

	@Override
	public void saveAndUpdateThesis(Thesis thesis) {
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
	public List<Thesis> getAllThesis(Map<String, String> params) {
		Session session = factory.getObject().getCurrentSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<Thesis> criteriaQuery = criteriaBuilder.createQuery(Thesis.class);
		Root<Thesis> root = criteriaQuery.from(Thesis.class);

		criteriaQuery.select(root);
		criteriaQuery.orderBy(criteriaBuilder.desc(root.get("updateDate")));

		Query query = session.createQuery(criteriaQuery);

		String page = params.get("page");
		if (page != null && !page.isEmpty()) {
			int pageSize = Integer.parseInt(env.getProperty("theses.pageSize").toString());
			int start = (Integer.parseInt(page) - 1)  * pageSize;
			query.setFirstResult(start);
			query.setMaxResults(pageSize);
		}
		return (List<Thesis>) query.getResultList();
	}


}
