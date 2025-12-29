package com.epicode.GreenTravelPlanner.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "destinations")
public class Destination {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cityName;

    @ManyToOne
    @JoinColumn(name = "trip_id")
    private Trip trip;

    // Getter e Setter
    public String getCityName() { return cityName; }
    public void setCityName(String cityName) { this.cityName = cityName; }
    public void setTrip(Trip trip) { this.trip = trip; }
}