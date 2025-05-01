package com.example.food_delivery_app.Entity;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NonNull
    private String username;

    @NonNull
    private String password;

    @NonNull
    private String userEmail;

    @NonNull
    private String userPhoneNumber;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String adminPermissions;
}
