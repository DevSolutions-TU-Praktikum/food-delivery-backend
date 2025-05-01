package com.example.food_delivery_app.Service;

import com.example.food_delivery_app.Entity.UserDeliverer;
import com.example.food_delivery_app.Repository.UserDelivererRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserDelivererService {
    private final UserDelivererRepository userdelivererRepository;

    @Autowired
    public UserDelivererService(UserDelivererRepository userdelivererRepository) {
        this.userdelivererRepository = userdelivererRepository;
    }


    public UserDeliverer createUserDeliverer(UserDeliverer userdeliverer) {
        return userdelivererRepository.save(userdeliverer);
    }


    public Optional<UserDeliverer> getUserDelivererById(int id) {
        if (!userdelivererRepository.existsById(id)) {
            throw new EntityNotFoundException("UserDeliverer not found with ID: " + id);
        }
        return userdelivererRepository.findById(id);
    }


    public List<UserDeliverer> getAllUserDeliverers() {
        return userdelivererRepository.findAll();
    }


    public UserDeliverer updateUserDeliverer(int id, UserDeliverer UserDeliverer) {
        if (!userdelivererRepository.existsById(id)) {
            throw new EntityNotFoundException("UserDeliverer not found with ID: " + id);
        }
        return userdelivererRepository.save(UserDeliverer);
    }


    public boolean deleteUserDeliverer(int id) {
        if (userdelivererRepository.existsById(id)) {
            userdelivererRepository.deleteById(id);
            return true;
        }
        return false;
    }
}


