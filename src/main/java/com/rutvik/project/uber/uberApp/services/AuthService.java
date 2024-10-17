package com.rutvik.project.uber.uberApp.services;

import com.rutvik.project.uber.uberApp.dtos.DriverDto;
import com.rutvik.project.uber.uberApp.dtos.LogOutDto;
import com.rutvik.project.uber.uberApp.dtos.LoginResponseDto;
import com.rutvik.project.uber.uberApp.dtos.SignUpDto;
import com.rutvik.project.uber.uberApp.dtos.UserDto;

public interface AuthService {
	String[] login(String email,String password);//passing refreshToken and accessToken back to user
	
	UserDto signup(SignUpDto signUpDto);
	
	DriverDto onboardNewDriver(Long userId,String vehicleId);

	boolean logout(LogOutDto logOutDto);

	String refreshToken(String refreshToken);
}
