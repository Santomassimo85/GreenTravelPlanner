package com.epicode.GreenTravelPlanner.controllers;

import com.epicode.GreenTravelPlanner.entities.User;
import com.epicode.GreenTravelPlanner.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public Page<User> getAllUsers(@RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "10") int size,
                                  @RequestParam(defaultValue = "id") String sortBy) {
        return userService.getAllUsers(page, size, sortBy);
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @PatchMapping("/{userId}/avatar")
    public User uploadAvatar(@PathVariable Long userId, @RequestParam("avatar") MultipartFile file) throws IOException {

        return userService.uploadImage(userId, file);
    }
}