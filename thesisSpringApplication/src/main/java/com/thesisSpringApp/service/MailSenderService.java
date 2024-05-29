package com.thesisSpringApp.service;

import javax.mail.MessagingException;

import com.thesisSpringApp.pojo.User;

public interface MailSenderService{

	void sendEmail(String from, User user) throws MessagingException;
	void sendOtp(String otp, User user) throws MessagingException;
}
