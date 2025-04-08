package com.example.food_delivery_app.Entity;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

@Entity
@Table(name = "MENU_ITEMS")
public class MenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "menu_id")
    private Menu menu;

    private MultipartFile image;

    @NonNull
    private String itemName;

    private String itemDescription;

    private double itemPrice;
}
