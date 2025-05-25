package com.example.food_delivery_app.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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

    @Email
    @NonNull
    private String userEmail;

    @Size(min = 10, max = 12)
    @NonNull
    private String userPhoneNumber;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String adminPermissions;
}
