package com.epicode.GreenTravelPlanner.repositories;

import com.epicode.GreenTravelPlanner.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for managing Review entities.
 * Inherits standard CRUD operations from JpaRepository and includes custom
 * finder methods for filtering reviews by trips or authors.
 */
@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    /**
     * Retrieves all reviews associated with a specific trip.
     * * @param tripId the unique identifier of the trip
     * @return a list of Review entities linked to the trip
     */
    List<Review> findByTripId(Long tripId);

    /**
     * Retrieves all reviews written by a specific user.
     * * @param userId the unique identifier of the user (author)
     * @return a list of Review entities authored by the user
     */
    List<Review> findByAuthorId(Long userId);
}