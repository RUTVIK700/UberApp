package com.rutvik.project.uber.uberApp.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseDto {
//	private String tokens[]=new String[2];
	
	//change to this after depolyemnet
	private String accessToken;
//	private String refreshToken; //pass this in cookie
}
