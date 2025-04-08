package com.example.food_delivery_app.Entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

@Entity
@Table(name = "USER_DELIVERER")
public class UserDeliverer {
    @Id
    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private int id;

    private double delivererRating;

    private int completedDeliveries;

    private double totalEarnings;
}
