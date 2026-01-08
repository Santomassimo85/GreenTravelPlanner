package com.epicode.GreenTravelPlanner.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entity representing a user role within the system.
 * Roles are used for access control and permission management,
 * defining what actions a user is authorized to perform.
 */
@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The unique name of the role.
     * Examples: "ADMIN", "USER", "PREMIUM"
     */
    @Column(nullable = false, unique = true)
    private String name;

}