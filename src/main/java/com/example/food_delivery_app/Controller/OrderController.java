package com.example.food_delivery_app.Controller;

import com.example.food_delivery_app.Entity.MenuItem;
import com.example.food_delivery_app.Entity.Order;
import com.example.food_delivery_app.Entity.Restaurant;
import com.example.food_delivery_app.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping
    public List<Order> getAllOrders(){
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable int id) {
        Optional<Order> found = orderService.getOrderById(id);
        return ResponseEntity.of(found);
    }
/*
    @PostMapping("/create")
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        Order savedOrder = orderService.createOrder(order);
        return ResponseEntity.ok(savedOrder);
    }
*/
    @PostMapping("/create")
    public Order createOrder(@RequestParam int userId, @RequestParam int restaurantId) {
        return orderService.createOrderForUserIdAndRestaurant(userId, restaurantId);
    }

    @PostMapping("/add-item")
    public Order addItemToCart(@RequestParam int userId, @RequestParam int menuItemId) {
        return orderService.addItemToOrder(userId, menuItemId);
    }

    @PostMapping("/checkout")
    public Order finalizeOrder(@RequestParam int userId){
        return orderService.finalizeOrder(userId);
    }

    @GetMapping("/cart")
    public Order getCart(@RequestParam int userId) {
        return orderService.getDraftOrder(userId);
    }

    @DeleteMapping("/delete/{Id}")
    public ResponseEntity<String> deleteOrder(@PathVariable int Id) {
        boolean deleted = orderService.deleteOrder(Id);
        if (deleted) {
            return ResponseEntity.ok("Order deleted successfully.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
