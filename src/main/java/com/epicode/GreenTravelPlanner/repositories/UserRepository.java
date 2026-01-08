package com.epicode.GreenTravelPlanner.repositories;

import com.epicode.GreenTravelPlanner.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

/**
 * Repository interface for User entities.
 * Inherits standard CRUD functionality from JpaRepository and defines
 * custom query methods for authentication and user management.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Finds a user by their unique email address.
     * Spring Data JPA automatically derives the SQL query from the method name.
     * * @param email the email address to search for
     * @return an Optional containing the found User, or empty if not found
     */
    Optional<User> findByEmail(String email);
}