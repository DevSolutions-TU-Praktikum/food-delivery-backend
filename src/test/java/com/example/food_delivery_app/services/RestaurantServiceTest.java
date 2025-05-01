package com.example.food_delivery_app.services;

import com.example.food_delivery_app.Entity.Restaurant;
import com.example.food_delivery_app.Repository.RestaurantRepository;
import com.example.food_delivery_app.Service.RestaurantService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RestaurantServiceTest {

    @Mock
    private RestaurantRepository restaurantRepository;

    @InjectMocks
    private RestaurantService restaurantService;

    private Restaurant sampleRestaurant;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        sampleRestaurant = new Restaurant();
        sampleRestaurant.setId(1);
        sampleRestaurant.setRestaurantName("Testaurant");
    }

    @Test
    void testCreateRestaurant() {
        when(restaurantRepository.save(sampleRestaurant)).thenReturn(sampleRestaurant);

        Restaurant result = restaurantService.createRestaurant(sampleRestaurant);

        assertEquals(sampleRestaurant, result);
        verify(restaurantRepository).save(sampleRestaurant);
    }

    @Test
    void testGetRestaurantByIdFound() {
        when(restaurantRepository.existsById(1)).thenReturn(true);
        when(restaurantRepository.findById(1)).thenReturn(Optional.of(sampleRestaurant));

        Optional<Restaurant> result = restaurantService.getRestaurantById(1);

        assertTrue(result.isPresent());
        assertEquals(sampleRestaurant, result.get());
    }

    @Test
    void testGetRestaurantByIdNotFound() {
        when(restaurantRepository.existsById(1)).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> restaurantService.getRestaurantById(1));
    }

    @Test
    void testGetAllRestaurants() {
        List<Restaurant> restaurants = List.of(sampleRestaurant);
        when(restaurantRepository.findAll()).thenReturn(restaurants);

        List<Restaurant> result = restaurantService.getAllRestaurants();

        assertEquals(1, result.size());
        assertEquals("Testaurant", result.get(0).getRestaurantName());
    }

    @Test
    void testUpdateRestaurantFound() {
        when(restaurantRepository.existsById(1)).thenReturn(true);
        when(restaurantRepository.save(sampleRestaurant)).thenReturn(sampleRestaurant);

        Restaurant result = restaurantService.updateRestaurant(1, sampleRestaurant);

        assertEquals(sampleRestaurant, result);
        verify(restaurantRepository).save(sampleRestaurant);
    }

    @Test
    void testUpdateRestaurantNotFound() {
        when(restaurantRepository.existsById(1)).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> restaurantService.updateRestaurant(1, sampleRestaurant));
    }

    @Test
    void testDeleteRestaurantExists() {
        when(restaurantRepository.existsById(1)).thenReturn(true);

        boolean result = restaurantService.deleteRestaurant(1);

        assertTrue(result);
        verify(restaurantRepository).deleteById(1);
    }

    @Test
    void testDeleteRestaurantNotExists() {
        when(restaurantRepository.existsById(1)).thenReturn(false);

        boolean result = restaurantService.deleteRestaurant(1);

        assertFalse(result);
        verify(restaurantRepository, never()).deleteById(anyInt());
    }
}
