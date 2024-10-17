package com.rutvik.project.uber.uberApp.strategies.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rutvik.project.uber.uberApp.entities.Driver;
import com.rutvik.project.uber.uberApp.entities.RideRequest;
import com.rutvik.project.uber.uberApp.repositories.DriverRepository;
import com.rutvik.project.uber.uberApp.strategies.DriverMatchingStrategy;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DriverMatchNearestDriverStrategy implements DriverMatchingStrategy {
	private final DriverRepository driverRepository;
	@Override
	public List<Driver> findMatchinghDriver(RideRequest rideRequest) {
		driverRepository.findTenNearsetDriver(rideRequest.getPickupLocation());
		return List.of();
	}

}
