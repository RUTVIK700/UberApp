package com.rutvik.project.uber.uberApp.strategies.impl;

import org.springframework.stereotype.Service;

import com.rutvik.project.uber.uberApp.entities.RideRequest;
import com.rutvik.project.uber.uberApp.services.DistanceService;
import com.rutvik.project.uber.uberApp.strategies.RideFareCalculationStrategy;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RideFareDefaultFareCalculationStrategy implements RideFareCalculationStrategy {
	private final DistanceService distanceService;

	@Override
	public double calcuclateFare(RideRequest rideRequest) {
		Double distance = distanceService.calculateDistance(rideRequest.getPickupLocation(),
				rideRequest.getDropLocation());
		return distance * RIDE_FARE_MULTIPLER;
	}

}
