package com.example.food_delivery_app.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.Size;

import lombok.*;
import jakarta.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

@Entity
@Table(name = "menu_items")
public class MenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "menu_id")
  
    @JsonBackReference
    @JsonIgnore
    private Menu menu;

    @ManyToOne
    @JoinColumn(name = "order_id")
    @JsonBackReference
    private Order order;

    private String imageUrl;

    @NonNull
    private String itemName;

    private String itemDescription;

    @Size(min = 0, message = "Price cannot be negative.")
    private double itemPrice;
}
