package com.rutvik.project.uber.uberApp.dtos;

import java.time.LocalDateTime;

import com.rutvik.project.uber.uberApp.enums.PaymentMethod;
import com.rutvik.project.uber.uberApp.enums.RideRequestStatus;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RideRequestDto {
	private Long id;
	private PointDto pickupLocation;
	private PointDto dropLocation;
	private LocalDateTime requestedTime;
	private PaymentMethod paymentMethod;
	private RiderDto rider;
	private RideRequestStatus rideRequestStatus;
	private double fare;
}
