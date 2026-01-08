package com.epicode.GreenTravelPlanner.services;

import com.epicode.GreenTravelPlanner.entities.User;
import com.epicode.GreenTravelPlanner.exceptions.NotFoundException;
import com.epicode.GreenTravelPlanner.payloads.LoginDTO;
import com.epicode.GreenTravelPlanner.payloads.NewUserDTO;
import com.epicode.GreenTravelPlanner.repositories.UserRepository;
import com.epicode.GreenTravelPlanner.security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

/**
 * Service class for managing User business logic.
 * Handles user registration, authentication, image uploads, and profile management.
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JWTTools jwtTools;

    @Autowired
    private Cloudinary cloudinary;

    /**
     * Registers a new user in the system.
     * Encrypts the password and assigns a default avatar before saving.
     * * @param body the registration data transfer object
     * @return the saved User entity
     * @throws RuntimeException if the email is already registered
     */
    public User save(NewUserDTO body) {
        // Check if email already exists
        userRepository.findByEmail(body.email()).ifPresent(user -> {
            throw new RuntimeException("Email " + body.email() + " is already in use!");
        });

        User newUser = new User();
        newUser.setName(body.name());
        newUser.setSurname(body.surname());
        newUser.setEmail(body.email());

        // Securely hash the password using BCrypt
        newUser.setPassword(passwordEncoder.encode(body.password()));

        newUser.setRegistrationDate(LocalDate.now());
        // Set a dynamic placeholder image based on user's name
        newUser.setProfileImage("https://ui-avatars.com/api/?name=" + body.name());

        return userRepository.save(newUser);
    }

    /**
     * Authenticates a user and generates a security token.
     * * @param body the login credentials
     * @return a signed JWT string
     */
    public String authenticate(LoginDTO body) {
        User user = userRepository.findByEmail(body.email())
                .orElseThrow(() -> new RuntimeException("Invalid credentials: email not found"));

        if (!passwordEncoder.matches(body.password(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials: wrong password");
        }

        return jwtTools.createToken(user);
    }

    /**
     * Finds a user by their unique database ID.
     */
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found with ID: " + id));
    }

    /**
     * Finds a user by their unique email address.
     */
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User with email " + email + " not found"));
    }

    /**
     * Retrieves a paginated list of all users.
     */
    public org.springframework.data.domain.Page<User> getAllUsers(int page, int size, String sortBy) {
        if (size > 50) size = 50;
        org.springframework.data.domain.Pageable pageable =
                org.springframework.data.domain.PageRequest.of(page, size, org.springframework.data.domain.Sort.by(sortBy));
        return userRepository.findAll(pageable);
    }

    /**
     * Uploads a profile image to Cloudinary and updates the user's record.
     * * @param id the user's ID
     * @param file the image file from the request
     * @return the updated User entity
     * @throws IOException if the upload to Cloudinary fails
     */
    public User uploadImage(Long id, MultipartFile file) throws IOException {
        User found = this.findById(id);

        // Upload to Cloudinary cloud storage
        String url = (String) cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap()).get("url");

        found.setProfileImage(url);
        return userRepository.save(found);
    }

    /**
     * Removes a user from the system.
     */
    public void deleteUser(Long id) {
        User user = this.findById(id);
        userRepository.delete(user);
    }
}