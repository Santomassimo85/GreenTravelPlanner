package com.epicode.GreenTravelPlanner.services;

import com.epicode.GreenTravelPlanner.entities.Traveler;
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

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JWTTools jwtTools;

    public UserService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public Traveler save(NewUserDTO body) {
        userRepository.findByEmail(body.email()).ifPresent(user -> {
            throw new RuntimeException("L'email " + body.email() + " è già in uso!");
        });

        Traveler newUser = new Traveler();
        newUser.setName(body.name()); // [cite: 17]
        newUser.setSurname(body.surname()); // [cite: 17]
        newUser.setEmail(body.email()); // [cite: 16]

        newUser.setPassword(passwordEncoder.encode(body.password()));

        newUser.setRegistrationDate(LocalDate.now());
        newUser.setProfileImage("https://ui-avatars.com/api/?name=" + body.name()); // Immagine di default

        return userRepository.save(newUser);
    }

    public String authenticate(LoginDTO body) {
        User user = userRepository.findByEmail(body.email())
                .orElseThrow(() -> new RuntimeException("Credenziali non valide: email non trovata"));

        if (!passwordEncoder.matches(body.password(), user.getPassword())) {
            throw new RuntimeException("Credenziali non valide: password errata");
        }

        return jwtTools.createToken(user);
    }

    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Utente non trovato con ID: " + id));
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Utente con email " + email + " non trovato"));
    }

    public org.springframework.data.domain.Page<User> getAllUsers(int page, int size, String sortBy) {
        if (size > 50) size = 50; // Limita la dimensione per sicurezza
        org.springframework.data.domain.Pageable pageable = org.springframework.data.domain.PageRequest.of(page, size, org.springframework.data.domain.Sort.by(sortBy));
        return userRepository.findAll(pageable);
    }

    @Autowired
    private Cloudinary cloudinary;

    public User uploadImage(Long id, MultipartFile file) throws IOException {

        User found = this.findById(id);

        String url = (String) cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap()).get("url");

        found.setProfileImage(url);

        return userRepository.save(found);
    }
}