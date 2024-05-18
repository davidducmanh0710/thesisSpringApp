package com.thesisSpringApp.service.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thesisSpringApp.pojo.Paymentvnpaydetail;
import com.thesisSpringApp.repository.PaymentvnpaydetailRepository;
import com.thesisSpringApp.service.PaymentvnpaydetailService;

@Service
public class PaymentvnpaydetailServiceImpl implements PaymentvnpaydetailService {

	@Autowired
	private PaymentvnpaydetailRepository paymentvnpaydetailRepository;

	@Override
	public void saveVnPay(Paymentvnpaydetail paymentvnpaydetail) {
		paymentvnpaydetailRepository.saveVnPay(paymentvnpaydetail);
	}

	@Override
	public String extractLastWord(String input) {
		String[] words = input.trim().split("\\s+");
		if (words.length > 0) {
			return words[words.length - 1];
		} else {
			return "";
		}
	}

}
