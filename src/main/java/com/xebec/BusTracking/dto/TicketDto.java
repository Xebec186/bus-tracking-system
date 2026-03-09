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
public class TicketDto {
    private Long id;
    private Long passengerId;

    private Long scheduleId;

    @NotBlank(message = "Ticket code is required")
    private String code;

    private Long originStopId;
    private Long destinationStopId;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.01", message = "Price must be positive")
    @DecimalMax(value = "999.99", message = "Price cannot exceed 999.99")
    @Digits(integer = 3, fraction = 2, message = "Price format: XXX.XX")
    private BigDecimal price;
}
