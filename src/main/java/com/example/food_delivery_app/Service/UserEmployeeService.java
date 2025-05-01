package com.example.food_delivery_app.Service;

import com.example.food_delivery_app.Entity.UserEmployee;
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


    public UserEmployee createUserEmployee(UserEmployee useremployee) {
        return useremployeeRepository.save(useremployee);
    }


    public Optional<UserEmployee> getUserEmployeeById(int id) {
        if (!useremployeeRepository.existsById(id)) {
            throw new EntityNotFoundException("UserEmployee not found with ID: " + id);
        }
        return useremployeeRepository.findById(id);
    }


    public List<UserEmployee> getAllUserEmployees() {
        return useremployeeRepository.findAll();
    }


    public UserEmployee updateUserEmployee(int id, UserEmployee UserEmployee) {
        if (!useremployeeRepository.existsById(id)) {
            throw new EntityNotFoundException("UserEmployee not found with ID: " + id);
        }
        return useremployeeRepository.save(UserEmployee);
    }


    public boolean deleteUserEmployee(int id) {
        if (useremployeeRepository.existsById(id)) {
            useremployeeRepository.deleteById(id);
            return true;
        }
        return false;
    }
}


