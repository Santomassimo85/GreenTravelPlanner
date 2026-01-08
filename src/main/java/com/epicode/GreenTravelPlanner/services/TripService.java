package com.epicode.GreenTravelPlanner.services;


import com.epicode.GreenTravelPlanner.entities.Trip;
import com.epicode.GreenTravelPlanner.entities.User;
import com.epicode.GreenTravelPlanner.exceptions.NotFoundException;
import com.epicode.GreenTravelPlanner.repositories.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TripService {

    @Autowired
    private TripRepository tripRepository;

    // Metodo finale per salvare un viaggio collegandolo all'utente
    public Trip saveTrip(Trip body, User user) {
        body.setOwner(user); // Collega il viaggio all'utente autenticato
        return tripRepository.save(body);
    }

    public List<Trip> getAll() {
        return tripRepository.findAll();
    }

    public Trip findById(Long id) {
        return tripRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Viaggio non trovato con ID: " + id));
    }
}