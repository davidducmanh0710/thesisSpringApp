package com.thesisSpringApp.repository.repositoryimpl;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.thesisSpringApp.pojo.CommitteeUser;
import com.thesisSpringApp.pojo.Thesis;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.thesisSpringApp.pojo.Committee;
import com.thesisSpringApp.repository.CommitteeRepository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
@Transactional
@PropertySource("classpath:config.properties")
public class CommitteeRepositoryImpl implements CommitteeRepository {

	@Autowired
	private LocalSessionFactoryBean factory;
	@Autowired
	private Environment env;

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
	public List<Committee> getAllCommittee(Map<String, String> params) {
		Session session = factory.getObject().getCurrentSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<Committee> criteriaQuery = criteriaBuilder.createQuery(Committee.class);
		Root<Committee> root = criteriaQuery.from(Committee.class);

		criteriaQuery.select(root);
		criteriaQuery.orderBy(criteriaBuilder.desc(root.get("id")));

		Query query = session.createQuery(criteriaQuery);

		String page = params.get("page");
		if (page != null && !page.isEmpty()) {
			int pageSize = Integer.parseInt(env.getProperty("committees.pageSize").toString());
			int start = (Integer.parseInt(page) - 1)  * pageSize;
			query.setFirstResult(start);
			query.setMaxResults(pageSize);
		}

		return (List<Committee>) query.getResultList();
	}
}
