package com.xebec.BusTracking.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RouteStopDto {
    private Long id;
    private Long routeId;
    private Long stopId;

    @NotNull(message = "Stop sequence is required")
    @Min(value = 1, message = "Stop sequence must start from 1")
    private Integer stopSequence;
}
