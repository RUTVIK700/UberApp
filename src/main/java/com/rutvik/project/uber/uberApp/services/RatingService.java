package com.rutvik.project.uber.uberApp.services;

import com.rutvik.project.uber.uberApp.dtos.DriverDto;
import com.rutvik.project.uber.uberApp.dtos.RiderDto;
import com.rutvik.project.uber.uberApp.entities.Ride;

public interface RatingService {

	DriverDto rateDriver(Ride ride, Integer rating);

	RiderDto rateRider(Ride ride, Integer rating);

	void createNewRating(Ride ride);
}
