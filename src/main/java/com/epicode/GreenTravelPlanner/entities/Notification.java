package com.epicode.GreenTravelPlanner.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Entity representing a system notification sent to a user.
 * It tracks the content of the message, the timestamp of creation,
 * the read status, and the intended recipient.
 */
@Entity
@Table(name = "notifications")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;

    // Automatically sets the creation timestamp to the current time
    private LocalDateTime createdAt = LocalDateTime.now();

    // Default status is unread (false)
    private boolean read = false;

    /**
     * The user who is intended to receive this notification.
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User recipient;

    // --- Getters and Setters ---

    public Long getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public User getRecipient() {
        return recipient;
    }

    public void setRecipient(User recipient) {
        this.recipient = recipient;
    }
}