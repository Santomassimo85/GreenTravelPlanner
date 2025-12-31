package com.epicode.GreenTravelPlanner.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Usiamo una String semplice come avevi impostato,
    // ma assicuriamoci che i nomi siano consistenti
    @Column(nullable = false, unique = true)
    private String name; // Esempio: "ADMIN", "USER", "PREMIUM"

}