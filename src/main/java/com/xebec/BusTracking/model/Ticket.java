package com.xebec.BusTracking.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * JPA Entity for Tickets table.
 * Represents mobile ticketing transactions
 */
@Entity
@Table(name = "tickets"
//    indexes = {
//        @Index(name = "idx_passenger_tickets",
//               columnList = "passenger_id, purchase_timestamp DESC"),
//        @Index(name = "idx_ticket_status",
//               columnList = "status, validity_date")
//    }
)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "passenger_id", nullable = false)
    private User passenger;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "schedule_id", nullable = false)
    private Schedule schedule;

    @Column(unique = true, nullable = false, length = 50)
    private String code;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "origin_stop_id", nullable = false)
    private Stop originStop;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "destination_stop_id", nullable = false)
    private Stop destinationStop;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(nullable = false)
    private LocalTime boardingTime;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }


//    TODO: ticket status
//    @Enumerated(EnumType.STRING)
//    @Column(name = "status", length = 20)
//    private TicketStatus status = TicketStatus.PENDING;
//
//seatNumber
//    validatedAt
//    @NotNull(message = "Validity date is required")
//    @Column(name = "validity_date", nullable = false)
//    private LocalDate validityDate;
//
//    @Column(name = "payment_method", length = 30)
//    private String paymentMethod;
//
//    @Column(name = "payment_reference", length = 100)
//    private String paymentReference;

//    /**
//     * Check if ticket is paid
//     */
//    public boolean isPaid() {
//        return TicketStatus.PAID.equals(this.status);
//    }
//
//    /**
//     * Check if ticket is used
//     */
//    public boolean isUsed() {
//        return TicketStatus.USED.equals(this.status);
//    }
//
//    /**
//     * Check if ticket is expired
//     */
//    public boolean isExpired() {
//        return TicketStatus.EXPIRED.equals(this.status) ||
//               (validityDate != null && LocalDate.now().isAfter(validityDate));
//    }
//
//    /**
//     * Check if ticket is cancelled
//     */
//    public boolean isCancelled() {
//        return TicketStatus.CANCELLED.equals(this.status);
//    }
//
//    /**
//     * Check if ticket can be validated (used)
//     */
//    public boolean canBeValidated() {
//        return TicketStatus.PAID.equals(this.status) &&
//               !isExpired() &&
//               validityDate != null &&
//               !LocalDate.now().isAfter(validityDate);
//    }
//
//    /**
//     * Check if ticket can be cancelled
//     */
//    public boolean canBeCancelled() {
//        return (TicketStatus.PENDING.equals(this.status) ||
//                TicketStatus.PAID.equals(this.status)) &&
//               !isUsed();
//    }
//
//    /**
//     * Mark ticket as paid
//     */
//    public void markAsPaid(String paymentMethod, String paymentReference) {
//        if (!TicketStatus.PENDING.equals(this.status)) {
//            throw new IllegalStateException("Only PENDING tickets can be marked as PAID");
//        }
//        this.status = TicketStatus.PAID;
//        this.paymentMethod = paymentMethod;
//        this.paymentReference = paymentReference;
//    }
//
//    /**
//     * Validate/use the ticket
//     */
//    public void validate(Bus bus) {
//        if (!canBeValidated()) {
//            throw new IllegalStateException("Ticket cannot be validated");
//        }
//        this.status = TicketStatus.USED;
//        this.bus = bus;
//    }
//
//    /**
//     * Cancel the ticket
//     */
//    public void cancel() {
//        if (!canBeCancelled()) {
//            throw new IllegalStateException("Ticket cannot be cancelled");
//        }
//        this.status = TicketStatus.CANCELLED;
//    }
//
//    /**
//     * Mark ticket as expired
//     */
//    public void markAsExpired() {
//        if (TicketStatus.PAID.equals(this.status) && isExpired()) {
//            this.status = TicketStatus.EXPIRED;
//        }
//    }
//
//    /**
//     * Get passenger name (convenience method)
//     */
//    public String getPassengerName() {
//        return passenger != null ? passenger.getFullName() : null;
//    }
//
//    /**
//     * Get route number (convenience method)
//     */
//    public String getRouteNumber() {
//        return route != null ? route.getRouteNumber() : null;
//    }
//
//    /**
//     * Get origin stop name (convenience method)
//     */
//    public String getOriginStopName() {
//        return originStop != null ? originStop.getStopName() : null;
//    }
//
//    /**
//     * Get destination stop name (convenience method)
//     */
//    public String getDestinationStopName() {
//        return destinationStop != null ? destinationStop.getStopName() : null;
//    }
//
//    /**
//     * Get bus registration (convenience method)
//     */
//    public String getBusRegistration() {
//        return bus != null ? bus.getRegistrationNumber() : null;
//    }
//
//    /**
//     * Get ticket journey description
//     */
//    public String getJourneyDescription() {
//        return String.format("%s: %s → %s",
//            getRouteNumber(),
//            getOriginStopName(),
//            getDestinationStopName()
//        );
//    }
//
//    /**
//     * Get formatted price
//     */
//    public String getFormattedPrice() {
//        return String.format("GHS %.2f", price);
//    }
//
//    /**
//     * Validate origin and destination are different
//     */
//    @PrePersist
//    @PreUpdate
//    private void validateStops() {
//        if (originStop != null && destinationStop != null) {
//            if (originStop.getStopId().equals(destinationStop.getStopId())) {
//                throw new IllegalArgumentException(
//                    "Origin and destination stops must be different"
//                );
//            }
//        }
//    }
//
//    /**
//     * Validate passenger has PASSENGER role
//     */
//    @PrePersist
//    @PreUpdate
//    private void validatePassenger() {
//        if (passenger != null && !passenger.isPassenger()) {
//            throw new IllegalArgumentException(
//                "Ticket passenger must have PASSENGER role"
//            );
//        }
//    }
//
//    /**
//     * Validate validity date is not in the past
//     */
//    @PrePersist
//    private void validateValidityDate() {
//        if (validityDate != null && validityDate.isBefore(LocalDate.now())) {
//            throw new IllegalArgumentException(
//                "Validity date cannot be in the past"
//            );
//        }
//    }
}
