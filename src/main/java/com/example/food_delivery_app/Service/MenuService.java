package com.example.food_delivery_app.Service;

import com.example.food_delivery_app.Entity.Menu;
import com.example.food_delivery_app.Repository.MenuRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MenuService {
    private final MenuRepository menuRepository;

    @Autowired
    public MenuService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }


    public Menu createMenu(Menu menu) {
        return menuRepository.save(menu);
    }


    public Optional<Menu> getMenuById(int id) {
        if (!menuRepository.existsById(id)) {
            throw new EntityNotFoundException("MenuItem not found with ID: " + id);
        }
        return menuRepository.findById(id);
    }


    public List<Menu> getAllMenus() {
        return menuRepository.findAll();
    }


    public Menu updateMenu(int id, Menu menu) {
        if (!menuRepository.existsById(id)) {
            throw new EntityNotFoundException("MenuItem not found with ID: " + id);
        }
        return menuRepository.save(menu);
    }


    public boolean deleteMenu(int id) {
        if (menuRepository.existsById(id)) {
            menuRepository.deleteById(id);
            return true;
        }
        return false;
    }
}


