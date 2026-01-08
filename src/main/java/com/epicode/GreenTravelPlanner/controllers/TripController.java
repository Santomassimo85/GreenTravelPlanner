package com.epicode.GreenTravelPlanner.controllers;

import com.epicode.GreenTravelPlanner.entities.Trip;
import com.epicode.GreenTravelPlanner.entities.User;
import com.epicode.GreenTravelPlanner.services.CountryService;
import com.epicode.GreenTravelPlanner.services.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller for managing travel trips.
 * This controller handles trip retrieval and creation, including integration
 * with external geographic data services.
 */
@RestController
@RequestMapping("/trips")
public class TripController {

    @Autowired
    private TripService tripService;

    @Autowired
    private CountryService countryService;

    /**
     * Retrieves a list of all available trips.
     * * @return a list of Trip entities
     */
    @GetMapping
    public List<Trip> getTrips() {
        return tripService.getAll();
    }

    /**
     * Retrieves a specific trip by its unique identifier.
     * * @param id the ID of the trip to find
     * @return the found Trip entity
     */
    @GetMapping("/{id}")
    public Trip getTrip(@PathVariable Long id) {
        return tripService.findById(id);
    }

    /**
     * Creates a new trip and enriches it with data from an external API.
     * * @param body        the trip details provided by the user
     * @param currentUser the currently authenticated user creating the trip
     * @return the saved Trip entity with enriched description
     */
    @PostMapping
    public Trip createTrip(@RequestBody Trip body, @AuthenticationPrincipal User currentUser) {
        // EXTERNAL API INTERACTION EXAMPLE
        // Fetch the capital city based on the trip's destination country
        String capital = countryService.getCapital(body.getDestination());
        System.out.println("EXTERNAL API - The capital of " + body.getDestination() + " is: " + capital);

        // Automatically append the capital information to the trip description
        String originalDescription = body.getDescription();
        body.setDescription(originalDescription + " (Capital: " + capital + ")");

        return tripService.saveTrip(body, currentUser);
    }
}