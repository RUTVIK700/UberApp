package com.rutvik.project.uber.uberApp.configs;

import org.locationtech.jts.geom.Point;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.rutvik.project.uber.uberApp.dtos.PointDto;
import com.rutvik.project.uber.uberApp.utils.GeometryUtil;

@Configuration
public class MapperConfig {

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.typeMap(PointDto.class, Point.class).setConverter(context->{
			PointDto pointDto=context.getSource();
			return GeometryUtil.createPoint(pointDto);});
		modelMapper.typeMap(Point.class, PointDto.class).setConverter(context->{
			Point point=context.getSource();
			double cooridenate[]= {
					point.getX(),
					point.getY()
			};
			return new PointDto(cooridenate);
		});
		return modelMapper ;
				
	}
}
