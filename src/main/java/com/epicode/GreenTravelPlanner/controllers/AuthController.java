package com.epicode.GreenTravelPlanner.controllers;

import com.epicode.GreenTravelPlanner.entities.User;
import com.epicode.GreenTravelPlanner.payloads.NewUserDTO;
import com.epicode.GreenTravelPlanner.payloads.UserLoginDTO;
import com.epicode.GreenTravelPlanner.payloads.LoginResponseDTO;
import com.epicode.GreenTravelPlanner.services.AuthService;
import com.epicode.GreenTravelPlanner.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * REST Controller responsible for authentication and authorization.
 * It provides endpoints for user registration and login.
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    /**
     * Handles new user registration requests.
     * * @param body the validated data transfer object containing new user details
     * @return the newly created User entity
     */
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public User register(@RequestBody @Valid NewUserDTO body) {
        // Delegates the business logic for saving a user to the UserService
        return userService.save(body);
    }

    /**
     * Handles user login requests and generates a security token.
     * * @param body the validated data transfer object containing login credentials
     * @return a DTO containing the generated access token
     */
    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody @Valid UserLoginDTO body) {
        // Authenticates credentials and returns the token wrapped in a response object
        return new LoginResponseDTO(authService.authenticateUserAndGenerateToken(body));
    }
}