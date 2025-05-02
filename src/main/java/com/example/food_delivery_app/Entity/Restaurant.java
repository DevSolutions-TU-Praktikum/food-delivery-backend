package com.example.food_delivery_app.Entity;

import jakarta.persistence.*;
import lombok.*;
import jakarta.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

@Entity
@Table(name = "restaurants")
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NonNull
    private String restaurantName;

    @NonNull
    private String restaurantAddress;

    @Size(min = 10, max = 12)
    @NonNull
    private String restaurantPhoneNumber;

    @Email
    @NonNull
    private String restaurantEmail;

    private double restaurantRevenue;

    private double restaurantRating;
}
