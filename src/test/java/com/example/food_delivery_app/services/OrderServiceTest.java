package com.example.food_delivery_app.services;

import com.example.food_delivery_app.Entity.*;
import com.example.food_delivery_app.Repository.MenuItemRepository;
import com.example.food_delivery_app.Repository.OrderRepository;
import com.example.food_delivery_app.Repository.RestaurantRepository;
import com.example.food_delivery_app.Repository.UserRepository;
import com.example.food_delivery_app.Service.OrderService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private MenuItemRepository menuItemRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private RestaurantRepository restaurantRepository;

    @InjectMocks
    private OrderService orderService;

    private User user;
    private Restaurant restaurant;
    private MenuItem menuItem;
    private Order order;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        user = new User();
        user.setId(1);
        user.setUsername("testUser");

        restaurant = new Restaurant();
        restaurant.setId(1);
        restaurant.setRestaurantName("Test Restaurant");

        menuItem = new MenuItem();
        menuItem.setId(1);
        menuItem.setItemName("Test Item");
        menuItem.setItemPrice(10.0);

        order = new Order();
        order.setId(1);
        order.setUser(user);
        order.setRestaurant(restaurant);
        order.setMenuItems(new ArrayList<>());
        order.setStatus(Status.DRAFT);
        order.setTotalCost(0);
    }

    @Test
    public void testCreateOrder() {
        when(orderRepository.save(any(Order.class))).thenReturn(order);

        Order createdOrder = orderService.createOrder(order);

        assertNotNull(createdOrder);
        assertEquals(order, createdOrder);
        verify(orderRepository, times(1)).save(order);
    }

    @Test
    public void testGetOrderById() {
        when(orderRepository.existsById(1)).thenReturn(true);
        when(orderRepository.findById(1)).thenReturn(Optional.of(order));

        Optional<Order> foundOrder = orderService.getOrderById(1);

        assertTrue(foundOrder.isPresent());
        assertEquals(order, foundOrder.get());
    }

    @Test
    public void testGetOrderByIdThrowsExceptionIfNotFound() {
        when(orderRepository.existsById(2)).thenReturn(false);

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            orderService.getOrderById(2);
        });

        assertEquals("Order not found with ID: 2", exception.getMessage());
    }

    @Test
    public void testAddItemToOrder() {
        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        when(menuItemRepository.findById(1)).thenReturn(Optional.of(menuItem));
        when(orderRepository.findByUserAndStatus(user, Status.DRAFT)).thenReturn(Optional.of(order));
        when(orderRepository.save(any(Order.class))).thenReturn(order);

        Order updatedOrder = orderService.addItemToOrder(1, 1);

        assertNotNull(updatedOrder);
        assertTrue(updatedOrder.getMenuItems().contains(menuItem));
        assertEquals(10.0, updatedOrder.getTotalCost());
        verify(orderRepository, times(1)).save(updatedOrder);
    }

    @Test
    public void testCreateOrderForUserIdAndRestaurant() {
        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        when(restaurantRepository.findById(1)).thenReturn(Optional.of(restaurant));

        Order savedOrder = new Order();  // returned by the repo
        when(orderRepository.save(any(Order.class))).thenReturn(savedOrder);

        Order result = orderService.createOrderForUserIdAndRestaurant(1, 1);

        assertNotNull(result);
        verify(orderRepository).save(any(Order.class));

        ArgumentCaptor<Order> captor = ArgumentCaptor.forClass(Order.class);
        verify(orderRepository).save(captor.capture());

        Order capturedOrder = captor.getValue();
        assertEquals(user, capturedOrder.getUser());
        assertEquals(restaurant, capturedOrder.getRestaurant());
        assertEquals(Status.DRAFT, capturedOrder.getStatus());
        assertEquals(0.0, capturedOrder.getTotalCost());
    }

    @Test
    public void testFinalizeOrder() {
        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        when(orderRepository.findByUserAndStatus(user, Status.DRAFT)).thenReturn(Optional.of(order));
        when(orderRepository.save(any(Order.class))).thenReturn(order);

        Order finalizedOrder = orderService.finalizeOrder(1);

        assertNotNull(finalizedOrder);
        assertEquals(Status.PENDING, finalizedOrder.getStatus());
        verify(orderRepository, times(1)).save(finalizedOrder);
    }

    @Test
    public void testGetDraftOrder() {
        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        when(orderRepository.findByUserAndStatus(user, Status.DRAFT)).thenReturn(Optional.of(order));

        Order draftOrder = orderService.getDraftOrder(1);

        assertNotNull(draftOrder);
        assertEquals(Status.DRAFT, draftOrder.getStatus());
    }

    @Test
    public void testDeleteOrder() {
        when(orderRepository.existsById(1)).thenReturn(true);

        boolean isDeleted = orderService.deleteOrder(1);

        assertTrue(isDeleted);
        verify(orderRepository, times(1)).deleteById(1);
    }

    @Test
    public void testDeleteOrderNotFound() {
        when(orderRepository.existsById(2)).thenReturn(false);

        boolean isDeleted = orderService.deleteOrder(2);

        assertFalse(isDeleted);
        verify(orderRepository, times(0)).deleteById(2);
    }
}