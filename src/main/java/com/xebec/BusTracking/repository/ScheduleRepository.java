package com.xebec.BusTracking.repository;

import com.xebec.BusTracking.model.Schedule;
import com.xebec.BusTracking.model.ScheduleStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findByStatus(ScheduleStatus status);
    List<Schedule> findByRouteId(Long routeId);
    List<Schedule> findByBusId(Long busId);
}
