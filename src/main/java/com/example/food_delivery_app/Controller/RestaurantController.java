package com.example.food_delivery_app.Controller;

import com.example.food_delivery_app.Entity.Restaurant;
import com.example.food_delivery_app.Service.RestaurantService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:5500")
@RequestMapping("/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;


    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping
    public List<Restaurant> getAllRestaurants(){
        return restaurantService.getAllRestaurants();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> getRestaurantById(@PathVariable int id) {
        Optional<Restaurant> found = restaurantService.getRestaurantById(id);
        return ResponseEntity.of(found);
    }

    @PostMapping("/create")
    public ResponseEntity<Restaurant> createRestaurant(@RequestBody Restaurant restaurant){
        Restaurant savedRestaurant = restaurantService.createRestaurant(restaurant);
        return ResponseEntity.ok(savedRestaurant);
    }

    @DeleteMapping("/delete/{Id}")
    public ResponseEntity<String> deleteRestaurant(@PathVariable int Id) {
        boolean deleted = restaurantService.deleteRestaurant(Id);
        if (deleted) {
            return ResponseEntity.ok("Restaurant deleted successfully.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
