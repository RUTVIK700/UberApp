package com.rutvik.project.uber.uberApp.strategies.impl;

import org.springframework.stereotype.Service;

import com.rutvik.project.uber.uberApp.entities.RideRequest;
import com.rutvik.project.uber.uberApp.services.DistanceService;
import com.rutvik.project.uber.uberApp.strategies.RideFareCalculationStrategy;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RideFareSurgePricingFareCalculationStrategy implements RideFareCalculationStrategy {
	private final DistanceService distanceService;
	private static final double SURGE_FACTOR = 2;// Call 3rd party API for rainy,summer weather and increase/decrease
													// surge value based on that

	@Override
	public double calcuclateFare(RideRequest rideRequest) {
		Double distance = distanceService.calculateDistance(rideRequest.getPickupLocation(),
				rideRequest.getDropLocation());
		return distance * SURGE_FACTOR*RIDE_FARE_MULTIPLER;
	}

}
