package com.epicode.GreenTravelPlanner.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "destinations")
public class Destination {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;        // Es. "Caracas"
    private String activity;    // Es. "Visita al museo"
    private LocalDate date;     // Quando ci vai

    @ManyToOne
    @JoinColumn(name = "trip_id")
    @JsonIgnore // Evita loop infiniti quando stampiamo il JSON
    private Trip trip;

    // --- Getter e Setter ---
    public Long getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getActivity() { return activity; }
    public void setActivity(String activity) { this.activity = activity; }
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
    public Trip getTrip() { return trip; }
    public void setTrip(Trip trip) { this.trip = trip; }
}