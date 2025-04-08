package com.example.food_delivery_app.Entity;

import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor


public class User
{
    private int id;

    @NonNull
    private String username;

    @NonNull
    private String password;

    @NonNull
    private String phoneNumber;

    @NonNull
    private String email;
}