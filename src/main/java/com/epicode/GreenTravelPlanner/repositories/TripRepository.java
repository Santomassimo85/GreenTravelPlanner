package com.epicode.GreenTravelPlanner.repositories;

import com.epicode.GreenTravelPlanner.entities.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface TripRepository extends JpaRepository<Trip, Long> {

    // 1. Query Derivata (automatica): trova i viaggi in base al titolo
    List<Trip> findByTitleContainingIgnoreCase(String title);

    // 2. Query JPQL (Manuale - REQUISITO SLIDE 8):
    // Seleziona i viaggi cercando tramite l'ID dell'utente proprietario
    @Query("SELECT t FROM Trip t WHERE t.owner.id = :userId")
    List<Trip> findAllByUserId(@Param("userId") Long userId);
}