package com.epicode.GreenTravelPlanner.entities;

import jakarta.persistence.*;

/**
 * Entity representing a review for a trip.
 * This class stores the rating, feedback text, and additional comments
 * provided by a user regarding a specific travel experience.
 */
@Entity
@Table(name = "reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int rating;
    private String text;
    private String comment;

    /**
     * The user who wrote the review.
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User author;

    /**
     * The trip being reviewed.
     */
    @ManyToOne
    @JoinColumn(name = "trip_id")
    private Trip trip;

    // --- Getters and Setters ---

    public Long getId() {
        return id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }
}