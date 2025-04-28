package com.example.food_delivery_app.Service;

import com.example.food_delivery_app.Entity.Order;
import com.example.food_delivery_app.Repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }


    public Optional<Order> getOrderById(int id) {
        if (!orderRepository.existsById(id)) {
            throw new EntityNotFoundException("OrderItem not found with ID: " + id);
        }
        return orderRepository.findById(id);
    }


    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }


    public Order updateOrder(int id, Order Order) {
        if (!orderRepository.existsById(id)) {
            throw new EntityNotFoundException("OrderItem not found with ID: " + id);
        }
        return orderRepository.save(Order);
    }


    public boolean deleteOrder(int id) {
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
            return true;
        }
        return false;
    }
}


