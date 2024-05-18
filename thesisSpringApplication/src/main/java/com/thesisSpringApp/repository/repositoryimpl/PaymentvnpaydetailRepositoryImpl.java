package com.thesisSpringApp.repository.repositoryimpl;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.thesisSpringApp.pojo.Paymentvnpaydetail;
import com.thesisSpringApp.repository.PaymentvnpaydetailRepository;

@Repository
@Transactional
public class PaymentvnpaydetailRepositoryImpl implements PaymentvnpaydetailRepository {

	@Autowired
	private LocalSessionFactoryBean factoryBean;

	@Override
	public void saveVnPay(Paymentvnpaydetail paymentvnpaydetail) {
		Session session = factoryBean.getObject().getCurrentSession();

		if (paymentvnpaydetail.getId() != null && paymentvnpaydetail.getId() > 0)
			session.update(paymentvnpaydetail);
		else
			session.save(paymentvnpaydetail);
	}

}
