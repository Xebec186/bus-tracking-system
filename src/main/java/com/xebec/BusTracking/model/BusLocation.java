package com.xebec.BusTracking.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * JPA Entity for Bus_Locations table.
 * Represents GPS tracking data for buses
 */
@Entity
@Table(name = "bus_locations"
//    indexes = {
//        @Index(name = "idx_bus_timestamp", columnList = "bus_id, timestamp DESC")
//    }
)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BusLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long locationId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "bus_id", nullable = false)
    private Bus bus;

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

    @DecimalMin(value = "0.0", message = "Speed cannot be negative")
    @DecimalMax(value = "120.0", message = "Speed cannot exceed 120 km/h")
    @Digits(integer = 3, fraction = 2, message = "Speed precision: 3 digits before, 2 after decimal")
    @Column(precision = 5, scale = 2)
    private BigDecimal speed;

    @DecimalMin(value = "0.0", message = "Heading must be between 0 and 360")
    @DecimalMax(value = "360.0", message = "Heading must be between 0 and 360")
    @Digits(integer = 3, fraction = 2, message = "Heading precision: 3 digits before, 2 after decimal")
    @Column(precision = 5, scale = 2)
    private BigDecimal heading;

    @NotNull(message = "Timestamp is required")
    @Column(name = "timestamp", nullable = false)
    private LocalDateTime timestamp;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

//    /**
//     * Get coordinates as string
//     */
//    public String getCoordinates() {
//        return String.format("%.8f, %.8f", latitude, longitude);
//    }
//
//    /**
//     * Get bus registration number (convenience method)
//     */
//    public String getBusRegistrationNumber() {
//        return bus != null ? bus.getRegistrationNumber() : null;
//    }
//
//    /**
//     * Check if bus is moving
//     */
//    public boolean isMoving() {
//        return speed != null && speed.compareTo(BigDecimal.valueOf(0.5)) > 0;
//    }
//
//    /**
//     * Check if bus is stopped
//     */
//    public boolean isStopped() {
//        return speed == null || speed.compareTo(BigDecimal.valueOf(0.5)) <= 0;
//    }
//
//    /**
//     * Check if location is within Greater Accra bounds
//     * Accra bounds: Lat 5.50-5.70, Lng -0.30 to -0.10
//     */
//    public boolean isWithinAccraBounds() {
//        double lat = latitude.doubleValue();
//        double lng = longitude.doubleValue();
//
//        return lat >= 5.50 && lat <= 5.70 &&
//               lng >= -0.30 && lng <= -0.10;
//    }
//
//    /**
//     * Get compass direction from heading
//     */
//    public String getCompassDirection() {
//        if (heading == null) {
//            return "N/A";
//        }
//
//        double h = heading.doubleValue();
//
//        if (h >= 337.5 || h < 22.5) return "N";
//        if (h >= 22.5 && h < 67.5) return "NE";
//        if (h >= 67.5 && h < 112.5) return "E";
//        if (h >= 112.5 && h < 157.5) return "SE";
//        if (h >= 157.5 && h < 202.5) return "S";
//        if (h >= 202.5 && h < 247.5) return "SW";
//        if (h >= 247.5 && h < 292.5) return "W";
//        if (h >= 292.5 && h < 337.5) return "NW";
//
//        return "N/A";
//    }
//
//    /**
//     * Get speed in km/h formatted
//     */
//    public String getFormattedSpeed() {
//        if (speed == null) {
//            return "0 km/h";
//        }
//        return String.format("%.1f km/h", speed);
//    }
//
//    /**
//     * Calculate distance to another location using Haversine formula
//     * @param other The other location
//     * @return Distance in kilometers
//     */
//    public double calculateDistanceTo(BusLocation other) {
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
//
//    /**
//     * Validate timestamp is not in the future
//     */
//    @PrePersist
//    @PreUpdate
//    private void validateTimestamp() {
//        if (timestamp != null && timestamp.isAfter(LocalDateTime.now())) {
//            throw new IllegalArgumentException("Location timestamp cannot be in the future");
//        }
//    }
}
