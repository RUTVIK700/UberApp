package com.rutvik.project.uber.uberApp.strategies;

import java.time.LocalTime;

import org.springframework.stereotype.Component;

import com.rutvik.project.uber.uberApp.strategies.impl.DriverMatchNearestDriverStrategy;
import com.rutvik.project.uber.uberApp.strategies.impl.DriverMatchingHighestRatedStrategy;
import com.rutvik.project.uber.uberApp.strategies.impl.RideFareDefaultFareCalculationStrategy;
import com.rutvik.project.uber.uberApp.strategies.impl.RideFareSurgePricingFareCalculationStrategy;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RideStrategyManager {
	private final DriverMatchingHighestRatedStrategy driverMatchingHighestRatedStrategy;
	private final DriverMatchNearestDriverStrategy driverMatchNearestDriverStrategy;
	private final RideFareDefaultFareCalculationStrategy defaultFareCalculationStrategy;
	private final RideFareSurgePricingFareCalculationStrategy fareSurgePricingFareCalculationStrategy;

	public DriverMatchingStrategy driverMatchingStrategy(double riderRating) {
		if(riderRating>=4.8) {
			return driverMatchingHighestRatedStrategy;
		}else {
			return driverMatchNearestDriverStrategy;
		}
		
		
	}
	public RideFareCalculationStrategy rideFareCalculationStrategy() {
		LocalTime surgeStartTime=LocalTime.of(18,0, 0);
		LocalTime surgeEndTime=LocalTime.of(21,0, 0);
		LocalTime currentTime=LocalTime.now();
		
		boolean isSurgeTime=currentTime.isAfter(surgeStartTime)&& currentTime.isBefore(surgeEndTime);
		
		if(isSurgeTime) {
			return fareSurgePricingFareCalculationStrategy;
		}else {
			return defaultFareCalculationStrategy;
		}
		
	}
}
