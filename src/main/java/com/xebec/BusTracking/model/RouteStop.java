//package com.xebec.BusTracking.model;
//
//import jakarta.persistence.*;
//import jakarta.validation.constraints.*;
//import lombok.*;
//
///**
// * JPA Entity for Route_Stops junction table
// * Represents the many-to-many relationship between Routes and Stops
// * with additional ordering and timing information
// */
//@Entity
//@Table(name = "route_stops",
//    uniqueConstraints = {
//        @UniqueConstraint(name = "unique_route_stop",
//                         columnNames = {"route_id", "stop_id"}),
//        @UniqueConstraint(name = "unique_route_sequence",
//                         columnNames = {"route_id", "stop_sequence"})
//    }
//)
//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//public class RouteStop {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "route_stop_id")
//    private Long routeStopId;
//
//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "route_id", nullable = false)
//    @ToString.Exclude
//    @EqualsAndHashCode.Exclude
//    private Route route;
//
//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "stop_id", nullable = false)
//    @ToString.Exclude
//    @EqualsAndHashCode.Exclude
//    private Stop stop;
//
//    @NotNull(message = "Stop sequence is required")
//    @Min(value = 1, message = "Stop sequence must start from 1")
//    @Column(name = "stop_sequence", nullable = false)
//    private Integer stopSequence;
//
//    @NotNull(message = "Estimated arrival time is required")
//    @Min(value = 0, message = "Estimated arrival cannot be negative")
//    @Max(value = 300, message = "Estimated arrival cannot exceed 300 minutes")
//    @Column(name = "estimated_arrival_minutes", nullable = false)
//    private Integer estimatedArrivalMinutes;
//
//    /**
//     * Check if this is the first stop in the route
//     */
//    public boolean isFirstStop() {
//        return stopSequence != null && stopSequence == 1;
//    }
//
//    /**
//     * Check if this is the last stop in the route
//     */
//    public boolean isLastStop() {
//        return route != null &&
//               stopSequence != null &&
//               stopSequence.equals(route.getTotalStops());
//    }
//
//    /**
//     * Get estimated arrival time in hours and minutes format
//     */
//    public String getFormattedArrivalTime() {
//        if (estimatedArrivalMinutes == null) {
//            return "N/A";
//        }
//
//        int hours = estimatedArrivalMinutes / 60;
//        int minutes = estimatedArrivalMinutes % 60;
//
//        if (hours > 0) {
//            return String.format("%dh %dm", hours, minutes);
//        } else {
//            return String.format("%dm", minutes);
//        }
//    }
//
//    /**
//     * Get stop name (convenience method)
//     */
//    public String getStopName() {
//        return stop != null ? stop.getStopName() : null;
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
//     * Validate that stop sequence is within route's total stops
//     */
//    @PrePersist
//    @PreUpdate
//    private void validateStopSequence() {
//        if (route != null && stopSequence != null && route.getTotalStops() != null) {
//            if (stopSequence > route.getTotalStops()) {
//                throw new IllegalArgumentException(
//                    String.format("Stop sequence %d exceeds route's total stops %d",
//                        stopSequence, route.getTotalStops())
//                );
//            }
//        }
//    }
//
//    /**
//     * Validate that estimated arrival is reasonable for the route
//     */
//    @PrePersist
//    @PreUpdate
//    private void validateEstimatedArrival() {
//        if (route != null && estimatedArrivalMinutes != null &&
//            route.getEstimatedDurationMinutes() != null) {
//
//            if (estimatedArrivalMinutes > route.getEstimatedDurationMinutes()) {
//                throw new IllegalArgumentException(
//                    String.format("Estimated arrival %d minutes exceeds route duration %d minutes",
//                        estimatedArrivalMinutes, route.getEstimatedDurationMinutes())
//                );
//            }
//        }
//    }
//}
