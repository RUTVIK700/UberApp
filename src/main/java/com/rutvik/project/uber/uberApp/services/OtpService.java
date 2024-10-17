package com.rutvik.project.uber.uberApp.services;

import com.rutvik.project.uber.uberApp.dtos.OtpDto;

public interface OtpService {

	OtpDto getotp(Long rideId);
}
