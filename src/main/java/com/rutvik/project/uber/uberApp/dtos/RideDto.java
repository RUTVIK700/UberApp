package com.rutvik.project.uber.uberApp.dtos;

import java.time.LocalDateTime;

import com.rutvik.project.uber.uberApp.enums.PaymentMethod;
import com.rutvik.project.uber.uberApp.enums.RideStatus;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RideDto {

	private Long id;
	private PointDto pickupLocation;
	private PointDto dropLocation;
	private LocalDateTime createdTime;
	private RiderDto rider;
	private DriverDto driver;
	private PaymentMethod paymentMethod;
	private RideStatus rideStatus;
	private Double fare;
	private LocalDateTime startedAt;
	private LocalDateTime endedAt;
	private String OTP;
}
