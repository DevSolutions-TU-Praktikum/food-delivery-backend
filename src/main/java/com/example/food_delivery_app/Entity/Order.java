package com.example.food_delivery_app.Entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

@Entity
@Table(name = "orders")

public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @ManyToOne
    @JoinColumn(name = "deliverer_id")
    private UserDeliverer userDelivererEntity;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    @JsonManagedReference
    List<MenuItem> menuItems = new ArrayList<>();

    @Size(min = 0, message = "Fee cannot be negative")
    private double deliveryFee;

    @Size(min = 0, message = "Tip cannot be negative")
    private double tip;

    @Size(min = 0, message = "Total cost cannot be negative")
    private double totalCost;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdOrder;

    @Enumerated(EnumType.ORDINAL)
    private Status status;
}