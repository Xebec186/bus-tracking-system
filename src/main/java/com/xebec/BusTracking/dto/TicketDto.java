package com.xebec.BusTracking.dto;

import com.xebec.BusTracking.model.TicketStatus;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

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

    private LocalTime boardingTime;

    @NotNull(message = "Validity date is required")
    private LocalDate validityDate;

    private LocalDateTime validatedAt;

    private TicketStatus status = TicketStatus.PENDING;

    private String paymentMethod;

    private String paymentReference;
}
