package com.epicode.GreenTravelPlanner.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Configuration class for password encryption.
 * This class provides a centralized bean for encoding and verifying passwords
 * across the application.
 */
@Configuration
public class PasswordConfig {

    /**
     * Defines the PasswordEncoder bean used by Spring Security.
     * Uses the BCrypt hashing algorithm, which is a strong, one-way hashing function
     * that includes a salt to protect against rainbow table attacks.
     *
     * @return an instance of BCryptPasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        // Return a new instance of BCryptPasswordEncoder for secure password management
        return new BCryptPasswordEncoder();
    }
}