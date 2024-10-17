package com.rutvik.project.uber.uberApp.services;

import com.rutvik.project.uber.uberApp.entities.RideRequest;

public interface RideRequestService {
	
	RideRequest findRideRequestById(Long rideRequestId);

	void update(RideRequest rideRequest);
}
