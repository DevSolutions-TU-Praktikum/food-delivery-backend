package com.example.food_delivery_app.Service;

import com.example.food_delivery_app.Entity.Restaurant;
import com.example.food_delivery_app.Repository.RestaurantRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }


    public Restaurant createRestaurant(@Valid Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }


    public Optional<Restaurant> getRestaurantById(int id) {
        if (!restaurantRepository.existsById(id)) {
            throw new EntityNotFoundException("Restaurant not found with ID: " + id);
        }
        return restaurantRepository.findById(id);
    }


    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }


    public Restaurant updateRestaurant(int id, @Valid Restaurant restaurant) {
        if (!restaurantRepository.existsById(id)) {
            throw new EntityNotFoundException("Restaurant not found with ID: " + id);
        }
        return restaurantRepository.save(restaurant);
    }


    public boolean deleteRestaurant(int id) {
        if (restaurantRepository.existsById(id)) {
            restaurantRepository.deleteById(id);
            return true;
        }
        return false;
    }
}


