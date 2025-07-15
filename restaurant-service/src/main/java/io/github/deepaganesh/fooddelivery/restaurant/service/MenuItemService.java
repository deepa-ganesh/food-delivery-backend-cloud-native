package io.github.deepaganesh.fooddelivery.restaurant.service;

import io.github.deepaganesh.fooddelivery.restaurant.dto.MenuItemDTO;

import java.util.List;

public interface MenuItemService {

    MenuItemDTO createMenuItem(MenuItemDTO dto);

    List<MenuItemDTO> getAllMenuItems();

    List<MenuItemDTO> getMenuItemsByRestaurantId(Long restaurantId);

    MenuItemDTO getMenuItemById(Long id);

    MenuItemDTO updateMenuItem(Long id, MenuItemDTO dto);

    void deleteMenuItem(Long id);
}
