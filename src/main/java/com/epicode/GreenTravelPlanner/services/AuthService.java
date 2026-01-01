package com.epicode.GreenTravelPlanner.services;

import com.epicode.GreenTravelPlanner.entities.User;
import com.epicode.GreenTravelPlanner.exceptions.UnauthorizedException;
import com.epicode.GreenTravelPlanner.payloads.UserLoginDTO;
import com.epicode.GreenTravelPlanner.security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserService userService;

    @Autowired
    private JWTTools jwtTools;

    @Autowired
    private PasswordEncoder bcrypt;

    public String authenticateUserAndGenerateToken(UserLoginDTO payload) {
        // 1. Cerchiamo l'utente nel database tramite email
        User user = userService.findByEmail(payload.email());

        // 2. Verifichiamo se la password nel payload corrisponde a quella (criptata) nel DB
        if (bcrypt.matches(payload.password(), user.getPassword())) {
            // 3. Se sono uguali, generiamo il token e lo restituiamo
            return jwtTools.createToken(user);
        } else {
            // 4. Se le password non corrispondono, lanciamo un errore 401
            throw new UnauthorizedException("Credenziali non valide! Riprova.");
        }
    }
}