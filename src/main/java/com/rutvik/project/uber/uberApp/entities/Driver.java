package com.rutvik.project.uber.uberApp.entities;

import org.locationtech.jts.geom.Point;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="app_driver",indexes = {
		@Index(name="idx_driver_vehicle_id", columnList = "vehicleId")
})
public class Driver {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Double rating;
	
	@OneToOne
	@JoinColumn(name="user_id")
	private User user;
	
	private Boolean available; 
	private String vehicleId;
	
	@Column(columnDefinition = "Geometry(Point,4326)")
	Point currentLocation;
}
