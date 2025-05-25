package com.example.food_delivery_app.dto;

import lombok.*;

@Setter
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthResponseDto {

    private int id;
    private String username;
    private int role;
}

