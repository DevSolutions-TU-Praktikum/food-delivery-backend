package com.example.food_delivery_app.Model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class UserDelivererModel {
    private int Id;
    private double delivererRating;
    private int completedDeliveries;
    private double totalEarnings;
}
