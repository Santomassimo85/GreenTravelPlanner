package com.epicode.GreenTravelPlanner.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "notifications")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;
    private LocalDateTime createdAt = LocalDateTime.now();
    private boolean read = false;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User recipient;

    // Getter e Setter
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public void setRecipient(User recipient) { this.recipient = recipient; }
}