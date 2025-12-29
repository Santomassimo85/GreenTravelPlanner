package com.epicode.GreenTravelPlanner.services;

import com.epicode.entities.Traveler;
import com.epicode.payloads.NewUserDTO;
import com.epicode.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDate;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Traveler save(NewUserDTO body) {
        // 1. Verifichiamo se l'email è già presente (Robustezza)
        userRepository.findByEmail(body.email()).ifPresent(user -> {
            throw new RuntimeException("L'email " + body.email() + " è già in uso!");
        });

        // 2. Creiamo l'oggetto Traveler (Ereditarietà)
        Traveler newUser = new Traveler();
        newUser.setName(body.name()); // [cite: 17]
        newUser.setSurname(body.surname()); // [cite: 17]
        newUser.setEmail(body.email()); // [cite: 16]

        // 3. Criptiamo la password (Sicurezza) [cite: 16, 50]
        newUser.setPassword(passwordEncoder.encode(body.password()));

        // 4. Impostiamo i dettagli automatici [cite: 17]
        newUser.setRegistrationDate(LocalDate.now());
        newUser.setProfileImage("https://ui-avatars.com/api/?name=" + body.name()); // Immagine di default

        return userRepository.save(newUser);
    }
}