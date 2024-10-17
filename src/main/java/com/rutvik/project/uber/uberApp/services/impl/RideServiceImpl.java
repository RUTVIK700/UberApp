package com.rutvik.project.uber.uberApp.services.impl;

import java.util.Random;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.rutvik.project.uber.uberApp.entities.Driver;
import com.rutvik.project.uber.uberApp.entities.Ride;
import com.rutvik.project.uber.uberApp.entities.RideRequest;
import com.rutvik.project.uber.uberApp.entities.Rider;
import com.rutvik.project.uber.uberApp.enums.RideRequestStatus;
import com.rutvik.project.uber.uberApp.enums.RideStatus;
import com.rutvik.project.uber.uberApp.exceptions.ResourceNotFoundException;
import com.rutvik.project.uber.uberApp.repositories.RideRepository;
import com.rutvik.project.uber.uberApp.services.RideRequestService;
import com.rutvik.project.uber.uberApp.services.RideService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RideServiceImpl implements RideService {
	private final RideRepository rideRepository;
	private final RideRequestService rideRequestService;
	private final ModelMapper mapper;

	@Override
	public Ride getRideById(Long rideId) {
		return rideRepository.findById(rideId)
				.orElseThrow(() -> new ResourceNotFoundException("Ride Not found with id " + rideId));
	}

	@Override
	public Ride createNewRide(RideRequest rideRequest, Driver driver) {
		rideRequest.setRideRequestStatus(RideRequestStatus.CONFIRMED);

		Ride ride = mapper.map(rideRequest, Ride.class);
		ride.setRideStatus(RideStatus.CONFIRMED);
		ride.setDriver(driver);
		ride.setOTP(generateOtp());
		ride.setId(null);

		rideRequestService.update(rideRequest);
		return rideRepository.save(ride);
	}

	@Override
	public Ride updateRideStatus(Ride ride, RideStatus rideStatus) {
		ride.setRideStatus(rideStatus);
		return rideRepository.save(ride);
	}

	@Override
	public Page<Ride> getAllRidesOfRider(Rider rider ,PageRequest pageRequest) {
		return rideRepository.findByRider(rider,pageRequest);
	}

	@Override
	public Page<Ride> getAllRidesOfDriver(Driver driver, PageRequest pageRequest) {
		return  rideRepository.findByDriver(driver,pageRequest);
	}

	private String generateOtp() {
		Random random = new Random();
		int otp = random.nextInt(1000);
		return String.format("%04d", otp);
	}
}
