package com.rutvik.project.uber.uberApp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.rutvik.project.uber.uberApp.services.EmailService;

@SpringBootTest
public class UberAppApplicationTests {
	
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
