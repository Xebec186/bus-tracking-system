package com.xebec.BusTracking.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * JPA Entity for Routes table.
 * Represents bus service routes
 */
@Entity
@Table(name = "routes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 10)
    private String number;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, precision = 6, scale = 2)
    private BigDecimal distanceKm;

    @OneToMany(mappedBy = "route", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("stopSequence ASC")
    private List<RouteStop> routeStops = new ArrayList<>();

    @Column(nullable = false)
    private Integer estimatedDurationMinutes;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "route", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Schedule> schedules = new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    public Stop getStartStop() {
        return routeStops.isEmpty() ? null : routeStops.get(0).getStop();
    }

    public Stop getEndStop() {
        return routeStops.isEmpty() ? null :
                routeStops.get(routeStops.size() - 1).getStop();
    }

    /**
     * Add a stop to the route
     */
    public void addRouteStop(RouteStop routeStop) {
        routeStops.add(routeStop);
        routeStop.setRoute(this);
    }

    /**
     * Remove a stop from the route
     */
    public void removeRouteStop(RouteStop routeStop) {
        routeStops.remove(routeStop);
        routeStop.setRoute(null);
    }

    /**
     * Calculate average speed (km/h) based on distance and duration
     */
    public BigDecimal getAverageSpeed() {
        if (estimatedDurationMinutes == null || estimatedDurationMinutes == 0) {
            return BigDecimal.ZERO;
        }
        BigDecimal hours = new BigDecimal(estimatedDurationMinutes).divide(
            new BigDecimal(60), 2, RoundingMode.HALF_UP
        );
        return distanceKm.divide(hours, 2, RoundingMode.HALF_UP);
    }

    /**
     * Get full route description
     */
    public String getFullDescription() {
        String start = getStartStop() != null ? getStartStop().getName() : "Unknown";
        String end = getEndStop() != null ? getEndStop().getName() : "Unknown";

        return String.format("%s: %s → %s (%.2f km, %d mins)",
                number,
                start,
                end,
                distanceKm,
                estimatedDurationMinutes);
    }
}
