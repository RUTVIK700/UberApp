package com.rutvik.project.uber.uberApp.services;

public interface EmailService {
	public void sendEmail(String toEmail,String subject,String body);
	public void sendEmailMulti(String[] toEmail,String subject,String body);
}
