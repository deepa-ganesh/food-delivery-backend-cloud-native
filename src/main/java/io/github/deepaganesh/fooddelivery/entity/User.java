package io.github.deepaganesh.fooddelivery.entity;

import io.github.deepaganesh.fooddelivery.common.UserRole;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "user")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private Long phoneNumber;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    private String passwordHash;

    private LocalDate createdAt;

    private LocalDate updatedAt;

    @OneToMany(mappedBy = "owner")
    private List<Restaurant> restaurants;
}
