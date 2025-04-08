package com.example.food_delivery_app.Entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

@Entity
@Table(name = "USER_CLIENT")
public class UserClient {
    @Id
    @OneToOne
    @MapsId 
    @JoinColumn(name = "id")
    private int id;
}
