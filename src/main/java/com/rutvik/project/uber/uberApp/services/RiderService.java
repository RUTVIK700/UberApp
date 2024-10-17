package com.rutvik.project.uber.uberApp.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.rutvik.project.uber.uberApp.dtos.DriverDto;
import com.rutvik.project.uber.uberApp.dtos.RideDto;
import com.rutvik.project.uber.uberApp.dtos.RideRequestDto;
import com.rutvik.project.uber.uberApp.dtos.RiderDto;
import com.rutvik.project.uber.uberApp.entities.Rider;
import com.rutvik.project.uber.uberApp.entities.User;

public interface RiderService {
	RideDto cancelRide(Long rideId);
	
	DriverDto rateDriver(Long rideId,Integer rating);
	
	RideRequestDto requestRide(RideRequestDto rideRequestDto);
	
	RiderDto getMyProfile();
	
	Page<RideDto> getAllMyRides(PageRequest pageRequest);
	
	Rider createNewRider(User user);
	
	Rider getCurrentRider();
}
