package com.example.food_delivery_app.Repository;

import com.example.food_delivery_app.Entity.Order;
import com.example.food_delivery_app.Entity.Status;
import com.example.food_delivery_app.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    Optional<Order> findByUserAndStatus(User user, Status status);
}