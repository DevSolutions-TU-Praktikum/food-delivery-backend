package com.example.food_delivery_app.Service;

import com.example.food_delivery_app.Entity.UserDelivererEntity;
import com.example.food_delivery_app.Repository.UserDelivererRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserDelivererService {
    private final UserDelivererRepository userDelivererRepository;

    @Autowired
    public UserDelivererService(UserDelivererRepository userDelivererRepository) {
        this.userDelivererRepository = userDelivererRepository;
    }


    public UserDelivererEntity createUserDeliverer(UserDelivererEntity userDeliverer) {
        return userDelivererRepository.save(userDeliverer);
    }


    public Optional<UserDelivererEntity> getUserDelivererById(int id) {
        if (!userDelivererRepository.existsById(id)) {
            throw new EntityNotFoundException("UserDelivererItem not found with ID: " + id);
        }
        return userDelivererRepository.findById(id);
    }


    public List<UserDelivererEntity> getAllUserDeliverers() {
        return userDelivererRepository.findAll();
    }


    public UserDelivererEntity updateUserDeliverer(int id, UserDelivererEntity UserDelivererEntity) {
        if (!userDelivererRepository.existsById(id)) {
            throw new EntityNotFoundException("UserDelivererItem not found with ID: " + id);
        }
        return userDelivererRepository.save(UserDelivererEntity);
    }


    public boolean deleteUserDeliverer(int id) {
        if (userDelivererRepository.existsById(id)) {
            userDelivererRepository.deleteById(id);
            return true;
        }
        return false;
    }
}


