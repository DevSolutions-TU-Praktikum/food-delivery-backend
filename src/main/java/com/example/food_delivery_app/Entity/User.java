package com.example.food_delivery_app.Entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class User {
    private int id;
    private String username;
    private String password;
    private String userEmail;
    private String userPhoneNumber;
}
