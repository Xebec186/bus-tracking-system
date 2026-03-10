package com.xebec.BusTracking.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * JPA Entity for Route_Stops junction table.
 * Represents the many-to-many relationship between Routes and Stops
 * with additional ordering and timing information
 */
@Entity
@Table(name = "route_stops"
//    uniqueConstraints = {
//        @UniqueConstraint(name = "unique_route_stop",
//                         columnNames = {"route_id", "stop_id"}),
//        @UniqueConstraint(name = "unique_route_sequence",
//                         columnNames = {"route_id", "stop_sequence"})
//    }
)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RouteStop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "route_id", nullable = false)
    private Route route;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "stop_id", nullable = false)
    private Stop stop;

    @Column(nullable = false)
    private Integer stopSequence;

    @Column(nullable = false)
    private Integer estimatedArrivalMinutes;

    /**
     * Get estimated arrival time in hours and minutes format
     */
    public String getFormattedArrivalTime() {
        if (estimatedArrivalMinutes == null) {
            return "N/A";
        }

        int hours = estimatedArrivalMinutes / 60;
        int minutes = estimatedArrivalMinutes % 60;

        if (hours > 0) {
            return String.format("%dh %dm", hours, minutes);
        } else {
            return String.format("%dm", minutes);
        }
    }

    /**
     * Get stop name
     */
    public String getStopName() {
        return stop != null ? stop.getName() : null;
    }

    /**
     * Get route number
     */
    public String getRouteNumber() {
        return route != null ? route.getNumber() : null;
    }
}