package com.epicode.GreenTravelPlanner.entities;

import jakarta.persistence.*;

/**
 * Entity representing specific settings or preferences for a user.
 * This allows for a flexible key-value storage system to customize
 * the user experience (e.g., sustainability settings, notifications).
 */
@Entity
@Table(name = "user_preferences")
public class UserPreference {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String preferenceKey;   // e.g., "EcoFriendly"
    private String preferenceValue; // e.g., "True"

    /**
     * The user to whom these preferences belong.
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // --- Getters and Setters ---

    public Long getId() {
        return id;
    }

    public String getPreferenceKey() {
        return preferenceKey;
    }

    public void setPreferenceKey(String key) {
        this.preferenceKey = key;
    }

    public String getPreferenceValue() {
        return preferenceValue;
    }

    public void setPreferenceValue(String preferenceValue) {
        this.preferenceValue = preferenceValue;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}