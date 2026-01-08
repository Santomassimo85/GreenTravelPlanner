package com.epicode.GreenTravelPlanner.controllers;


import com.epicode.GreenTravelPlanner.entities.Trip;
import com.epicode.GreenTravelPlanner.entities.User;
import com.epicode.GreenTravelPlanner.services.CountryService;
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

    @Autowired private CountryService countryService; // Aggiungi questo


    @GetMapping("/{id}")
    public Trip getTrip(@PathVariable Long id) {
        return tripService.findById(id);
    }

    @PostMapping
    public Trip createTrip(@RequestBody Trip body, @AuthenticationPrincipal User currentUser) {
        // ESEMPIO DI INTERAZIONE CON API ESTERNA
        String capitale = countryService.getCapital(body.getDestination());
        System.out.println("API ESTERNA - La capitale di " + body.getDestination() + " è: " + capitale);

        // Magari aggiungiamolo alla descrizione automaticamente!
        String vecchiaDesc = body.getDescription();
        body.setDescription(vecchiaDesc + " (Capitale: " + capitale + ")");

        return tripService.saveTrip(body, currentUser);
    }


}