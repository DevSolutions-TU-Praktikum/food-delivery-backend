package com.example.food_delivery_app.Service;

import com.example.food_delivery_app.Entity.UserEmployeeEntity;
import com.example.food_delivery_app.Repository.UserEmployeeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserEmployeeService {
    private final UserEmployeeRepository useremployeeRepository;

    @Autowired
    public UserEmployeeService(UserEmployeeRepository useremployeeRepository) {
        this.useremployeeRepository = useremployeeRepository;
    }


    public UserEmployeeEntity createUserEmployee(UserEmployeeEntity useremployee) {
        return useremployeeRepository.save(useremployee);
    }


    public Optional<UserEmployeeEntity> getUserEmployeeById(int id) {
        if (!useremployeeRepository.existsById(id)) {
            throw new EntityNotFoundException("UserEmployeeItem not found with ID: " + id);
        }
        return useremployeeRepository.findById(id);
    }


    public List<UserEmployeeEntity> getAllUserEmployees() {
        return useremployeeRepository.findAll();
    }


    public UserEmployeeEntity updateUserEmployee(int id, UserEmployeeEntity UserEmployeeEntity) {
        if (!useremployeeRepository.existsById(id)) {
            throw new EntityNotFoundException("UserEmployeeItem not found with ID: " + id);
        }
        return useremployeeRepository.save(UserEmployeeEntity);
    }


    public boolean deleteUserEmployee(int id) {
        if (useremployeeRepository.existsById(id)) {
            useremployeeRepository.deleteById(id);
            return true;
        }
        return false;
    }
}


