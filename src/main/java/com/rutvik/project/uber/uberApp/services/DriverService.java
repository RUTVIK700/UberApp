package com.rutvik.project.uber.uberApp.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.rutvik.project.uber.uberApp.dtos.DriverDto;
import com.rutvik.project.uber.uberApp.dtos.DriverRideDto;
import com.rutvik.project.uber.uberApp.dtos.RideDto;
import com.rutvik.project.uber.uberApp.dtos.RiderDto;
import com.rutvik.project.uber.uberApp.entities.Driver;

public interface DriverService {
	DriverRideDto cancelRide(Long rideId);
	
	DriverRideDto startRide(Long rideId,String otp);
	
	DriverRideDto endRide(Long rideId);
	
	RiderDto rateRider(Long rideId,Integer rating);
	
	DriverRideDto acceptRide(Long rideRequestId);
	
	DriverDto getMyProfile();
	
	Page<RideDto> getAllMyRides(PageRequest pageRequest);
	
	Driver getCurrentDriver();
	
	Driver updateDriverAvailablity(Driver driver,boolean available);
	
	Driver createNewDriver(Driver driver);
}
