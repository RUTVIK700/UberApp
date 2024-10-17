package com.rutvik.project.uber.uberApp.entities;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.locationtech.jts.geom.Point;

import com.rutvik.project.uber.uberApp.enums.PaymentMethod;
import com.rutvik.project.uber.uberApp.enums.RideStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(indexes = { @Index(name = "idx_ride_rider", columnList = "rider_id"),
		@Index(name = "idx_ride_driver", columnList = "driver_id") })
public class Ride {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(columnDefinition = "Geometry(Point,4326)")
	private Point pickupLocation;

	@Column(columnDefinition = "Geometry(Point,4326)")
	private Point dropLocation;

	@CreationTimestamp
	private LocalDateTime createdTime;

	@ManyToOne(fetch = FetchType.LAZY)
	private Rider rider;

	@ManyToOne(fetch = FetchType.LAZY)
	private Driver driver;

	@Enumerated(EnumType.STRING)
	private PaymentMethod paymentMethod;

	@Enumerated(EnumType.STRING)
	private RideStatus rideStatus;

	private String OTP;

	private Double fare;
	private LocalDateTime startedAt;
	private LocalDateTime endedAt;
}
