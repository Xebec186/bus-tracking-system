package com.xebec.BusTracking.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * JPA Entity for Stops table.
 * Represents bus stops/stations
 */
@Entity
@Table(name = "stops")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Stop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Stop name is required")
    @Size(max = 100, message = "Stop name cannot exceed 100 characters")
    @Column(nullable = false, length = 100)
    private String stopName;

    @NotNull(message = "Latitude is required")
    @DecimalMin(value = "-90.0", message = "Latitude must be between -90 and 90")
    @DecimalMax(value = "90.0", message = "Latitude must be between -90 and 90")
    @Digits(integer = 2, fraction = 8, message = "Latitude precision: 2 digits before, 8 after decimal")
    @Column(nullable = false, precision = 10, scale = 8)
    private BigDecimal latitude;

    @NotNull(message = "Longitude is required")
    @DecimalMin(value = "-180.0", message = "Longitude must be between -180 and 180")
    @DecimalMax(value = "180.0", message = "Longitude must be between -180 and 180")
    @Digits(integer = 3, fraction = 8, message = "Longitude precision: 3 digits before, 8 after decimal")
    @Column(nullable = false, precision = 11, scale = 8)
    private BigDecimal longitude;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    // Relationships
//    @OneToMany(mappedBy = "stop", cascade = CascadeType.ALL)
//    private List<RouteStop> routeStops = new ArrayList<>();

    /**
     * Get coordinates as string
     */
    public String getCoordinates() {
        return String.format("%.8f, %.8f", latitude, longitude);
    }

    /**
     * Calculate distance to another stop using Haversine formula
     * @param other The other stop
     * @return Distance in kilometers
     */
//    public double calculateDistanceTo(Stop other) {
//        if (other == null) {
//            return 0.0;
//        }
//
//        final int EARTH_RADIUS_KM = 6371;
//
//        double lat1Rad = Math.toRadians(this.latitude.doubleValue());
//        double lat2Rad = Math.toRadians(other.latitude.doubleValue());
//        double deltaLat = Math.toRadians(other.latitude.subtract(this.latitude).doubleValue());
//        double deltaLng = Math.toRadians(other.longitude.subtract(this.longitude).doubleValue());
//
//        double a = Math.sin(deltaLat / 2) * Math.sin(deltaLat / 2) +
//                   Math.cos(lat1Rad) * Math.cos(lat2Rad) *
//                   Math.sin(deltaLng / 2) * Math.sin(deltaLng / 2);
//
//        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
//
//        return EARTH_RADIUS_KM * c;
//    }
}
