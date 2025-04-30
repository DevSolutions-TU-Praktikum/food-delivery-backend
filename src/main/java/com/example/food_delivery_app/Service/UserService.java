package com.example.food_delivery_app.Service;

import com.example.food_delivery_app.Entity.UserEntity;
import com.example.food_delivery_app.Repository.UserRepository;
import com.example.food_delivery_app.dto.SignUpDto;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public UserEntity createUser(SignUpDto dto) {
        UserEntity userEntity = modelMapper.map(dto, UserEntity.class);
        return userRepository.save(userEntity);
    }


    public Optional<UserEntity> getUserById(int id) {
        if (!userRepository.existsById(id)) {
            throw new EntityNotFoundException("UserItem not found with ID: " + id);
        }
        return userRepository.findById(id);
    }


    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }


    public UserEntity updateUser(int id, UserEntity UserEntity) {
        if (!userRepository.existsById(id)) {
            throw new EntityNotFoundException("UserItem not found with ID: " + id);
        }
        return userRepository.save(UserEntity);
    }


    public boolean deleteUser(int id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }
}


