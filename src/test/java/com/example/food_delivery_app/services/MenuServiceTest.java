package com.example.food_delivery_app.services;

import com.example.food_delivery_app.Entity.Menu;
import com.example.food_delivery_app.Entity.MenuItem;
import com.example.food_delivery_app.Entity.Restaurant;
import com.example.food_delivery_app.Repository.MenuItemRepository;
import com.example.food_delivery_app.Repository.MenuRepository;
import com.example.food_delivery_app.Repository.RestaurantRepository;
import com.example.food_delivery_app.Service.MenuService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MenuServiceTest {

    @Mock
    private MenuRepository menuRepository;

    @Mock
    private RestaurantRepository restaurantRepository;

    @Mock
    private MenuItemRepository menuItemRepository;

    @InjectMocks
    private MenuService menuService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getMenuItemsByRestaurantId_shouldReturnItems() {
        Menu menu = new Menu();
        MenuItem item1 = new MenuItem();
        MenuItem item2 = new MenuItem();
        menu.setMenuItems(List.of(item1, item2));

        when(menuRepository.findByRestaurantId(1)).thenReturn(List.of(menu));

        List<MenuItem> result = menuService.getMenuItemsByRestaurantId(1);

        assertEquals(2, result.size());
    }

    @Test
    void createMenu_shouldCreateMenu_givenValidRestaurantId() {
        Restaurant restaurant = new Restaurant();
        when(restaurantRepository.findById(1)).thenReturn(Optional.of(restaurant));
        when(menuRepository.save(any(Menu.class))).thenAnswer(i -> i.getArgument(0));

        Menu result = menuService.createMenu(1);

        assertNotNull(result);
        assertEquals("Default description", result.getMenuDescription());
        assertEquals(restaurant, result.getRestaurant());
    }

    @Test
    void createMenu_shouldThrow_whenRestaurantNotFound() {
        when(restaurantRepository.findById(99)).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> menuService.createMenu(99));
    }

    @Test
    void getMenuById_shouldReturnMenu_whenExists() {
        Menu menu = new Menu();
        when(menuRepository.existsById(1)).thenReturn(true);
        when(menuRepository.findById(1)).thenReturn(Optional.of(menu));

        Optional<Menu> result = menuService.getMenuById(1);

        assertTrue(result.isPresent());
    }

    @Test
    void getMenuById_shouldThrow_whenNotFound() {
        when(menuRepository.existsById(2)).thenReturn(false);
        assertThrows(EntityNotFoundException.class, () -> menuService.getMenuById(2));
    }

    @Test
    void getAllMenus_shouldReturnMenus() {
        List<Menu> menus = List.of(new Menu(), new Menu());
        when(menuRepository.findAll()).thenReturn(menus);

        List<Menu> result = menuService.getAllMenus();
        assertEquals(2, result.size());
    }

    @Test
    void updateMenu_shouldUpdate_whenExists() {
        Menu menu = new Menu();
        when(menuRepository.existsById(1)).thenReturn(true);
        when(menuRepository.save(menu)).thenReturn(menu);

        Menu result = menuService.updateMenu(1, menu);
        assertEquals(menu, result);
    }

    @Test
    void updateMenu_shouldThrow_whenNotFound() {
        Menu menu = new Menu();
        when(menuRepository.existsById(1)).thenReturn(false);
        assertThrows(EntityNotFoundException.class, () -> menuService.updateMenu(1, menu));
    }

    @Test
    void deleteMenu_shouldReturnTrue_whenExists() {
        when(menuRepository.existsById(1)).thenReturn(true);
        boolean result = menuService.deleteMenu(1);
        assertTrue(result);
        verify(menuRepository).deleteById(1);
    }

    @Test
    void deleteMenu_shouldReturnFalse_whenNotFound() {
        when(menuRepository.existsById(1)).thenReturn(false);
        boolean result = menuService.deleteMenu(1);
        assertFalse(result);
    }

    @Test
    void addItemToMenu_shouldAddItem_whenValid() {
        MenuItem item = new MenuItem();
        Restaurant restaurant = new Restaurant();
        Menu menu = new Menu();
        menu.setMenuItems(new ArrayList<>());

        when(menuItemRepository.findById(1)).thenReturn(Optional.of(item));
        when(restaurantRepository.findById(2)).thenReturn(Optional.of(restaurant));
        when(menuRepository.findByRestaurant(restaurant)).thenReturn(Optional.of(menu));
        when(menuRepository.save(menu)).thenReturn(menu);

        Menu result = menuService.addItemToMenu(1, 2);

        assertEquals(1, result.getMenuItems().size());
        assertEquals(menu, item.getMenu());
    }

    @Test
    void addItemToMenu_shouldThrow_whenMenuItemNotFound() {
        when(menuItemRepository.findById(1)).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> menuService.addItemToMenu(1, 2));
    }

    @Test
    void addItemToMenu_shouldThrow_whenRestaurantNotFound() {
        MenuItem item = new MenuItem();
        when(menuItemRepository.findById(1)).thenReturn(Optional.of(item));
        when(restaurantRepository.findById(2)).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> menuService.addItemToMenu(1, 2));
    }

    @Test
    void addItemToMenu_shouldThrow_whenMenuNotFound() {
        MenuItem item = new MenuItem();
        Restaurant restaurant = new Restaurant();
        when(menuItemRepository.findById(1)).thenReturn(Optional.of(item));
        when(restaurantRepository.findById(2)).thenReturn(Optional.of(restaurant));
        when(menuRepository.findByRestaurant(restaurant)).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> menuService.addItemToMenu(1, 2));
    }
}
