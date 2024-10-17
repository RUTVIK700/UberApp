package com.rutvik.project.uber.uberApp.emailTestCases;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.rutvik.project.uber.uberApp.services.EmailService;

class EmailTest {

	@Autowired
	private EmailService emailService;
	
	@Test
	void contextLoads() {
		emailService.sendEmail("bosopo5387@adambra.com", "TESTING EMAIL", "MY BODY");
	}
	@Test
	void newTest() {
		String[] email= {"hello@gmail.com","rutvik@gmail.com"};
		emailService.sendEmailMulti(email, "TESTING EMAIL", "MY BODY");
	}

}
