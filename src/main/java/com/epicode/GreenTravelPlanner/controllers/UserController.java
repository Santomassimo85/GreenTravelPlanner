package com.epicode.GreenTravelPlanner.controllers;

import com.epicode.GreenTravelPlanner.entities.User;
import com.epicode.GreenTravelPlanner.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.security.access.prepost.PreAuthorize;

import java.io.IOException;

/**
 * REST Controller for managing user accounts.
 * Provides endpoints for retrieving user profiles, updating avatars,
 * and administrative deletion of users.
 */
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Retrieves a paginated list of all users.
     * * @param page   the page number to retrieve (default 0)
     * @param size   the number of records per page (default 10)
     * @param sortBy the field used for sorting the results (default "id")
     * @return a Page object containing User entities
     */
    @GetMapping
    public Page<User> getAllUsers(@RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "10") int size,
                                  @RequestParam(defaultValue = "id") String sortBy) {
        return userService.getAllUsers(page, size, sortBy);
    }

    /**
     * Retrieves a specific user by their unique identifier.
     * * @param id the ID of the user to find
     * @return the found User entity
     */
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.findById(id);
    }

    /**
     * Updates a user's profile picture by uploading an image to Cloudinary.
     * * @param userId the ID of the user whose avatar is being updated
     * @param file   the multipart image file
     * @return the updated User entity
     * @throws IOException if there is an error during file processing
     */
    @PatchMapping("/{userId}/avatar")
    public User uploadAvatar(@PathVariable Long userId, @RequestParam("avatar") MultipartFile file) throws IOException {
        return userService.uploadImage(userId, file);
    }

    /**
     * Deletes a user from the system. Restricted to users with ADMIN authority.
     * * @param id the ID of the user to be deleted
     * @return a confirmation message
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')") // Security check
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "User with ID " + id + " has been successfully deleted.";
    }
}