package com.rutvik.project.uber.uberApp.services.impl;

import org.springframework.stereotype.Service;

import com.rutvik.project.uber.uberApp.entities.RideRequest;
import com.rutvik.project.uber.uberApp.exceptions.ResourceNotFoundException;
import com.rutvik.project.uber.uberApp.repositories.RideRequestRepository;
import com.rutvik.project.uber.uberApp.services.RideRequestService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RideRequestServiceImpl implements RideRequestService {
	private final RideRequestRepository rideRequestRepository;

	@Override
	public RideRequest findRideRequestById(Long rideRequestId) {
		return rideRequestRepository.findById(rideRequestId)
				.orElseThrow(() -> new ResourceNotFoundException("RideRequest not found with id: " + rideRequestId));
	}

	@Override
	public void update(RideRequest rideRequest) {
		rideRequestRepository.findById(rideRequest.getId())
				.orElseThrow(() -> new ResourceNotFoundException("RideRequest not found with id: " + rideRequest));
		rideRequestRepository.save(rideRequest);
	}

}