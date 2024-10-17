package com.rutvik.project.uber.uberApp.services.impl;

import java.util.List;

import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.rutvik.project.uber.uberApp.services.DistanceService;

import lombok.Data;
@Service
public class DistanceServiceImpl implements DistanceService {
	private static final String OSRM_API="https://router.project-osrm.org/route/v1/driving/";
		
	@Override
	public double calculateDistance(Point src, Point dest) {
		try {
			String uri=src.getX()+","+src.getY()+";"+dest.getX()+","+dest.getY();
			OSRMResponseDto responseDto=RestClient.builder()
					.baseUrl(OSRM_API)
						.build()
							.get()
								.uri(uri)
									.retrieve()
										.body(OSRMResponseDto.class);
		return responseDto.getRoutes().get(0).getDistance()/1000.0;
		}catch (Exception e) {
			throw new RuntimeException("Error getting data from OSRM"+e.getMessage());
		}

	}
	
}

@Data
class OSRMResponseDto{ 
	private List<OSRMRoute> routes;
}

@Data
class OSRMRoute{
	private Double distance;
}
