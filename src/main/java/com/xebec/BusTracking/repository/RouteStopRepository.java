package com.xebec.BusTracking.repository;

import com.xebec.BusTracking.model.RouteStop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RouteStopRepository extends JpaRepository<RouteStop, Long> {
    List<RouteStop> findByRouteId(Long routeId);

    List<RouteStop> findByRouteIdOrderByStopSequence(Long routeId);

    List<RouteStop> findByRouteIdOrderByStopOrder(Long routeId);
}
