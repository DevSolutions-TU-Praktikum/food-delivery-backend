package com.example.food_delivery_app.services;

import com.example.food_delivery_app.Entity.MenuItem;
import com.example.food_delivery_app.Repository.MenuItemRepository;
import com.example.food_delivery_app.Service.MenuItemService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.*;
import org.mockito.*;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MenuItemServiceTest {
    @Mock
    private MenuItemRepository menuItemRepository;

    @InjectMocks
    private MenuItemService menuItemService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateMenuItem() {
        MenuItem item = new MenuItem();
        item.setItemName("Burger");

        when(menuItemRepository.save(item)).thenReturn(item);

        MenuItem result = menuItemService.createMenuItem(item);
        assertEquals("Burger", result.getItemName());

        verify(menuItemRepository, times(1)).save(item);
    }

    @Test
    public void testGetMenuItemsById_found() {
        MenuItem item = new MenuItem();
        item.setId(1);
        item.setItemName("Fries");

        when(menuItemRepository.existsById(1)).thenReturn(true);
        when(menuItemRepository.findById(1)).thenReturn(Optional.of(item));

        Optional<MenuItem> result = menuItemService.getMenuItemById(1);

        assertTrue(result.isPresent());
        assertEquals("Fries", result.get().getItemName());
    }

    @Test
    public void testGetMenuItemsById_notFound_throwsException() {
        when(menuItemRepository.existsById(999)).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> {
            menuItemService.getMenuItemById(999);
        });
    }

    @Test
    public void testGetAllMenuItems() {
        List<MenuItem> items = List.of(new MenuItem(), new MenuItem());
        when(menuItemRepository.findAll()).thenReturn(items);

        List<MenuItem> result = menuItemService.getAllMenuItems();
        assertEquals(2, result.size());
    }

    @Test
    public void testUpdateMenuItem_success() {
        MenuItem updated = new MenuItem();
        updated.setId(1);
        updated.setItemName("Updated Name");

        when(menuItemRepository.existsById(1)).thenReturn(true);
        when(menuItemRepository.save(updated)).thenReturn(updated);

        MenuItem result = menuItemService.updateMenuItem(1, updated);
        assertEquals("Updated Name", result.getItemName());
    }

    @Test
    public void testDeleteMenuItem_found() {
        when(menuItemRepository.existsById(1)).thenReturn(true);

        boolean result = menuItemService.deleteMenuItem(1);

        assertTrue(result);
        verify(menuItemRepository, times(1)).deleteById(1);
    }

    @Test
    public void testDeleteMenuItem_notFound() {
        when(menuItemRepository.existsById(1)).thenReturn(false);

        boolean result = menuItemService.deleteMenuItem(1);
        assertFalse(result);
    }
}
