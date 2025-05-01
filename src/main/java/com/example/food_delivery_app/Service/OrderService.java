package com.example.food_delivery_app.Service;

import com.example.food_delivery_app.Entity.*;
import com.example.food_delivery_app.Repository.MenuItemRepository;
import com.example.food_delivery_app.Repository.OrderRepository;
import com.example.food_delivery_app.Repository.RestaurantRepository;
import com.example.food_delivery_app.Repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final MenuItemRepository menuItemRepository;
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, MenuItemRepository menuItemRepository, UserRepository userRepository, RestaurantRepository restaurantRepository) {
        this.orderRepository = orderRepository;
        this.menuItemRepository = menuItemRepository;
        this.userRepository = userRepository;
        this.restaurantRepository = restaurantRepository;
    }


    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }


    public Optional<Order> getOrderById(int id) {
        if (!orderRepository.existsById(id)) {
            throw new EntityNotFoundException("Order not found with ID: " + id);
        }
        return orderRepository.findById(id);
    }


    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }


    public Order updateOrder(int id, Order Order) {
        if (!orderRepository.existsById(id)) {
            throw new EntityNotFoundException("Order not found with ID: " + id);
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

    public Order createOrderForUserIdAndRestaurant(int userId, int restaurantId) {
        User user = userRepository.findById(userId).orElseThrow();
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow();

        Order order = new Order();
        order.setUser(user);
        order.setRestaurant(restaurant);
        order.setMenuItems(new ArrayList<>());
        order.setCreatedOrder(LocalDateTime.now());
        order.setStatus(Status.DRAFT);
        order.setDeliveryFee(0);
        order.setTip(0);
        order.setTotalCost(0);

        return orderRepository.save(order);
    }

    public Order addItemToOrder(int userId, int menuItemId) {
        User user = userRepository.findById(userId).orElseThrow();
        MenuItem item = menuItemRepository.findById(menuItemId).orElseThrow();

        Order order = orderRepository
                .findByUserAndStatus(user, Status.DRAFT)
                .orElseGet(() -> {
                   Order newOrder = new Order();
                   newOrder.setUser(user);
                   newOrder.setRestaurant(item.getMenu().getRestaurant());
                   newOrder.setMenuItems(new ArrayList<>());
                   newOrder.setCreatedOrder(LocalDateTime.now());
                   newOrder.setStatus(Status.DRAFT);
                   newOrder.setDeliveryFee(0);
                   newOrder.setTip(0);
                   newOrder.setTotalCost(0);
                   return newOrder;
                });

        item.setOrder(order);
        menuItemRepository.save(item);
        order.getMenuItems().add(item);

        double total = order.getTotalCost() + item.getItemPrice() + order.getTip();
        order.setTotalCost(Math.round(total * 100.0) / 100.0);

        return orderRepository.save(order);
    }

    public Order finalizeOrder(int userId) {
        User user = userRepository.findById(userId).orElseThrow();
        Order draftOrder = orderRepository.findByUserAndStatus(user, Status.DRAFT)
                .orElseThrow(() -> new RuntimeException("No draft order to finalize"));

        draftOrder.setStatus(Status.PENDING);
        return orderRepository.save(draftOrder);
    }

    public Order getDraftOrder(int userId){
        User user = userRepository.findById(userId).orElseThrow();
        return orderRepository.findByUserAndStatus(user, Status.DRAFT).orElse(null);
    }
}


