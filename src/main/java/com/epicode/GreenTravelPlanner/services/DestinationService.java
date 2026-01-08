package com.epicode.GreenTravelPlanner.services;

import com.epicode.GreenTravelPlanner.entities.Destination;
import com.epicode.GreenTravelPlanner.entities.Trip;
import com.epicode.GreenTravelPlanner.repositories.DestinationRepository;
import com.epicode.GreenTravelPlanner.repositories.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service responsible for managing trip destinations (waypoints).
 * It handles the association between specific locations and their parent trips.
 */
@Service
public class DestinationService {

    @Autowired
    private DestinationRepository destinationRepo;

    @Autowired
    private TripService tripService;

    /**
     * Adds and saves a new destination to an existing trip.
     * * @param tripId the unique identifier of the trip
     * @param newDestination the destination entity to be saved
     * @return the persisted Destination entity
     */
    public Destination saveDestination(Long tripId, Destination newDestination) {
        // 1. Retrieve the trip to which the destination will be added
        // Using tripService.findById ensures consistent error handling (e.g., 404 if not found)
        Trip trip = tripService.findById(tripId);

        // 2. Establish the bidirectional relationship link
        newDestination.setTrip(trip);

        // 3. Persist the destination to the database
        return destinationRepo.save(newDestination);
    }
}