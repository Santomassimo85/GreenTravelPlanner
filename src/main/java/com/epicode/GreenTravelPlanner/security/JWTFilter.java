package com.epicode.GreenTravelPlanner.security;

import com.epicode.GreenTravelPlanner.entities.User;
import com.epicode.GreenTravelPlanner.services.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

@Component
public class JWTFilter extends OncePerRequestFilter { // Filtro eseguito una volta per ogni richiesta

    @Autowired
    private JWTTools jwtTools;

    private final UserService userService;

    public JWTFilter(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // 1. Controlliamo se nella richiesta c'è l'Header "Authorization"
        String authHeader = request.getHeader("Authorization");

        // Se non c'è o non inizia con "Bearer ", la richiesta va avanti (verrà bloccata dal SecurityConfig se la rotta è protetta)
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // 2. Estraiamo il token togliendo la parola "Bearer "
        String token = authHeader.substring(7);

        // 3. Verifichiamo il token
        jwtTools.verifyToken(token);

        // 4. Se è valido, cerchiamo l'utente nel DB tramite l'ID scritto nel token
        String id = jwtTools.extractIdFromToken(token);
        User user = userService.findById(Long.parseLong(id));
        // 5. "Autentichiamo" l'utente nel sistema di Spring Security
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // 6. Passiamo al prossimo filtro o al Controller
        filterChain.doFilter(request, response);
    }

    // Specifichiamo che per le rotte di registrazione/login il filtro NON deve scattare
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return request.getServletPath().startsWith("/auth/");
    }


}