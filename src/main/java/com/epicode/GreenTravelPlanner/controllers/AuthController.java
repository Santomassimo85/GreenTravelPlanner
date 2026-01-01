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


@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public User register(@RequestBody @Valid NewUserDTO body) {
        return userService.save(body);
    }

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody @Valid UserLoginDTO body) {
        return new LoginResponseDTO(authService.authenticateUserAndGenerateToken(body));
    }
}