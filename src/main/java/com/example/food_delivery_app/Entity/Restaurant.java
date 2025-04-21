package com.example.food_delivery_app.Entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

@Entity
@Table(name = "RESTAURANTS")
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NonNull
    private String restaurantName;

    @NonNull
    private String restaurantAddress;

    @NonNull
    private String restaurantPhoneNumber;

    @NonNull
    private String restaurantEmail;
    private double restaurantRevenue;
    private double restaurantRating;
}
