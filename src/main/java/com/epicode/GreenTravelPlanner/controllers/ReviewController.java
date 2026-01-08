package com.epicode.GreenTravelPlanner.controllers;

import com.epicode.GreenTravelPlanner.entities.Review;
import com.epicode.GreenTravelPlanner.entities.Trip;
import com.epicode.GreenTravelPlanner.entities.User;
import com.epicode.GreenTravelPlanner.repositories.ReviewRepository;
import com.epicode.GreenTravelPlanner.services.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

/**
 * REST Controller for managing trip reviews.
 * This controller handles the creation of reviews and associates them with
 * specific trips and the currently authenticated user.
 */
@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewRepository reviewRepo;

    @Autowired
    private TripService tripService;

    /**
     * Creates a new review for a specific trip.
     * * @param tripId      the ID of the trip being reviewed
     * @param body        the review details provided in the request body
     * @param currentUser the currently authenticated user, injected via Spring Security
     * @return the persisted Review entity
     */
    @PostMapping("/trip/{tripId}")
    public Review addReview(
            @PathVariable Long tripId,
            @RequestBody Review body,
            @AuthenticationPrincipal User currentUser) {

        // Fetch the trip entity to associate it with the review
        Trip trip = tripService.findById(tripId);

        body.setTrip(trip);

        // The author is automatically set to the currently logged-in user
        body.setAuthor(currentUser);

        return reviewRepo.save(body);
    }
}