package com.epicode.GreenTravelPlanner.services;

import com.epicode.GreenTravelPlanner.entities.User;
import com.epicode.GreenTravelPlanner.exceptions.UnauthorizedException;
import com.epicode.GreenTravelPlanner.payloads.UserLoginDTO;
import com.epicode.GreenTravelPlanner.security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Service responsible for user authentication logic.
 * This class verifies user credentials and manages the issuance of security tokens.
 */
@Service
public class AuthService {

    @Autowired
    private UserService userService;

    @Autowired
    private JWTTools jwtTools;

    @Autowired
    private PasswordEncoder bcrypt;

    /**
     * Authenticates a user based on email and password, then generates a JWT.
     * * @param payload the DTO containing the user's login credentials
     * @return a signed JSON Web Token (JWT) string
     * @throws UnauthorizedException if the password does not match or user is not found
     */
    public String authenticateUserAndGenerateToken(UserLoginDTO payload) {
        // 1. Find the user in the database by their email address
        User user = userService.findByEmail(payload.email());

        // 2. Verify if the provided raw password matches the hashed password in the DB
        if (bcrypt.matches(payload.password(), user.getPassword())) {

            // 3. If credentials are correct, generate and return the access token
            return jwtTools.createToken(user);
        } else {

            // 4. If passwords do not match, throw a 401 Unauthorized error
            throw new UnauthorizedException("Invalid credentials! Please try again.");
        }
    }
}