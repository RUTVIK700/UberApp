package com.rutvik.project.uber.uberApp.entities;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.rutvik.project.uber.uberApp.enums.TranscationMethod;
import com.rutvik.project.uber.uberApp.enums.TranscationType;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(indexes = {
		@Index(name="idx_wallet_transcation_wallet",columnList = "wallet_id"),
})
public class WalletTranscation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Double amount;
	
	private TranscationType transcationType;
	
	private TranscationMethod transcationMethod;
	
	@ManyToOne
	private Ride ride;
	
	private String transcationId;
	
	@CreationTimestamp
	private LocalDateTime timestamp;
	
	@ManyToOne
	private Wallet wallet;
}
