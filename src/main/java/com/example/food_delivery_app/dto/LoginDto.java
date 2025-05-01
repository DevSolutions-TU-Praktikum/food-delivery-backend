package com.example.food_delivery_app.dto;

import lombok.*;

@Setter
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginDto {
    @NonNull
    private String username;
    @NonNull
    private String password;
}
