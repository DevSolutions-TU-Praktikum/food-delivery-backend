package com.example.food_delivery_app.services;

import com.example.food_delivery_app.Entity.Role;
import com.example.food_delivery_app.Entity.User;
import com.example.food_delivery_app.Repository.UserRepository;
import com.example.food_delivery_app.Service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userService = new UserService(userRepository);
    }

    @Test
    void testCreateUser() {
        User user = new User(1, "john", "pass", "email@test.com", "123456", Role.CLIENT, "");
        when(userRepository.save(user)).thenReturn(user);

        User result = userService.createUser(user);

        assertEquals(user, result);
        verify(userRepository).save(user);
    }

    @Test
    void testGetUserById_UserExists() {
        User user = new User(1, "john", "pass", "email@test.com", "123456", Role.CLIENT, "");
        when(userRepository.existsById(1)).thenReturn(true);
        when(userRepository.findById(1)).thenReturn(Optional.of(user));

        Optional<User> result = userService.getUserById(1);

        assertTrue(result.isPresent());
        assertEquals(user, result.get());
    }

    @Test
    void testGetUserById_UserDoesNotExist() {
        when(userRepository.existsById(1)).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> userService.getUserById(1));
    }

    @Test
    void testGetUserByUsername() {
        User user = new User(1, "john", "pass", "email@test.com", "123456", Role.CLIENT, "");
        when(userRepository.findByUsername("john")).thenReturn(Optional.of(user));

        Optional<User> result = userService.getUserByUsername("john");

        assertTrue(result.isPresent());
        assertEquals("john", result.get().getUsername());
    }

    @Test
    void testGetAllUsers() {
        List<User> users = Arrays.asList(
                new User(1, "john", "pass", "email@test.com", "123456", Role.CLIENT, ""),
                new User(2, "jane", "pass2", "email2@test.com", "654321", Role.ADMIN, "")
        );
        when(userRepository.findAll()).thenReturn(users);

        List<User> result = userService.getAllUsers();

        assertEquals(2, result.size());
        verify(userRepository).findAll();
    }

    @Test
    void testUpdateUser_UserExists() {
        User user = new User(1, "john", "pass", "email@test.com", "123456", Role.CLIENT, "");
        when(userRepository.existsById(1)).thenReturn(true);
        when(userRepository.save(user)).thenReturn(user);

        User result = userService.updateUser(1, user);

        assertEquals(user, result);
        verify(userRepository).save(user);
    }

    @Test
    void testUpdateUser_UserDoesNotExist() {
        User user = new User();
        when(userRepository.existsById(1)).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> userService.updateUser(1, user));
    }

    @Test
    void testDeleteUser_UserExists() {
        when(userRepository.existsById(1)).thenReturn(true);

        boolean result = userService.deleteUser(1);

        assertTrue(result);
        verify(userRepository).deleteById(1);
    }

    @Test
    void testDeleteUser_UserDoesNotExist() {
        when(userRepository.existsById(1)).thenReturn(false);

        boolean result = userService.deleteUser(1);

        assertFalse(result);
        verify(userRepository, never()).deleteById(1);
    }
}
