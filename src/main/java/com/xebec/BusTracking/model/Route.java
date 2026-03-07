package com.xebec.BusTracking.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
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

//    @NotBlank(message = "Route number is required")
//    @Pattern(regexp = "^R\\d{3}$", message = "Route number must follow format: R001, R002, etc.")
//    @Column(name = "route_number", unique = true, nullable = false, length = 10)
//    private String routeNumber;

//    @NotBlank(message = "Route name is required")
//    @Size(max = 100, message = "Route name cannot exceed 100 characters")
//    @Column(name = "route_name", nullable = false, length = 100)
//    private String routeName;

    @Column(nullable = false, precision = 6, scale = 2)
    private BigDecimal distanceKm;

    @Column(nullable = false)
    private Integer totalStops;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @OneToMany(mappedBy = "route", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("stopSequence ASC")
    private List<RouteStop> routeStops = new ArrayList<>();

//    @NotNull(message = "Estimated duration is required")
//    @Min(value = 5, message = "Estimated duration must be at least 5 minutes")
//    @Max(value = 300, message = "Estimated duration cannot exceed 300 minutes")
//    @Column(name = "estimated_duration_minutes", nullable = false)
//    private Integer estimatedDurationMinutes;

    // Relationships
//    @OneToMany(mappedBy = "route", cascade = CascadeType.ALL, orphanRemoval = true)
//    @OrderBy("stopSequence ASC")
//    private List<RouteStop> routeStops = new ArrayList<>();
//
//    @OneToMany(mappedBy = "route")
//    private List<Schedule> schedules = new ArrayList<>();
//
//    @OneToMany(mappedBy = "route")
//    private List<Ticket> tickets = new ArrayList<>();
//
//    /**
//     * Add a stop to the route
//     */
//    public void addRouteStop(RouteStop routeStop) {
//        routeStops.add(routeStop);
//        routeStop.setRoute(this);
//    }
//
//    /**
//     * Remove a stop from the route
//     */
//    public void removeRouteStop(RouteStop routeStop) {
//        routeStops.remove(routeStop);
//        routeStop.setRoute(null);
//    }
//
//    /**
//     * Calculate average speed (km/h) based on distance and duration
//     */
//    public BigDecimal getAverageSpeed() {
//        if (estimatedDurationMinutes == null || estimatedDurationMinutes == 0) {
//            return BigDecimal.ZERO;
//        }
//        BigDecimal hours = new BigDecimal(estimatedDurationMinutes).divide(
//            new BigDecimal(60), 2, BigDecimal.ROUND_HALF_UP
//        );
//        return distanceKm.divide(hours, 2, BigDecimal.ROUND_HALF_UP);
//    }
//
//    /**
//     * Get full route description
//     */
//    public String getFullDescription() {
//        return String.format("%s: %s → %s (%.2f km, %d mins)",
//            routeNumber, startPoint, endPoint, distanceKm, estimatedDurationMinutes);
//    }
}
