package com.rutvik.project.uber.uberApp.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.rutvik.project.uber.uberApp.entities.Driver;
import com.rutvik.project.uber.uberApp.entities.Ride;
import com.rutvik.project.uber.uberApp.entities.RideRequest;
import com.rutvik.project.uber.uberApp.entities.Rider;
import com.rutvik.project.uber.uberApp.enums.RideStatus;

public interface RideService {

	Ride getRideById(Long rideId);

	Ride createNewRide(RideRequest rideRequest, Driver driver);

	Ride updateRideStatus(Ride ride, RideStatus rideStatus);

	Page<Ride> getAllRidesOfRider(Rider rider, PageRequest pageRequest);

	Page<Ride> getAllRidesOfDriver(Driver driver, PageRequest pageRequest);
}
