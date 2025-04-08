package com.example.food_delivery_app.Model;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class MenuItemsModel {
    private int Id;
    private int menuId;
    private MultipartFile image;
    private String itemName;
    private String itemDescription;
    private double itemPrice;
}
