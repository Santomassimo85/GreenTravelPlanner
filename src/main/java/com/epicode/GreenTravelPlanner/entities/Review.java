package com.epicode.GreenTravelPlanner.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int rating;
    private String text;

    // --- NUOVO CAMPO ---
    private String comment;
    // -------------------

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User author;

    @ManyToOne
    @JoinColumn(name = "trip_id")
    private Trip trip;

    // --- GETTER E SETTER (Non dimenticarli!) ---

    public Long getId() { return id; }

    public int getRating() { return rating; }
    public void setRating(int rating) { this.rating = rating; }

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }

    // Getter e Setter per il commento
    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }

    public User getAuthor() { return author; }
    public void setAuthor(User author) { this.author = author; }

    public Trip getTrip() { return trip; }
    public void setTrip(Trip trip) { this.trip = trip; }
}