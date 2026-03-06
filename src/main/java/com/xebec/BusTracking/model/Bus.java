package com.xebec.BusTracking.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

/**
 * JPA Entity for Buses table
 * Represents the bus fleet
 */
@Entity
@Table(name = "buses")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Bus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @NotBlank(message = "Registration number is required")
//    @Pattern(regexp = "^GH-\\d{4}-\\d{2}$",
//             message = "Registration number must follow format: GH-XXXX-XX")
//    @Column(name = "registration_number", unique = true, nullable = false, length = 20)
//    private String registrationNumber;

    @NotNull(message = "Capacity is required")
    @Min(value = 10, message = "Minimum capacity is 10")
    @Max(value = 100, message = "Maximum capacity is 100")
    @Column(nullable = false)
    private Integer capacity;

    @Size(max = 50, message = "Make cannot exceed 50 characters")
    @Column(length = 50)
    private String make;

    @Size(max = 50, message = "Model cannot exceed 50 characters")
    @Column(length = 50)
    private String model;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "driver_id", referencedColumnName = "user_id")
//    private User driver;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

//    /**
//     * Check if bus is active
//     */
//    public boolean isActive() {
//        return BusStatus.ACTIVE.equals(this.status);
//    }
//
//    /**
//     * Check if bus is in maintenance
//     */
//    public boolean isInMaintenance() {
//        return BusStatus.MAINTENANCE.equals(this.status);
//    }
//
//    /**
//     * Check if bus has assigned driver
//     */
//    public boolean hasDriver() {
//        return this.driver != null;
//    }
//
//    /**
//     * Activate the bus
//     */
//    public void activate() {
//        this.status = BusStatus.ACTIVE;
//    }
//
//    /**
//     * Deactivate the bus
//     */
//    public void deactivate() {
//        this.status = BusStatus.INACTIVE;
//    }
//
//    /**
//     * Set bus to maintenance mode
//     */
//    public void setMaintenance() {
//        this.status = BusStatus.MAINTENANCE;
//    }
//
//    /**
//     * Assign driver to bus
//     */
//    public void assignDriver(User driver) {
//        if (driver != null && driver.isDriver()) {
//            this.driver = driver;
//        } else {
//            throw new IllegalArgumentException("User must have DRIVER role");
//        }
//    }
//
//    /**
//     * Remove driver assignment
//     */
//    public void removeDriver() {
//        this.driver = null;
//    }
}
