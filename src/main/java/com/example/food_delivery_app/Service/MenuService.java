package com.example.food_delivery_app.Service;

import com.example.food_delivery_app.Entity.Menu;
import com.example.food_delivery_app.Entity.MenuItem;
import com.example.food_delivery_app.Entity.Restaurant;
import com.example.food_delivery_app.Repository.MenuItemRepository;
import com.example.food_delivery_app.Repository.MenuRepository;
import com.example.food_delivery_app.Repository.RestaurantRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MenuService {
    private final MenuRepository menuRepository;
    private final RestaurantRepository restaurantRepository;
    private final MenuItemRepository menuItemRepository;

    @Autowired
    public MenuService(MenuRepository menuRepository, RestaurantRepository restaurantRepository, MenuItemRepository menuItemRepository) {
        this.menuRepository = menuRepository;
        this.restaurantRepository = restaurantRepository;
        this.menuItemRepository = menuItemRepository;
    }

    public List<MenuItem> getMenuItemsByRestaurantId(int restaurantId) {
        List<Menu> menus = menuRepository.findByRestaurantId(restaurantId);
        List<MenuItem> result = new ArrayList<>();
        for (Menu menu : menus){
            result.addAll(menu.getMenuItems());
        }
        return result;
    }

    public Menu createMenu(int restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow();

        Menu menu = new Menu();
        menu.setMenuDescription("Default description");
        menu.setRestaurant(restaurant);

        return menuRepository.save(menu);
    }


    public Optional<Menu> getMenuById(int id) {
        if (!menuRepository.existsById(id)) {
            throw new EntityNotFoundException("Menu not found with ID: " + id);
        }
        return menuRepository.findById(id);
    }


    public List<Menu> getAllMenus() {
        return menuRepository.findAll();
    }


    public Menu updateMenu(int id, @Valid Menu menu) {
        if (!menuRepository.existsById(id)) {
            throw new EntityNotFoundException("Menu not found with ID: " + id);
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

    public Menu addItemToMenu(int itemId, int restaurantId) {
        MenuItem menuItem = menuItemRepository.findById(itemId).orElseThrow();
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow();

        Menu menu = menuRepository.findByRestaurant(restaurant).orElseThrow();

        menu.getMenuItems().add(menuItem);

        menuItem.setMenu(menu);

        return menuRepository.save(menu);
    }
}


