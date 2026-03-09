package com.xebec.BusTracking.service;

import com.xebec.BusTracking.dto.ScheduleDto;

import java.util.List;

public interface ScheduleService {
    ScheduleDto addSchedule(ScheduleDto scheduleDto);

    ScheduleDto getScheduleById(Long scheduleId);

    List<ScheduleDto> getAllSchedules();

    ScheduleDto updateSchedule(Long scheduleId, ScheduleDto scheduleDto);

    void deleteSchedule(Long scheduleId);
}