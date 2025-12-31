package com.epicode.GreenTravelPlanner.repositories;

import com.epicode.GreenTravelPlanner.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

// JpaRepository fornisce già i metodi base (salva, elimina, cerca) [cite: 29]
public interface UserRepository extends JpaRepository<User, Long> {
    // Ci servirà per il login: cerca un utente tramite l'email
    Optional<User> findByEmail(String email);
}