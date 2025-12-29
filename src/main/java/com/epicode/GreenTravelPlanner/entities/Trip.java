package com.epicode.GreenTravelPlanner.entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "trips")
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ManyToOne // Molti viaggi appartengono a un solo utente
    @JoinColumn(name = "user_id")
    private User owner;

    @OneToMany(mappedBy = "trip") // Un viaggio ha molte tappe/destinazioni
    private List<Destination> destinations;

    // Getter e Setter
    public Long getId() { return id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public User getOwner() { return owner; }
    public void setOwner(User owner) { this.owner = owner; }
}