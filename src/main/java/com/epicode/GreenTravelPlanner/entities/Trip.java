package com.epicode.GreenTravelPlanner.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

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

    @Transient
    private String status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties({"password", "trips", "reviews", "roles", "authorities"})
    private User owner;

    @OneToMany(mappedBy = "trip")
    private List<Destination> destinations;

    // --- GETTER E SETTER ---

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

    public String getStatus() {
        if (startDate == null || endDate == null) return "NON_PIANIFICATO";
        LocalDate today = LocalDate.now();
        if (today.isBefore(startDate)) return "IN_PROGRAMMA";
        else if (today.isAfter(endDate)) return "COMPLETATO";
        else return "IN_CORSO";
    }

    public String getFormattedBudget() {
        if (budget == null) return "0 DKK";
        return String.format("%,.0f DKK", budget);
    }
}