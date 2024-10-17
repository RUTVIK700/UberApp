package com.rutvik.project.uber.uberApp.dtos;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class WalletDto {
	private Long id;

	private UserDto userDto;

	private Double balance;
	@JsonIgnore
	private List<WalletTranscationDto> transcation;
}
