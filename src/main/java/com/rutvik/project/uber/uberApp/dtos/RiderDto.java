package com.rutvik.project.uber.uberApp.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RiderDto {
	private Long id;
	private UserDto user;
	private Double rating;
}
