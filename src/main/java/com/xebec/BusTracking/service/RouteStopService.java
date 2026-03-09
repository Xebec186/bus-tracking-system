package com.xebec.BusTracking.service;

import com.xebec.BusTracking.dto.RouteStopDto;

import java.util.List;

public interface RouteStopService {
    RouteStopDto addRouteStop(RouteStopDto routeStopDto);

    RouteStopDto getRouteStopById(Long routeStopId);

    List<RouteStopDto> getAllRouteStops();

    RouteStopDto updateRouteStop(Long routeStopId, RouteStopDto routeStopDto);

    void deleteRouteStop(Long routeStopId);
}
