package com.epicode.GreenTravelPlanner.controllers;

import com.epicode.GreenTravelPlanner.entities.Destination;
import com.epicode.GreenTravelPlanner.services.DestinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * REST Controller for managing travel destinations.
 * Provides endpoints to handle the locations and stops associated with user trips.
 */
@RestController
@RequestMapping("/destinations")
public class DestinationController {

    @Autowired
    private DestinationService destinationService;

    /**
     * Adds a new destination stop to a specific trip.
     * * @param tripId the unique identifier of the trip the destination belongs to
     * @param body   the destination details to be saved
     * @return the persisted Destination entity
     */
    @PostMapping("/trip/{tripId}")
    public Destination addDestination(@PathVariable Long tripId, @RequestBody Destination body) {
        // Example URL: /destinations/trip/1 (Adds a stop to the trip with ID 1)
        return destinationService.saveDestination(tripId, body);
    }
}