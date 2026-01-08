package com.epicode.GreenTravelPlanner.controllers;

import com.epicode.GreenTravelPlanner.entities.Destination;
import com.epicode.GreenTravelPlanner.services.DestinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/destinations")
public class DestinationController {
    @Autowired private DestinationService destinationService;

    // POST: Aggiunge una tappa a un viaggio specifico
    // Esempio URL: /destinations/trip/1 (Aggiunge al viaggio con ID 1)
    @PostMapping("/trip/{tripId}")
    public Destination addDestination(@PathVariable Long tripId, @RequestBody Destination body) {
        return destinationService.saveDestination(tripId, body);
    }
}