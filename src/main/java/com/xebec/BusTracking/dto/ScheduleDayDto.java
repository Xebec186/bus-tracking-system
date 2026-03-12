package com.xebec.BusTracking.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.DayOfWeek;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ScheduleDayDto {
    private Long id;
    private Long scheduleId;
    private DayOfWeek dayOfWeek;
    private LocalTime departureTime;
    private LocalTime arrivalTime;
}
