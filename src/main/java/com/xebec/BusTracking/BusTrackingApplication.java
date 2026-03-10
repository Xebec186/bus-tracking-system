package com.xebec.BusTracking;

import com.xebec.BusTracking.dto.RouteStopDto;
import com.xebec.BusTracking.model.RouteStop;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BusTrackingApplication {

	public static void main(String[] args) {
		SpringApplication.run(BusTrackingApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.typeMap(RouteStop.class, RouteStopDto.class)
				.addMappings(mapper -> {
					mapper.map(src -> src.getRoute().getId(), RouteStopDto::setRouteId);
					mapper.map(src -> src.getStop().getId(), RouteStopDto::setStopId);
				});

		return modelMapper;
	}
}
