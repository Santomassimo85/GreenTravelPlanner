package com.epicode.GreenTravelPlanner.controllers;

import com.epicode.GreenTravelPlanner.entities.Review;
import com.epicode.GreenTravelPlanner.entities.Trip;
import com.epicode.GreenTravelPlanner.entities.User;
import com.epicode.GreenTravelPlanner.repositories.ReviewRepository;
import com.epicode.GreenTravelPlanner.services.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    @Autowired private ReviewRepository reviewRepo;
    @Autowired private TripService tripService;

    @PostMapping("/trip/{tripId}")
    public Review addReview(
            @PathVariable Long tripId,
            @RequestBody Review body,
            @AuthenticationPrincipal User currentUser) {

        Trip trip = tripService.findById(tripId);
        body.setTrip(trip);
        body.setAuthor(currentUser); // L'autore è chi è loggato!
        return reviewRepo.save(body);
    }
}