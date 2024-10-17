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
import com.rutvik.project.uber.uberApp.dtos.OtpDto;
import com.rutvik.project.uber.uberApp.dtos.RatingDto;
import com.rutvik.project.uber.uberApp.dtos.RideDto;
import com.rutvik.project.uber.uberApp.dtos.RideRequestDto;
import com.rutvik.project.uber.uberApp.dtos.RiderDto;
import com.rutvik.project.uber.uberApp.services.OtpService;
import com.rutvik.project.uber.uberApp.services.RiderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/rider")
@RequiredArgsConstructor
@Secured("ROLE_RIDER")
public class RiderController {
	private final RiderService riderService;
	private final OtpService otpService;

	@GetMapping("/getOtp/{rideId}")
	public ResponseEntity<OtpDto> requestOtp(@PathVariable Long rideId) {
		return ResponseEntity.ok(otpService.getotp(rideId));

	}

	@PostMapping("/requestRide")
	public ResponseEntity<RideRequestDto> requestRide(@RequestBody RideRequestDto rideRequestDto) {
		return ResponseEntity.ok(riderService.requestRide(rideRequestDto));

	}

	@PostMapping("/cancelRide/{rideId}")
	public ResponseEntity<RideDto> cancelRide(@PathVariable Long rideId) {
		return ResponseEntity.ok(riderService.cancelRide(rideId));
	}

	@PostMapping("/rateDriver")
	public ResponseEntity<DriverDto> rateDriver(@RequestBody RatingDto ratingDto) {
		return ResponseEntity.ok(riderService.rateDriver(ratingDto.getRideId(),ratingDto.getRating()));
	}
	

	@GetMapping("/getMyProfile")
	public ResponseEntity<RiderDto> getMyProfile() {
		return ResponseEntity.ok(riderService.getMyProfile());

	}
	
	@GetMapping("/getMyAllRides")
	public ResponseEntity<Page<RideDto>> getMyAllRides(@RequestParam (defaultValue = "0") Integer pageOffset,
												@RequestParam (defaultValue = "10") Integer pageSize) {
		PageRequest pageRequest=PageRequest.of(pageOffset, pageSize,org.springframework.data.domain.Sort.by(Direction.DESC, "createdTime","id"));
		return ResponseEntity.ok(riderService.getAllMyRides(pageRequest));

	}
}
