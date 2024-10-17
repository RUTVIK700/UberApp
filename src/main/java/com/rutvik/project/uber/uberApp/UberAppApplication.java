package com.rutvik.project.uber.uberApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EntityScan(basePackages = "com.rutvik.uber.uberApp")  
public class UberAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(UberAppApplication.class, args);
	}

}
