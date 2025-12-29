package com.epicode.GreenTravelPlanner.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "user_preferences")
public class UserPreference {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String preferenceKey; // Es: "EcoFriendly"
    private String preferenceValue; // Es: "True"

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Getter e Setter
    public String getPreferenceKey() { return preferenceKey; }
    public void setPreferenceKey(String key) { this.preferenceKey = key; }
}