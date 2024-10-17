package com.rutvik.project.uber.uberApp.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SignUpDto {
	
	private String name;
	private String email;
	private String password;
}
