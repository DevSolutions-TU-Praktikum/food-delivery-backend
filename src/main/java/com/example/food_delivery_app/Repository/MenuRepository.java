package com.example.food_delivery_app.Repository;

import com.example.food_delivery_app.Entity.Menu;
import com.example.food_delivery_app.Entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Integer> {
    List<Menu> findByRestaurantId(int restaurantId);
    Optional<Menu> findByRestaurant(Restaurant restaurant);
}

