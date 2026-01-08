package com.epicode.GreenTravelPlanner.repositories;

import com.epicode.GreenTravelPlanner.entities.Destination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DestinationRepository extends JpaRepository<Destination, Long> {

   // This method retrieves all Destination entities associated with a specific Trip ID
    // How it works : Spring Data JPA generates the query based on the method name
    List<Destination> findByTripId(Long tripId);
}