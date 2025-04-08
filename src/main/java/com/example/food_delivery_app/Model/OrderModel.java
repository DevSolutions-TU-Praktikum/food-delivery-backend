package com.example.food_delivery_app.Model;

import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class OrderModel {
    private int Id;
    private int customerId;
    private int restaurantId;
    private int delivererId;
    List<MenuItemsModel> itemsModels;
    private double deliveryFee;
    private double tip;
    private double totalCost;
    private DateTimeFormat createdOrder;
    private enum status {
        PENDING,
        ACCEPTED,
        PICKEDUP,
        DELIVERED,
        REFUSED
    }
}
