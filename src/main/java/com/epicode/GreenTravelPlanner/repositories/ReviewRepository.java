package com.epicode.GreenTravelPlanner.repositories;

import com.epicode.GreenTravelPlanner.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

// Search for all reviews related to a specific trip
    List<Review> findByTripId(Long tripId);

    // Search for all reviews written by a specific user
    List<Review> findByAuthorId(Long userId);
}