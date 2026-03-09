package com.xebec.BusTracking.dto;

import com.xebec.BusTracking.model.ScheduleStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleDto {
    private Long scheduleId;
    private Long busId;
    private Long routeId;

    @NotNull(message = "Departure time is required")
    private LocalTime departureTime;

    @NotNull(message = "Arrival time is required")
    private LocalTime arrivalTime;

    @NotBlank(message = "Days of week is required")
    @Pattern(regexp = "^(MON|TUE|WED|THU|FRI|SAT|SUN)(,(MON|TUE|WED|THU|FRI|SAT|SUN))*$",
            message = "Days must be comma-separated: MON,TUE,WED,THU,FRI,SAT,SUN")
    private String daysOfWeek;

    @NotNull(message = "Effective date is required")
    private LocalDate effectiveDate;
    private LocalDate expiryDate;

    private ScheduleStatus status;
}
