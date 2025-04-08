package com.example.food_delivery_app.Model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class RestaurantsModel {
    private int Id;
    private String restaurantName;
    private String restaurantAddress;
    private String restaurantPhoneNumber;
    private String restaurantEmail;
    private double restaurantRevenue;
    private double restaurantRating;
}
