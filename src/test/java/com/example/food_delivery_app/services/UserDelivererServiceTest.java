package com.example.food_delivery_app.services;

import com.example.food_delivery_app.Entity.UserDeliverer;
import com.example.food_delivery_app.Repository.UserDelivererRepository;
import com.example.food_delivery_app.Service.UserDelivererService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserDelivererServiceTest {

    @Mock
    private UserDelivererRepository userDelivererRepository;

    @InjectMocks
    private UserDelivererService userDelivererService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateUserDeliverer() {
        UserDeliverer user = new UserDeliverer();
        when(userDelivererRepository.save(user)).thenReturn(user);

        UserDeliverer result = userDelivererService.createUserDeliverer(user);
        assertEquals(user, result);
        verify(userDelivererRepository, times(1)).save(user);
    }

    @Test
    void testGetUserDelivererByIdFound() {
        UserDeliverer user = new UserDeliverer();
        when(userDelivererRepository.existsById(1)).thenReturn(true);
        when(userDelivererRepository.findById(1)).thenReturn(Optional.of(user));

        Optional<UserDeliverer> result = userDelivererService.getUserDelivererById(1);
        assertTrue(result.isPresent());
        assertEquals(user, result.get());
    }

    @Test
    void testGetUserDelivererByIdNotFound() {
        when(userDelivererRepository.existsById(1)).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> {
            userDelivererService.getUserDelivererById(1);
        });
    }

    @Test
    void testGetAllUserDeliverers() {
        List<UserDeliverer> list = Arrays.asList(new UserDeliverer(), new UserDeliverer());
        when(userDelivererRepository.findAll()).thenReturn(list);

        List<UserDeliverer> result = userDelivererService.getAllUserDeliverers();
        assertEquals(2, result.size());
        verify(userDelivererRepository, times(1)).findAll();
    }

    @Test
    void testUpdateUserDelivererFound() {
        UserDeliverer user = new UserDeliverer();
        when(userDelivererRepository.existsById(1)).thenReturn(true);
        when(userDelivererRepository.save(user)).thenReturn(user);

        UserDeliverer result = userDelivererService.updateUserDeliverer(1, user);
        assertEquals(user, result);
        verify(userDelivererRepository, times(1)).save(user);
    }

    @Test
    void testUpdateUserDelivererNotFound() {
        UserDeliverer user = new UserDeliverer();
        when(userDelivererRepository.existsById(1)).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> {
            userDelivererService.updateUserDeliverer(1, user);
        });
    }

    @Test
    void testDeleteUserDelivererSuccess() {
        when(userDelivererRepository.existsById(1)).thenReturn(true);
        boolean result = userDelivererService.deleteUserDeliverer(1);
        assertTrue(result);
        verify(userDelivererRepository, times(1)).deleteById(1);
    }

    @Test
    void testDeleteUserDelivererNotFound() {
        when(userDelivererRepository.existsById(1)).thenReturn(false);
        boolean result = userDelivererService.deleteUserDeliverer(1);
        assertFalse(result);
    }
}
