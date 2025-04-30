package com.example.food_delivery_app.Entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

@Entity
@Table(name = "user_deliverers")
public class UserDeliverer {
    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private User userEntity;

    @Id
    private int id;

    private double delivererRating;

    private int completedDeliveries;

    private double totalEarnings;
}
