package com.rutvik.project.uber.uberApp.services.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.rutvik.project.uber.uberApp.dtos.DriverDto;
import com.rutvik.project.uber.uberApp.dtos.RideDto;
import com.rutvik.project.uber.uberApp.dtos.RideRequestDto;
import com.rutvik.project.uber.uberApp.dtos.RiderDto;
import com.rutvik.project.uber.uberApp.entities.Driver;
import com.rutvik.project.uber.uberApp.entities.Ride;
import com.rutvik.project.uber.uberApp.entities.RideRequest;
import com.rutvik.project.uber.uberApp.entities.Rider;
import com.rutvik.project.uber.uberApp.entities.User;
import com.rutvik.project.uber.uberApp.enums.RideRequestStatus;
import com.rutvik.project.uber.uberApp.enums.RideStatus;
import com.rutvik.project.uber.uberApp.exceptions.ResourceNotFoundException;
import com.rutvik.project.uber.uberApp.repositories.RideRequestRepository;
import com.rutvik.project.uber.uberApp.repositories.RiderRepository;
import com.rutvik.project.uber.uberApp.services.DriverService;
import com.rutvik.project.uber.uberApp.services.RatingService;
import com.rutvik.project.uber.uberApp.services.RideService;
import com.rutvik.project.uber.uberApp.services.RiderService;
import com.rutvik.project.uber.uberApp.strategies.RideStrategyManager;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class RiderServiceImpl implements RiderService {
	private final ModelMapper modelMapper;
	private final RideStrategyManager strategyManager;
	private final RideRequestRepository rideRequestRepository;
	private final RiderRepository riderRepository;
	private final RideService rideService;
	private final DriverService driverService;
	private final RatingService ratingService;

	@Override
	public RideDto cancelRide(Long rideId) {
		Rider rider = getCurrentRider();
		Ride ride = rideService.getRideById(rideId);

		if (!rider.equals(ride.getRider())) {
			throw new RuntimeException("Rider does not own this ride " + ride.getId());

		}

		if (!ride.getRideStatus().equals(RideStatus.CONFIRMED)) {
			throw new RuntimeException("Ride cannot be cancelled,invalid status: " + ride.getRideStatus());
		}

		Ride savedRide = rideService.updateRideStatus(ride, RideStatus.CANCELLED);
		driverService.updateDriverAvailablity(ride.getDriver(), true);
		return modelMapper.map(savedRide, RideDto.class);
	}

	@Override
	public DriverDto rateDriver(Long rideId, Integer rating) {
		Ride ride = rideService.getRideById(rideId);
		Rider rider = getCurrentRider();

		if (!rider.equals(ride.getRider())) {
			throw new RuntimeException("Rider is not the owner of this id " + ride.getId());
		}

		if (!ride.getRideStatus().equals(RideStatus.ENDED)) {
			throw new RuntimeException(
					"Ride status is not ENDED hence cannot be rate , status: " + ride.getRideStatus());
		}
		DriverDto rateDriverDto = ratingService.rateDriver(ride, rating);

		return rateDriverDto;
	}

	@Override
	public RideRequestDto requestRide(RideRequestDto rideRequestDto) {
		Rider rider = getCurrentRider();
		RideRequest rideRequest = modelMapper.map(rideRequestDto, RideRequest.class);
		rideRequest.setRideRequestStatus(RideRequestStatus.PENDING);
		rideRequest.setRider(rider);

		Double fare = strategyManager.rideFareCalculationStrategy().calcuclateFare(rideRequest);
		rideRequest.setFare(fare);

		RideRequest saveRideRequest = rideRequestRepository.save(rideRequest);

		List<Driver> drivers = strategyManager.driverMatchingStrategy(rider.getRating())
				.findMatchinghDriver(rideRequest);
		driverService.addDrivers(drivers, saveRideRequest);
		
		return modelMapper.map(saveRideRequest, RideRequestDto.class);
	}

	@Override
	public RiderDto getMyProfile() {
		Rider rider = getCurrentRider();
		return modelMapper.map(rider, RiderDto.class);
	}

	@Override
	public Page<RideDto> getAllMyRides(PageRequest pageRequest) {
		Rider currentRider = getCurrentRider();
		return rideService.getAllRidesOfRider(currentRider, pageRequest)
				.map(ride -> modelMapper.map(ride, RideDto.class));
	}

	@Override
	public Rider createNewRider(User user) {
		Rider rider = Rider.builder().user(user).rating(0.0).build();
		return riderRepository.save(rider);
	}

	@Override
	public Rider getCurrentRider() {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return riderRepository.findByUser(user).orElseThrow(() -> new ResourceNotFoundException("id not found"));
	}

}
