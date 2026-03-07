package com.xebec.BusTracking.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RouteDto {
    private Long id;

    @NotNull(message = "Distance is required")
    @DecimalMin(value = "0.1", message = "Distance must be at least 0.1 km")
    @DecimalMax(value = "9999.99", message = "Distance cannot exceed 9999.99 km")
    @Digits(integer = 4, fraction = 2, message = "Distance must have max 4 digits before and 2 after decimal")
    private BigDecimal distanceKm;

    @NotNull(message = "Total stops is required")
    @Min(value = 2, message = "Route must have at least 2 stops")
    @Max(value = 50, message = "Route cannot have more than 50 stops")
    private Integer totalStops;
}
