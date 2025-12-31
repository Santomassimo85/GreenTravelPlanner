package com.epicode.GreenTravelPlanner.services;

import com.epicode.GreenTravelPlanner.entities.Traveler;
import com.epicode.GreenTravelPlanner.entities.User;
import com.epicode.GreenTravelPlanner.payloads.LoginDTO;
import com.epicode.GreenTravelPlanner.payloads.NewUserDTO;
import com.epicode.GreenTravelPlanner.repositories.UserRepository;
import com.epicode.GreenTravelPlanner.security.JWTTools;
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

    @Autowired
    private JWTTools jwtTools;

    public UserService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

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

    public String authenticate(LoginDTO body) {
        // 1. Cerchiamo l'utente nel DB tramite l'email che ha inserito nel Login
        User user = userRepository.findByEmail(body.email())
                .orElseThrow(() -> new RuntimeException("Credenziali non valide: email non trovata"));

        // 2. Confrontiamo la password inserita (body.password) con quella criptata nel DB (user.getPassword)
        // Usiamo .matches perché non possiamo leggerle direttamente essendo criptate
        if (!passwordEncoder.matches(body.password(), user.getPassword())) {
            throw new RuntimeException("Credenziali non valide: password errata");
        }

        // 3. Se tutto è ok, chiediamo a JWTTools di stampare il "pass" (Token) per questo utente
        return jwtTools.createToken(user);
    }

    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Utente non trovato con ID: " + id));
    }
}