package com.xebec.BusTracking.dto;

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
    private Integer stopSequence;
}
