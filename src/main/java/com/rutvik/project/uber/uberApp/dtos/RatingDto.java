package com.rutvik.project.uber.uberApp.dtos;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class RatingDto {
	private Long rideId;
	private Integer rating;
}
