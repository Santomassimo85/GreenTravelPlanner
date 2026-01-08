package com.epicode.GreenTravelPlanner.security;

import com.epicode.GreenTravelPlanner.entities.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Utility component for handling JSON Web Tokens (JWT).
 * This class provides methods to generate, validate, and parse tokens
 * using the HS256 algorithm and a secret key.
 */
@Component
public class JWTTools {

    @Value("${jwt.secret}")
    private String secret;

    /**
     * Generates a JWT for a specific user.
     * The token contains the user ID as the subject and is valid for 24 hours.
     * * @param user the authenticated user for whom the token is generated
     * @return a signed JWT string
     */
    public String createToken(User user) {
        // Set issue date, 24-hour expiration, and the user ID as the subject
        return Jwts.builder()
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)) // 24 hours
                .setSubject(String.valueOf(user.getId()))
                .signWith(Keys.hmacShaKeyFor(secret.getBytes()), SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Validates the integrity and expiration of a provided token.
     * * @param token the JWT string to verify
     * @throws RuntimeException if the token is invalid, tampered with, or expired
     */
    public void verifyToken(String token) {
        try {
            // Reconstruct the parser with the secret key and validate the signature
            Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(secret.getBytes()))
                    .build()
                    .parseClaimsJws(token);
        } catch (Exception ex) {
            throw new RuntimeException("Token is invalid or expired!");
        }
    }

    /**
     * Extracts the user ID (Subject) from the claims of a valid token.
     * * @param token the JWT string
     * @return the user ID stored in the token's subject field
     */
    public String extractIdFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(secret.getBytes()))
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}