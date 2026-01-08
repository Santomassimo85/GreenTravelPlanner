package com.epicode.GreenTravelPlanner.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * General configuration class for the application.
 * It defines global beans that are injected and used across the system,
 * such as utility for HTTP requests and password security.
 */
@Configuration
public class AppConfig {

    /**
     * Creates and exposes a RestTemplate bean.
     * This client is used to perform synchronous HTTP requests to external APIs
     * (e.g., fetching country data from RestCountries).
     *
     * @return A new instance of RestTemplate.
     */
    @Bean
    public RestTemplate restTemplate() {
        // Returns a standard RestTemplate instance to handle external API calls
        return new RestTemplate();
    }

    /**
     * Creates and exposes a PasswordEncoder bean using the BCrypt hashing algorithm.
     * This component is essential for security: it hashes passwords before saving them
     * to the database and verifies them during the login process.
     *
     * @return An instance of BCryptPasswordEncoder.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        // Uses BCrypt, a strong hashing function designed for password storage
        return new BCryptPasswordEncoder();
    }

}