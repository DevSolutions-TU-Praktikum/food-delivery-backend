package com.example.food_delivery_app.Service;

import com.example.food_delivery_app.Entity.User;
import com.example.food_delivery_app.Repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public User createUser(User user) {
        return userRepository.save(user);
    }


    public Optional<User> getUserById(int id) {
        if (!userRepository.existsById(id)) {
            throw new EntityNotFoundException("UserItem not found with ID: " + id);
        }
        return userRepository.findById(id);
    }

    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    public User updateUser(int id, User User) {
        if (!userRepository.existsById(id)) {
            throw new EntityNotFoundException("UserItem not found with ID: " + id);
        }
        return userRepository.save(User);
    }


    public boolean deleteUser(int id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }
}


