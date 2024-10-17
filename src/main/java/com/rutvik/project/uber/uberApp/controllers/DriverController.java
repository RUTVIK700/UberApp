package com.rutvik.project.uber.uberApp.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rutvik.project.uber.uberApp.dtos.DriverDto;
import com.rutvik.project.uber.uberApp.dtos.DriverRideDto;
import com.rutvik.project.uber.uberApp.dtos.RatingDto;
import com.rutvik.project.uber.uberApp.dtos.RideDto;
import com.rutvik.project.uber.uberApp.dtos.RideStartDto;
import com.rutvik.project.uber.uberApp.dtos.RiderDto;
import com.rutvik.project.uber.uberApp.services.DriverService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/drivers")
@RequiredArgsConstructor
@Secured("ROLE_DRIVER")
public class DriverController {
	
	private final DriverService driverService;
	
	@PostMapping("/acceptRide/{rideRequestId}")
	public ResponseEntity<DriverRideDto>  acceptRide(@PathVariable Long rideRequestId) {
		return ResponseEntity.ok( driverService.acceptRide(rideRequestId));
	}
	
	@PostMapping("/startRide/{rideRequestId}")
	public ResponseEntity<DriverRideDto>  startRide(@PathVariable Long rideRequestId,@RequestBody RideStartDto rideStartDto) {
		return ResponseEntity.ok( driverService.startRide(rideRequestId,rideStartDto.getOtp()));
	}
	
	@PostMapping("/endRide/{rideId}")
	public ResponseEntity<DriverRideDto>  EndRide(@PathVariable Long rideId) {
		return ResponseEntity.ok( driverService.endRide(rideId));
	}
	
	@PostMapping("/cancelRide/{rideId}")
	public ResponseEntity<DriverRideDto> cancelRide(@PathVariable Long rideId) {
		return ResponseEntity.ok(driverService.cancelRide(rideId));
	}

	@PostMapping("/rateRider")
	public ResponseEntity<RiderDto> rateDriver(@RequestBody RatingDto ratingDto  ) {
		return ResponseEntity.ok(driverService.rateRider(ratingDto.getRideId(), ratingDto.getRating()));
	}
	

	@GetMapping("/getMyProfile")
	public ResponseEntity<DriverDto> getMyProfile() {
		return ResponseEntity.ok(driverService.getMyProfile());

	}
	
	@GetMapping("/getMyAllRides")
	public ResponseEntity<Page<RideDto>> getMyAllRides(@RequestParam (defaultValue = "0") Integer pageOffset,
												@RequestParam (defaultValue = "10") Integer pageSize) {
		PageRequest pageRequest=PageRequest.of(pageOffset, pageSize,org.springframework.data.domain.Sort.by(Direction.DESC, "createdTime","id"));
		return ResponseEntity.ok(driverService.getAllMyRides(pageRequest));

	}
}
