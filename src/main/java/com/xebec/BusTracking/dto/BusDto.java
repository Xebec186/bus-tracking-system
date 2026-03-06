package com.xebec.BusTracking.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BusDto {
    private Long id;
    private Integer capacity;
    private String make;
    private String model;
}
