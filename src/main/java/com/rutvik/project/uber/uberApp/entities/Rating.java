package com.rutvik.project.uber.uberApp.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(indexes = { @Index(name = "idx_rating_rider", columnList = "rider_id"),
		@Index(name = "idx_rating_driver", columnList = "driver_id") })
@Builder
public class Rating {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;

	@OneToOne(fetch = FetchType.LAZY)
	private Ride ride;

	@ManyToOne
	private Rider rider;

	@ManyToOne
	private Driver driver;

	private Integer driverRating;

	private Integer riderRating;
}
