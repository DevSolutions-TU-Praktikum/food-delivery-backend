package com.example.food_delivery_app.Service;

import com.example.food_delivery_app.Entity.MenuItem;
import com.example.food_delivery_app.Repository.MenuItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MenuItemService {
    private final MenuItemRepository menuItemRepository;

    @Autowired
    public MenuItemService(MenuItemRepository menuItemRepository) {
        this.menuItemRepository = menuItemRepository;
    }


    public MenuItem createMenuItem(MenuItem menuItem) {
        return menuItemRepository.save(menuItem);
    }


    public Optional<MenuItem> getMenuItemById(int id) {
        return menuItemRepository.findById(id);
    }


    public List<MenuItem> getAllMenuItems() {
        return menuItemRepository.findAll();
    }


    public MenuItem updateMenuItem(int id, MenuItem menuItem) {
        if (menuItemRepository.existsById(id)) {
            menuItem.setId(id);
            return menuItemRepository.save(menuItem);
        }
        return null;
    }


    public boolean deleteMenuItem(int id) {
        if (menuItemRepository.existsById(id)) {
            menuItemRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

