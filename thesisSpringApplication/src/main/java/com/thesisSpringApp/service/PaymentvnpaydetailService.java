package com.thesisSpringApp.service;

import com.thesisSpringApp.pojo.Paymentvnpaydetail;

public interface PaymentvnpaydetailService {
	void saveVnPay(Paymentvnpaydetail paymentvnpaydetail);

	String extractLastWord(String s);
}
