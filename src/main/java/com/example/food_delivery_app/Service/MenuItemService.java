package com.example.food_delivery_app.Service;

import com.example.food_delivery_app.Entity.MenuItem;
import com.example.food_delivery_app.Repository.MenuItemRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
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


    public MenuItem createMenuItem(@Valid MenuItem menuItem) {
        return menuItemRepository.save(menuItem);
    }


    public Optional<MenuItem> getMenuItemById(int id) {
        if (!menuItemRepository.existsById(id)) {
            throw new EntityNotFoundException("MenuItem not found with ID: " + id);
        }
        return menuItemRepository.findById(id);
    }


    public List<MenuItem> getAllMenuItems() {
        return menuItemRepository.findAll();
    }


    public MenuItem updateMenuItem(int id, @Valid MenuItem menuItem) {
        if (!menuItemRepository.existsById(id)) {
            throw new EntityNotFoundException("MenuItem not found with ID: " + id);
        }
        return menuItemRepository.save(menuItem);
    }


    public boolean deleteMenuItem(int id) {
        if (menuItemRepository.existsById(id)) {
            menuItemRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

