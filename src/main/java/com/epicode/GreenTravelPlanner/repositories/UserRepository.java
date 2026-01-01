package com.epicode.GreenTravelPlanner.repositories;

import com.epicode.GreenTravelPlanner.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

// JpaRepository fornisce già i metodi base (salva, elimina, cerca) [cite: 29]
public interface UserRepository extends JpaRepository<User, Long> {
    // Spring genererà automaticamente la logica grazie a questo nome
    Optional<User> findByEmail(String email);
}