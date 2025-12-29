package com.epicode.GreenTravelPlanner.entities;

@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String comment;
    private int rating; // Da 1 a 5

    @ManyToOne
    private User author;
}
