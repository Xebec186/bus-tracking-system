package com.xebec.BusTracking.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TicketDto {
    private Long id;
    private Long passengerId;
    private Long routeId;
    private Long busId;
    private String code;
    private Long originStopId;
    private Long destinationStopId;
}
