package com.rutvik.project.uber.uberApp.services.impl;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.rutvik.project.uber.uberApp.services.EmailService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailServiceImpl implements EmailService {
	private final JavaMailSender javaMailSender;

	@Override
	public void sendEmail(String toEmail, String subject, String body) {
		try {
			SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
			simpleMailMessage.setTo(toEmail);
			simpleMailMessage.setSubject(subject);
			simpleMailMessage.setText(body);

			javaMailSender.send(simpleMailMessage);
			log.info("Email sent Successfully ");
		} catch (Exception e) {
			log.info("Cannot send email "+e.getMessage().toString());
		}

	}

	@Override
	public void sendEmailMulti(String[] toEmail, String subject, String body) {
		try {
			SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
			simpleMailMessage.setTo(toEmail);
			simpleMailMessage.setSubject(subject);
			simpleMailMessage.setText(body);

			javaMailSender.send(simpleMailMessage);
			log.info("Email sent Successfully ");
		} catch (Exception e) {
			log.info("Cannot send email "+e.getMessage().toString());
		}
	}

}
