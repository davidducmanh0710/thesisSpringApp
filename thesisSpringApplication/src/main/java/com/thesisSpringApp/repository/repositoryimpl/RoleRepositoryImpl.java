package com.thesisSpringApp.repository.repositoryimpl;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.thesisSpringApp.pojo.Role;
import com.thesisSpringApp.repository.RoleRepository;

@Repository
@Transactional
public class RoleRepositoryImpl implements RoleRepository {

	@Autowired
	private LocalSessionFactoryBean factoryBean;

	@Override
	public Role findRoleById(int id) {
		Session session = factoryBean.getObject().getCurrentSession();
		Query query = session.getNamedQuery("Role.findById");
		query.setParameter("id", id);
		return (Role) query.getSingleResult();
	}

	@Override
	public List<Role> findAllRoles() {
		Session session = factoryBean.getObject().getCurrentSession();
		Query query = session.getNamedQuery("Role.findAll");
		return query.getResultList();
	}

}
