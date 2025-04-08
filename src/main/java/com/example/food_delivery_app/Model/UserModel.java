package com.example.food_delivery_app.Model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class UserModel {
    private int id;
    private String username;
    private String password;
    private String userEmail;
    private String userPhoneNumber;
}
