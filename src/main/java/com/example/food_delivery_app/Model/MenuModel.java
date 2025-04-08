package com.example.food_delivery_app.Model;

import java.util.List;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class MenuModel {
    private int Id;
    private int restaurantId;
    private String menuDescription;
    List<MenuItemsModel> itemsModels;
}
