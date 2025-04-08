package com.example.food_delivery_app.Model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class UserAdminModel {
    private int Id;
    private String adminPermissions;
}
