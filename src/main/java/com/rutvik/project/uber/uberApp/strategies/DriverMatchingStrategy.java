package com.rutvik.project.uber.uberApp.strategies;

import java.util.List;

import com.rutvik.project.uber.uberApp.entities.Driver;
import com.rutvik.project.uber.uberApp.entities.RideRequest;

public interface DriverMatchingStrategy {
	
	 List<Driver> findMatchinghDriver(RideRequest rideRequest);
}
