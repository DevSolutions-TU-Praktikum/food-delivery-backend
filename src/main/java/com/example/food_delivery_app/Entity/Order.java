package com.example.food_delivery_app.Entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

@Entity
@Table(name = "ORDER")

public class Order {
    private enum Status {
        PENDING,
        ACCEPTED,
        PICKEDUP,
        DELIVERED,
        REFUSED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "userclient_id")
    private UserClient userClient;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @ManyToOne
    @JoinColumn(name = "deliverer_id")
    private UserDeliverer userDeliverer;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    List<MenuItem> menuItems = new ArrayList<>();

    private double deliveryFee;

    private double tip;

    private double totalCost;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdOrder;

    @Enumerated(EnumType.ORDINAL)
    private Status status;
}
