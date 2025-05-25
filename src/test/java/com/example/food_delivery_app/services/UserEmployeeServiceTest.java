package com.example.food_delivery_app.services;

import com.example.food_delivery_app.Entity.UserEmployee;
import com.example.food_delivery_app.Repository.UserEmployeeRepository;
import com.example.food_delivery_app.Service.UserEmployeeService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserEmployeeServiceTest {

    @Mock
    private UserEmployeeRepository userEmployeeRepository;

    @InjectMocks
    private UserEmployeeService userEmployeeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateUserEmployee() {
        UserEmployee employee = new UserEmployee();
        when(userEmployeeRepository.save(employee)).thenReturn(employee);

        UserEmployee result = userEmployeeService.createUserEmployee(employee);

        assertEquals(employee, result);
        verify(userEmployeeRepository).save(employee);
    }

    @Test
    void testGetUserEmployeeByIdFound() {
        UserEmployee employee = new UserEmployee();
        when(userEmployeeRepository.existsById(1)).thenReturn(true);
        when(userEmployeeRepository.findById(1)).thenReturn(Optional.of(employee));

        Optional<UserEmployee> result = userEmployeeService.getUserEmployeeById(1);

        assertTrue(result.isPresent());
        assertEquals(employee, result.get());
    }

    @Test
    void testGetUserEmployeeByIdNotFound() {
        when(userEmployeeRepository.existsById(1)).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> userEmployeeService.getUserEmployeeById(1));
    }

    @Test
    void testGetAllUserEmployees() {
        List<UserEmployee> list = List.of(new UserEmployee(), new UserEmployee());
        when(userEmployeeRepository.findAll()).thenReturn(list);

        List<UserEmployee> result = userEmployeeService.getAllUserEmployees();

        assertEquals(2, result.size());
    }

    @Test
    void testUpdateUserEmployeeFound() {
        UserEmployee employee = new UserEmployee();
        when(userEmployeeRepository.existsById(1)).thenReturn(true);
        when(userEmployeeRepository.save(employee)).thenReturn(employee);

        UserEmployee result = userEmployeeService.updateUserEmployee(1, employee);

        assertEquals(employee, result);
        verify(userEmployeeRepository).save(employee);
    }

    @Test
    void testUpdateUserEmployeeNotFound() {
        UserEmployee employee = new UserEmployee();
        when(userEmployeeRepository.existsById(1)).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> userEmployeeService.updateUserEmployee(1, employee));
    }

    @Test
    void testDeleteUserEmployeeFound() {
        when(userEmployeeRepository.existsById(1)).thenReturn(true);

        boolean result = userEmployeeService.deleteUserEmployee(1);

        assertTrue(result);
        verify(userEmployeeRepository).deleteById(1);
    }

    @Test
    void testDeleteUserEmployeeNotFound() {
        when(userEmployeeRepository.existsById(1)).thenReturn(false);

        boolean result = userEmployeeService.deleteUserEmployee(1);

        assertFalse(result);
        verify(userEmployeeRepository, never()).deleteById(1);
    }
}
