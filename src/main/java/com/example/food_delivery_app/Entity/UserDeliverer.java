package com.example.food_delivery_app.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
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

    @Size(min = 1, max = 5, message = "Rating is between 1 and 5")
    private double delivererRating;

    @Size(min = 0, message = "Deliveries cannot be negative")
    private int completedDeliveries;

    @Size(min = 0, message = "Earnings cannot be negative")
    private double totalEarnings;
}
