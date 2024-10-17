package com.rutvik.project.uber.uberApp.dtos;

import java.time.LocalDateTime;

import com.rutvik.project.uber.uberApp.enums.TranscationMethod;
import com.rutvik.project.uber.uberApp.enums.TranscationType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WalletTranscationDto {
	private Long id;
	
	private Double amount;
	
	private TranscationType transcationType;
	
	private TranscationMethod transcationMethod;
	
	private RideDto ride;
	
	private String transcationId;
	
	private WalletDto wallet;
	
	private LocalDateTime timestamp;
	
}
