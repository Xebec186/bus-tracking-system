package com.xebec.BusTracking.service.impl;

import com.xebec.BusTracking.dto.RouteDto;
import com.xebec.BusTracking.service.RouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RouteServiceImpl implements RouteService {
    @Override
    public RouteDto addRoute(RouteDto routeDto) {
        return null;
    }

    @Override
    public RouteDto getRouteById(Long routeId) {
        return null;
    }

    @Override
    public List<RouteDto> getAllRoutes() {
        return List.of();
    }

    @Override
    public RouteDto updateRoute(Long routeId, RouteDto routeDto) {
        return null;
    }

    @Override
    public void deleteRoute(Long routeId) {

    }
}
