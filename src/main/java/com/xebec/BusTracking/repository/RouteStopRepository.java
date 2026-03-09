package com.xebec.BusTracking.repository;

import com.xebec.BusTracking.model.RouteStop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RouteStopRepository extends JpaRepository<RouteStop, Long> {
}
