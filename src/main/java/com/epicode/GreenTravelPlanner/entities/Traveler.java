package com.epicode.GreenTravelPlanner.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "travelers")
public class Traveler extends User {
    private String bio;

    public String getBio() { return bio; }
    public void setBio(String bio) { this.bio = bio; }
}