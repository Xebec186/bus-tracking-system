package com.xebec.BusTracking.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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

    @NotNull(message = "Capacity is required")
    @Min(value = 10, message = "Minimum capacity is 10")
    @Max(value = 100, message = "Maximum capacity is 100")
    private Integer capacity;

    @Size(max = 50, message = "Make cannot exceed 50 characters")
    private String make;

    @Size(max = 50, message = "Model cannot exceed 50 characters")
    private String model;
}
