package com.epicode.GreenTravelPlanner.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

/**
 * Entity representing a travel trip.
 * This class stores core trip information such as destination, budget, and dates.
 * It also calculates the real-time status of the trip based on the current date.
 */
@Entity
@Table(name = "trips")
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String destination;
    private String description;
    private Double budget;
    private LocalDate startDate;
    private LocalDate endDate;

    /**
     * Calculated field that is not persisted in the database.
     */
    @Transient
    private String status;

    /**
     * The user who created/owns the trip.
     * Sensitive user information is excluded during JSON serialization.
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties({"password", "trips", "reviews", "roles", "authorities"})
    private User owner;

    /**
     * List of specific destinations or stops planned for this trip.
     */
    @OneToMany(mappedBy = "trip")
    private List<Destination> destinations;

    // --- Getters and Setters ---

    public Long getId() { return id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Double getBudget() { return budget; }
    public void setBudget(Double budget) { this.budget = budget; }

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }

    public User getOwner() { return owner; }
    public void setOwner(User owner) { this.owner = owner; }

    /**
     * Determines the trip status based on current date comparison.
     * @return A string indicating if the trip is PLANNED, COMPLETED, IN_PROGRESS, or NOT_SCHEDULED.
     */
    public String getStatus() {
        if (startDate == null || endDate == null) return "NOT_SCHEDULED";
        LocalDate today = LocalDate.now();
        if (today.isBefore(startDate)) return "PLANNED";
        else if (today.isAfter(endDate)) return "COMPLETED";
        else return "IN_PROGRESS";
    }

    /**
     * Formats the budget value for display.
     * @return Formatted currency string in DKK.
     */
    public String getFormattedBudget() {
        if (budget == null) return "0 DKK";
        return String.format("%,.0f DKK", budget);
    }
}