package com.example.food_delivery_app.Controller;

import com.example.food_delivery_app.Entity.Menu;
import com.example.food_delivery_app.Entity.MenuItem;
import com.example.food_delivery_app.Service.MenuService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins ="localhost:5500")
@RequestMapping("/menu")
public class MenuController {

    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping
    public List<Menu> getAllMenus(){
        return menuService.getAllMenus();
    }

    @PostMapping("/create")
    public Menu createMenu(@RequestParam int restaurantId){
        return menuService.createMenu(restaurantId);
    }

    @PostMapping("/add-items")
    public Menu addItemsToMenu(@RequestParam int itemId, @RequestParam int restaurantId){
        return menuService.addItemToMenu(itemId, restaurantId);
    }

    @GetMapping("/restaurants/{restaurantId}")
    public List<MenuItem> getMenuItem(@PathVariable int restaurantId) {
        return menuService.getMenuItemsByRestaurantId(restaurantId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Menu> getMenuById(@PathVariable int id) {
        Optional<Menu> found = menuService.getMenuById(id);
        return ResponseEntity.of(found);
    }
}
