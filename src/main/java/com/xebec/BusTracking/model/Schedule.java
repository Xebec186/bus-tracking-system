package com.xebec.BusTracking.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.*;
import java.util.Set;

/**
 * JPA Entity for Schedules table.
 * Represents bus-route assignments with timing
 */
@Entity
@Table(name = "schedules"
//    indexes = {
//        @Index(name = "idx_active_schedules",
//               columnList = "status, effective_date, expiry_date")
//    }
)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "bus_id", nullable = false)
    private Bus bus;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "route_id", nullable = false)
    private Route route;

    @Column(nullable = false)
    private LocalTime departureTime;

    @Column(nullable = false)
    private LocalTime arrivalTime;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "schedule_days", joinColumns = @JoinColumn(name = "schedule_id"))
    @Column(name = "day_of_week")
    private Set<DayOfWeek> daysOfWeek;

    @Column(nullable = false)
    private LocalDate effectiveDate;

    private LocalDate expiryDate;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ScheduleStatus status = ScheduleStatus.ACTIVE;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }


    /**
     * Check if the schedule is active
     */
    public boolean isActive() {
        return ScheduleStatus.ACTIVE.equals(this.status);
    }

    /**
     * Check if the schedule is currently valid based on effective and expiry dates
     */
    public boolean isCurrentlyValid() {
        LocalDate today = LocalDate.now();
        return isActive() &&
               !today.isBefore(effectiveDate) &&
               (expiryDate == null || !today.isAfter(expiryDate));
    }

    /**
     * Check if the schedule is expired
     */
    public boolean isExpired() {
        if (expiryDate == null) {
            return false;
        }
        return LocalDate.now().isAfter(expiryDate);
    }

    /**
     * Get schedule duration in minutes
     */
    public long getDurationMinutes() {
        if (departureTime == null || arrivalTime == null) {
            return 0;
        }

        Duration duration;
        if (arrivalTime.isBefore(departureTime)) {
            // Next day arrival
            duration = Duration.between(departureTime, LocalTime.MAX)
                     .plus(Duration.between(LocalTime.MIN, arrivalTime))
                     .plusMinutes(1);
        } else {
            duration = Duration.between(departureTime, arrivalTime);
        }

        return duration.toMinutes();
    }

    /**
     * Get formatted duration
     */
    public String getFormattedDuration() {
        long minutes = getDurationMinutes();
        long hours = minutes / 60;
        long mins = minutes % 60;

        if (hours > 0) {
            return String.format("%dh %dm", hours, mins);
        } else {
            return String.format("%dm", mins);
        }
    }

    /**
     * Get bus registration number
     */
    public String getBusRegistrationNumber() {
        return bus != null ? bus.getRegistrationNumber() : null;
    }

    /**
     * Get route number
     */
    public String getRouteNumber() {
        return route != null ? route.getNumber() : null;
    }

    /**
     * Activate the schedule
     */
    public void activate() {
        this.status = ScheduleStatus.ACTIVE;
    }

    /**
     * Deactivate the schedule
     */
    public void deactivate() {
        this.status = ScheduleStatus.INACTIVE;
    }

    /**
     * Mark schedule as expired
     */
    public void expire() {
        this.status = ScheduleStatus.EXPIRED;
    }

    /**
     * Validate duration matches route's estimated duration (within tolerance)
     */
    @PrePersist
    @PreUpdate
    private void validateDurationMatchesRoute() {
        if (route != null && route.getEstimatedDurationMinutes() != null) {
            long scheduleDuration = getDurationMinutes();
            int routeDuration = route.getEstimatedDurationMinutes();

            // Allow ±30 minutes tolerance
            if (scheduleDuration < routeDuration - 30 ||
                scheduleDuration > routeDuration + 30) {
                throw new IllegalArgumentException(
                    String.format("Schedule duration %d minutes is too different from route duration %d minutes",
                        scheduleDuration, routeDuration)
                );
            }
        }
    }
}
