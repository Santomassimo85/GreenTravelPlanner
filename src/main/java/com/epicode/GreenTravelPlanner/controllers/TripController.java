package com.epicode.GreenTravelPlanner.controllers;


import com.epicode.GreenTravelPlanner.entities.Trip;
import com.epicode.GreenTravelPlanner.entities.User;
import com.epicode.GreenTravelPlanner.services.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trips")
public class TripController {

    @Autowired
    private TripService tripService;

    @GetMapping
    public List<Trip> getTrips() {
        return tripService.getAll();
    }

    @PostMapping
    public Trip createTrip(@RequestBody Trip body, @AuthenticationPrincipal User currentUser) {
        // @AuthenticationPrincipal prende automaticamente l'utente dal JWT Token
        return tripService.saveTrip(body, currentUser);
    }
}