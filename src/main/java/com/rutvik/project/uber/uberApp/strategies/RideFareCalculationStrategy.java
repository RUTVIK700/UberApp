package com.rutvik.project.uber.uberApp.strategies;

import com.rutvik.project.uber.uberApp.entities.RideRequest;

public interface RideFareCalculationStrategy {
	double RIDE_FARE_MULTIPLER=10;
	
	double calcuclateFare(RideRequest rideRequest);
}
