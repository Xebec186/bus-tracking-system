package com.xebec.BusTracking.service.impl;

import com.xebec.BusTracking.dto.RouteDto;
import com.xebec.BusTracking.exception.ResourceNotFoundException;
import com.xebec.BusTracking.model.Route;
import com.xebec.BusTracking.repository.RouteRepository;
import com.xebec.BusTracking.repository.RouteStopRepository;
import com.xebec.BusTracking.service.RouteService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class RouteServiceImpl implements RouteService {

    private final RouteRepository routeRepository;
    private final ModelMapper modelMapper;

    @Override
    public RouteDto addRoute(RouteDto routeDto) {
        Route route = modelMapper.map(routeDto, Route.class);

        Route addedRoute = routeRepository.save(route);

        return modelMapper.map(addedRoute, RouteDto.class);
    }

    @Override
    @Transactional(readOnly = true)
    public RouteDto getRouteById(Long routeId) {
        Route route = routeRepository.findById(routeId)
                .orElseThrow(() -> new ResourceNotFoundException("Route not found with given id: " + routeId));
        return modelMapper.map(route, RouteDto.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RouteDto> getAllRoutes() {
        return routeRepository.findAll().stream()
                .map((route) -> modelMapper.map(route, RouteDto.class))
                .toList();
    }

    @Override
    public RouteDto updateRoute(Long routeId, RouteDto routeDto) {
        Route route = routeRepository.findById(routeId)
                .orElseThrow(() -> new ResourceNotFoundException("Route not found with given id: " + routeId));

        route.setName(routeDto.getName());
        route.setNumber(routeDto.getNumber());
        route.setDistanceKm(routeDto.getDistanceKm());
        route.setEstimatedDurationMinutes(routeDto.getEstimatedDurationMinutes());

        Route updatedRoute = routeRepository.save(route);

        return modelMapper.map(updatedRoute, RouteDto.class);
    }

    @Override
    public void deleteRoute(Long routeId) {
        Route route = routeRepository.findById(routeId)
                .orElseThrow(() -> new ResourceNotFoundException("Route not found with given id: " + routeId));
        routeRepository.delete(route);
    }
}
