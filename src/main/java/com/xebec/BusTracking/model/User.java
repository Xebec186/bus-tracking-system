package com.xebec.BusTracking.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

/**
 * JPA Entity for Users table
 * Represents system users: Administrators, Drivers, and Passengers
 */
@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    @Column(name = "username", unique = true, nullable = false, length = 50)
    private String username;

    @NotBlank(message = "Password is required")
    @Column(name = "password_hash", nullable = false, length = 100)
    private String passwordHash;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    @Column(name = "email", unique = true, nullable = false, length = 100)
    private String email;

    @NotBlank(message = "First name is required")
    @Size(max = 100, message = "First name cannot exceed 100 characters")
    @Column(name = "first_name", nullable = false, length = 100)
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(max = 100, message = "First name cannot exceed 100 characters")
    @Column(name = "last_name", nullable = false, length = 100)
    private String lastName;

    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be exactly 10 digits")
    @Column(length = 10)
    private String phoneNumber;

    @NotNull(message = "Role is required")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private UserRole role;

//    @Enumerated(EnumType.STRING)
//    @Column(name = "account_status", length = 20)
//    private AccountStatus accountStatus = AccountStatus.ACTIVE;

    @Min(value = 0, message = "Failed login attempts cannot be negative")
    @Column(name = "failed_login_attempts")
    private Integer failedLoginAttempts = 0;

    @Column(name = "last_login")
    private LocalDateTime lastLogin;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }


//    public enum AccountStatus {
//        ACTIVE,
//        LOCKED,
//        DISABLED
//    }

//    /**
//     * Check if a user account is active
//     */
//    public boolean isActive() {
//        return AccountStatus.ACTIVE.equals(this.accountStatus);
//    }

//    /**
//     * Check if user is an administrator
//     */
//    public boolean isAdmin() {
//        return UserRole.ADMIN.equals(this.role);
//    }
//
//    /**
//     * Check if a user is a driver
//     */
//    public boolean isDriver() {
//        return UserRole.DRIVER.equals(this.role);
//    }
//
//    /**
//     * Check if user is a passenger
//     */
//    public boolean isPassenger() {
//        return UserRole.PASSENGER.equals(this.role);
//    }

//
//    /**
//     * Increment failed login attempts
//     */
//    public void incrementFailedAttempts() {
//        this.failedLoginAttempts++;
//    }
//
//    /**
//     * Reset failed login attempts
//     */
//    public void resetFailedAttempts() {
//        this.failedLoginAttempts = 0;
//    }
//
//    /**
//     * Lock the account
//     */
//    public void lockAccount() {
//        this.accountStatus = AccountStatus.LOCKED;
//    }
//
//    /**
//     * Unlock the account
//     */
//    public void unlockAccount() {
//        this.accountStatus = AccountStatus.ACTIVE;
//        this.failedLoginAttempts = 0;
//    }
}
