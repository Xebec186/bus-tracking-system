package com.xebec.BusTracking.repository;

import com.xebec.BusTracking.model.ScheduleDay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.util.Optional;

@Repository
public interface ScheduleDayRepository extends JpaRepository<ScheduleDay, Long> {
    Optional<ScheduleDay> findByScheduleIdAndDayOfWeek(Long scheduleId, DayOfWeek dayOfWeek);
}
