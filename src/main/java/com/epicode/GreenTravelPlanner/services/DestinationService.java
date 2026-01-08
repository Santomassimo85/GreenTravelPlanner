package com.epicode.GreenTravelPlanner.services;

import com.epicode.GreenTravelPlanner.entities.Destination;
import com.epicode.GreenTravelPlanner.entities.Trip;
import com.epicode.GreenTravelPlanner.repositories.DestinationRepository; // Crealo se manca!
import com.epicode.GreenTravelPlanner.repositories.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DestinationService {
    @Autowired private DestinationRepository destinationRepo;
    @Autowired private TripService tripService; // Usiamo il service dei viaggi

    public Destination saveDestination(Long tripId, Destination newDestination) {
        // 1. Cerchiamo il viaggio a cui aggiungere la tappa
        Trip trip = tripService.findById(tripId);

        // 2. Colleghiamo la tappa al viaggio
        newDestination.setTrip(trip);

        // 3. Salviamo
        return destinationRepo.save(newDestination);
    }
}