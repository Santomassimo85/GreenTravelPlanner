package com.epicode.GreenTravelPlanner.controllers;


import com.epicode.GreenTravelPlanner.entities.Traveler;
import com.epicode.GreenTravelPlanner.payloads.NewUserDTO;
import com.epicode.GreenTravelPlanner.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Traveler register(@RequestBody @Validated NewUserDTO body) {
        // Riceve i dati, li valida e li passa al service per il salvataggio
        return userService.save(body);
    }
}