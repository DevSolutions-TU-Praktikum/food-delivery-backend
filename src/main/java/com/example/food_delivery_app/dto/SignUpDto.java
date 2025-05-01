package com.example.food_delivery_app.dto;

import lombok.*;

@Setter
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignUpDto {
    @NonNull
    private String username;

    @NonNull
    private String password;

    @NonNull
    private String userEmail;

    @NonNull
    private String userPhoneNumber;

}
