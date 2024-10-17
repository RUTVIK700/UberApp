package com.rutvik.project.uber.uberApp.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.rutvik.project.uber.uberApp.dtos.OtpDto;
import com.rutvik.project.uber.uberApp.entities.Ride;
import com.rutvik.project.uber.uberApp.enums.RideStatus;
import com.rutvik.project.uber.uberApp.exceptions.ResourceNotFoundException;
import com.rutvik.project.uber.uberApp.services.OtpService;
import com.rutvik.project.uber.uberApp.services.RideService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OtpServiceImpl implements OtpService {
	private final RideService rideService;
	private final ModelMapper mapper;

	@Override
	public OtpDto getotp(Long rideId) {
		Ride ride = rideService.getRideById(rideId);
		if(ride.getRideStatus()==RideStatus.CANCELLED) {
			throw new ResourceNotFoundException("The Ride is cancelled with id "+ride.getId());
		}
		return mapper.map(ride, OtpDto.class);
	}

}
