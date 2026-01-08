package com.epicode.GreenTravelPlanner.repositories;

import com.epicode.GreenTravelPlanner.entities.Destination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for Destination entities.
 * Provides standard CRUD operations via JpaRepository and custom query methods
 * to filter destinations based on their associated trip.
 */
@Repository
public interface DestinationRepository extends JpaRepository<Destination, Long> {

    /**
     * Retrieves a list of all destinations associated with a specific trip.
     * Spring Data JPA automatically generates the SQL query by parsing the method name:
     * "SELECT d FROM Destination d WHERE d.trip.id = ?1"
     * * @param tripId the unique identifier of the trip
     * @return a list of destinations linked to the provided trip ID
     */
    List<Destination> findByTripId(Long tripId);
}