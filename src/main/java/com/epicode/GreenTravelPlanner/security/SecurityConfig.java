package com.epicode.GreenTravelPlanner.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Main security configuration class for the application.
 * This class configures the filter chain, defines public and private routes,
 * and sets the session management to stateless for JWT-based authentication.
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private JWTFilter jwtFilter;

    /**
     * Configures the HTTP security filter chain.
     * * @param http the HttpSecurity object to configure
     * @return the built SecurityFilterChain
     * @throws Exception if an error occurs during configuration
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Disable CSRF as it is not needed for stateless REST APIs
        http.csrf(csrf -> csrf.disable())
                // Configure session management to not store user state in memory
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // Define endpoint authorization rules
                .authorizeHttpRequests(auth -> auth
                        // Allow all requests to authentication endpoints (login/register)
                        .requestMatchers("/auth/**").permitAll()
                        // Restrict access to administrative endpoints
                        .requestMatchers("/admin/**").hasAuthority("ADMIN")
                        // Require authentication for any other request
                        .anyRequest().authenticated()
                );

        // Insert the custom JWT filter before the standard UsernamePasswordAuthenticationFilter
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}