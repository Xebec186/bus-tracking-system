//package com.xebec.BusTracking.model;
//
//import jakarta.persistence.*;
//import jakarta.validation.constraints.*;
//import lombok.*;
//import org.hibernate.annotations.CreationTimestamp;
//
//import java.time.LocalDateTime;
//
///**
// * JPA Entity for Audit_Logs table
// * Represents administrative action tracking for compliance and security
// */
//@Entity
//@Table(name = "audit_logs",
//    indexes = {
//        @Index(name = "idx_audit_search",
//               columnList = "user_id, timestamp DESC")
//    }
//)
//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//public class AuditLog {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "log_id")
//    private Long logId;
//
//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "user_id", nullable = false)
//    @ToString.Exclude
//    @EqualsAndHashCode.Exclude
//    private User user;
//
//    @NotBlank(message = "Action type is required")
//    @Column(name = "action_type", nullable = false, length = 50)
//    private String actionType;
//
//    @Column(name = "entity_type", length = 50)
//    private String entityType;
//
//    @Column(name = "entity_id")
//    private Long entityId;
//
//    @Column(name = "action_details", columnDefinition = "TEXT")
//    private String actionDetails;
//
//    @Size(max = 45, message = "IP address cannot exceed 45 characters")
//    @Column(name = "ip_address", length = 45)
//    private String ipAddress;
//
//    @CreationTimestamp
//    @Column(name = "timestamp", nullable = false, updatable = false)
//    private LocalDateTime timestamp;
//
//    // Action type constants
//    public static final String ACTION_CREATE = "CREATE";
//    public static final String ACTION_UPDATE = "UPDATE";
//    public static final String ACTION_DELETE = "DELETE";
//    public static final String ACTION_LOGIN = "LOGIN";
//    public static final String ACTION_LOGOUT = "LOGOUT";
//    public static final String ACTION_LOGIN_FAILED = "LOGIN_FAILED";
//    public static final String ACTION_LOCK_ACCOUNT = "LOCK_ACCOUNT";
//    public static final String ACTION_UNLOCK_ACCOUNT = "UNLOCK_ACCOUNT";
//    public static final String ACTION_CHANGE_ROLE = "CHANGE_ROLE";
//    public static final String ACTION_VIEW = "VIEW";
//    public static final String ACTION_EXPORT = "EXPORT";
//
//    // Entity type constants
//    public static final String ENTITY_BUS = "BUS";
//    public static final String ENTITY_ROUTE = "ROUTE";
//    public static final String ENTITY_SCHEDULE = "SCHEDULE";
//    public static final String ENTITY_USER = "USER";
//    public static final String ENTITY_STOP = "STOP";
//    public static final String ENTITY_TICKET = "TICKET";
//    public static final String ENTITY_SYSTEM = "SYSTEM";
//
//    /**
//     * Get username (convenience method)
//     */
//    public String getUsername() {
//        return user != null ? user.getUsername() : null;
//    }
//
//    /**
//     * Get user full name (convenience method)
//     */
//    public String getUserFullName() {
//        return user != null ? user.getFullName() : null;
//    }
//
//    /**
//     * Check if this is a create action
//     */
//    public boolean isCreateAction() {
//        return ACTION_CREATE.equals(this.actionType);
//    }
//
//    /**
//     * Check if this is an update action
//     */
//    public boolean isUpdateAction() {
//        return ACTION_UPDATE.equals(this.actionType);
//    }
//
//    /**
//     * Check if this is a delete action
//     */
//    public boolean isDeleteAction() {
//        return ACTION_DELETE.equals(this.actionType);
//    }
//
//    /**
//     * Check if this is a login action
//     */
//    public boolean isLoginAction() {
//        return ACTION_LOGIN.equals(this.actionType) ||
//               ACTION_LOGIN_FAILED.equals(this.actionType);
//    }
//
//    /**
//     * Check if this is a security-related action
//     */
//    public boolean isSecurityAction() {
//        return ACTION_LOGIN.equals(this.actionType) ||
//               ACTION_LOGOUT.equals(this.actionType) ||
//               ACTION_LOGIN_FAILED.equals(this.actionType) ||
//               ACTION_LOCK_ACCOUNT.equals(this.actionType) ||
//               ACTION_UNLOCK_ACCOUNT.equals(this.actionType) ||
//               ACTION_CHANGE_ROLE.equals(this.actionType);
//    }
//
//    /**
//     * Get formatted log entry
//     */
//    public String getFormattedLogEntry() {
//        StringBuilder sb = new StringBuilder();
//        sb.append("[").append(timestamp).append("] ");
//        sb.append(getUsername()).append(" - ");
//        sb.append(actionType);
//
//        if (entityType != null) {
//            sb.append(" ").append(entityType);
//        }
//
//        if (entityId != null) {
//            sb.append(" #").append(entityId);
//        }
//
//        if (ipAddress != null) {
//            sb.append(" from ").append(ipAddress);
//        }
//
//        return sb.toString();
//    }
//
//    /**
//     * Get short description
//     */
//    public String getShortDescription() {
//        if (entityType != null && entityId != null) {
//            return String.format("%s %s #%d", actionType, entityType, entityId);
//        } else if (entityType != null) {
//            return String.format("%s %s", actionType, entityType);
//        } else {
//            return actionType;
//        }
//    }
//
//    /**
//     * Static factory methods for common audit log entries
//     */
//    public static AuditLog createLoginLog(User user, String ipAddress, boolean success) {
//        return AuditLog.builder()
//            .user(user)
//            .actionType(success ? ACTION_LOGIN : ACTION_LOGIN_FAILED)
//            .entityType(ENTITY_SYSTEM)
//            .ipAddress(ipAddress)
//            .actionDetails(success ? "Successful login" : "Failed login attempt")
//            .build();
//    }
//
//    public static AuditLog createLogoutLog(User user, String ipAddress) {
//        return AuditLog.builder()
//            .user(user)
//            .actionType(ACTION_LOGOUT)
//            .entityType(ENTITY_SYSTEM)
//            .ipAddress(ipAddress)
//            .actionDetails("User logged out")
//            .build();
//    }
//
//    public static AuditLog createEntityLog(User user, String actionType,
//                                          String entityType, Long entityId,
//                                          String details, String ipAddress) {
//        return AuditLog.builder()
//            .user(user)
//            .actionType(actionType)
//            .entityType(entityType)
//            .entityId(entityId)
//            .actionDetails(details)
//            .ipAddress(ipAddress)
//            .build();
//    }
//
//    /**
//     * Prevent modification of audit logs
//     */
//    @PreUpdate
//    private void preventUpdate() {
//        throw new UnsupportedOperationException(
//            "Audit logs are immutable and cannot be updated"
//        );
//    }
//}
