package com.rutvik.project.uber.uberApp.services.impl;

import java.time.LocalDateTime;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rutvik.project.uber.uberApp.dtos.DriverDto;
import com.rutvik.project.uber.uberApp.dtos.DriverRideDto;
import com.rutvik.project.uber.uberApp.dtos.RideDto;
import com.rutvik.project.uber.uberApp.dtos.RiderDto;
import com.rutvik.project.uber.uberApp.entities.Driver;
import com.rutvik.project.uber.uberApp.entities.Ride;
import com.rutvik.project.uber.uberApp.entities.RideRequest;
import com.rutvik.project.uber.uberApp.entities.User;
import com.rutvik.project.uber.uberApp.enums.RideRequestStatus;
import com.rutvik.project.uber.uberApp.enums.RideStatus;
import com.rutvik.project.uber.uberApp.exceptions.ResourceNotFoundException;
import com.rutvik.project.uber.uberApp.repositories.DriverRepository;
import com.rutvik.project.uber.uberApp.services.DriverService;
import com.rutvik.project.uber.uberApp.services.PaymentService;
import com.rutvik.project.uber.uberApp.services.RatingService;
import com.rutvik.project.uber.uberApp.services.RideRequestService;
import com.rutvik.project.uber.uberApp.services.RideService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService {
	private final RideRequestService rideRequestService;
	private final DriverRepository driverRepository;
	private final RideService rideService;
	private final ModelMapper mapper;
	private final PaymentService paymentService;
	private final RatingService ratingService;
	@Override
	public DriverRideDto cancelRide(Long rideId) {
		Ride ride = rideService.getRideById(rideId);

		Driver driver = getCurrentDriver();

		if (!driver.equals(ride.getDriver())) {
			throw new RuntimeException("Driver cannot start a ride he has not accepted it earlier");
		}

		if (!ride.getRideStatus().equals(RideStatus.CONFIRMED)) {
			throw new RuntimeException("Ride cannot be cancelled,invalid status: " + ride.getRideStatus());
		}

		rideService.updateRideStatus(ride, RideStatus.CANCELLED);
		updateDriverAvailablity(driver, true);
		return mapper.map(ride, DriverRideDto.class);
	}

	@Override
	public DriverRideDto startRide(Long rideId, String otp) {
		Ride ride = rideService.getRideById(rideId);
		Driver driver = getCurrentDriver();

		if (!driver.equals(ride.getDriver())) {
			throw new RuntimeException("Driver cannot start a ride he has not accepted it earlier");
		}

		if (!ride.getRideStatus().equals(RideStatus.CONFIRMED)) {
			throw new RuntimeException(
					"Ride status is not CONFIRMED hence cannot be started , status: " + ride.getRideStatus());
		}

		if (!otp.equals(ride.getOTP())) {
			throw new RuntimeException("Otp is not valid " + otp);
		}
		ride.setStartedAt(LocalDateTime.now());
		Ride savedRide = rideService.updateRideStatus(ride, RideStatus.ONGOING);
		
		paymentService.createNewPayment(savedRide);
		ratingService.createNewRating(savedRide);
		
		return mapper.map(savedRide, DriverRideDto.class);
	}

	@Override
	@Transactional
	public DriverRideDto endRide(Long rideId) {
		Ride ride = rideService.getRideById(rideId);
		Driver driver = getCurrentDriver();

		if (!driver.equals(ride.getDriver())) {
			throw new RuntimeException("Driver cannot start a ride he has not accepted it earlier");
		}

		if (!ride.getRideStatus().equals(RideStatus.ONGOING)) {
			throw new RuntimeException(
					"Ride status is not ONGOING hence cannot be started , status: " + ride.getRideStatus());
		}
		
		ride.setEndedAt(LocalDateTime.now());
		
		Ride savedRide = rideService.updateRideStatus(ride, RideStatus.ENDED);
		updateDriverAvailablity(driver, true);
		
		paymentService.processPayment(ride);
		
		return mapper.map(savedRide, DriverRideDto.class) ;
	}

	@Override
	public RiderDto rateRider(Long rideId, Integer rating) {
		//look 02:27:00 on week4 first video for implementing
		Ride ride = rideService.getRideById(rideId);
		Driver driver = getCurrentDriver();

		if (!driver.equals(ride.getDriver())) {
			throw new RuntimeException("Driver is not the owner of this id "+ride.getId());
		}

		if (!ride.getRideStatus().equals(RideStatus.ENDED)) {
			throw new RuntimeException(
					"Ride status is not ENDED hence cannot be rate , status: " + ride.getRideStatus());
		}
		RiderDto rateRiderDto = ratingService.rateRider(ride, rating);
		
		return rateRiderDto;
	}

	@Override
	@Transactional
	public DriverRideDto acceptRide(Long rideRequestId) {
		RideRequest rideRequest = rideRequestService.findRideRequestById(rideRequestId);
		if (!rideRequest.getRideRequestStatus().equals(RideRequestStatus.PENDING)) {
			throw new RuntimeException(
					"RideRequest cannot be accepted,status is " + rideRequest.getRideRequestStatus());
		}

		Driver currentDriver = getCurrentDriver();

		if (!currentDriver.getAvailable()) {
			throw new RuntimeException("Driver cannot accept due to unavialbility");
		}

		Driver savedDriver = updateDriverAvailablity(currentDriver, false);
		Ride ride = rideService.createNewRide(rideRequest, savedDriver);

		return mapper.map(ride, DriverRideDto.class);
	}

	@Override
	public DriverDto getMyProfile() {
		Driver currentDriver = getCurrentDriver();
		return mapper.map(currentDriver, DriverDto.class);
	}

	@Override
	public Page<RideDto> getAllMyRides(PageRequest pageRequest) {
		Driver currentDriver = getCurrentDriver();
		return rideService.getAllRidesOfDriver(currentDriver, pageRequest)
				.map(ride -> mapper.map(ride, RideDto.class));

	}

	@Override
	public Driver getCurrentDriver() {
		User user=(User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return driverRepository.findByUser(user).orElseThrow(() -> new ResourceNotFoundException("Driver not found"));
	}

	@Override
	public Driver updateDriverAvailablity(Driver driver, boolean available) {
		driver.setAvailable(available);
		return driverRepository.save(driver);
	}

	@Override
	public Driver createNewDriver(Driver driver) {
		
		return driverRepository.save(driver);
	}

}
