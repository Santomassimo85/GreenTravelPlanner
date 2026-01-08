package com.epicode.GreenTravelPlanner.services;

import com.epicode.GreenTravelPlanner.entities.Trip;
import com.epicode.GreenTravelPlanner.entities.User;
import com.epicode.GreenTravelPlanner.exceptions.NotFoundException;
import com.epicode.GreenTravelPlanner.repositories.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Service class for managing Trip-related business logic.
 * Handles creation, retrieval, and validation of travel plans.
 */
@Service
public class TripService {

    @Autowired
    private TripRepository tripRepository;

    /**
     * Saves a new trip by associating it with a specific authenticated user.
     * * @param body The trip details provided in the request body.
     * @param user The user who will be the owner of this trip.
     * @return The persisted Trip entity.
     */
    public Trip saveTrip(Trip body, User user) {
        // Link the trip to the authenticated user (owner)
        body.setOwner(user);
        return tripRepository.save(body);
    }

    /**
     * Retrieves all trips available in the system.
     * * @return A list of all Trip entities.
     */
    public List<Trip> getAll() {
        return tripRepository.findAll();
    }

    /**
     * Finds a specific trip by its unique identifier.
     * * @param id The unique ID of the trip.
     * @return The Trip entity if found.
     * @throws NotFoundException if no trip exists with the given ID.
     */
    public Trip findById(Long id) {
        return tripRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Trip not found with ID: " + id));
    }
}