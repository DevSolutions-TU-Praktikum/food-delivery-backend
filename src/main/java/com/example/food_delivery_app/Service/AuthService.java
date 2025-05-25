package com.example.food_delivery_app.Service;

import com.example.food_delivery_app.Entity.User;
import com.example.food_delivery_app.Service.UserDelivererService;
import com.example.food_delivery_app.Service.UserEmployeeService;
import com.example.food_delivery_app.Service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UserService userService;
    private final UserEmployeeService employeeService;
    private final UserDelivererService delivererService;

    public AuthService(UserService userService, UserEmployeeService employeeService, UserDelivererService delivererService) {
        this.userService = userService;
        this.employeeService = employeeService;
        this.delivererService = delivererService;
    }

    public int getUserRole(int userId) {
        try {
            if (employeeService.getUserEmployeeById(userId).isPresent()) return 2;
        } catch (EntityNotFoundException e) {
            // safely ignore
        }
        try {
            if (delivererService.getUserDelivererById(userId).isPresent()) return 1;
        } catch (EntityNotFoundException e) {
            // safely ignore
        }
        return 0;
    }


    public Optional<User> authenticate(String username, String password) {
        Optional<User> userOpt = userService.getUserByUsername(username);
        return userOpt.filter(user -> user.getPassword().equals(password));
    }
}

