package io.github.deepaganesh.fooddelivery.restaurant.service.impl;

import io.github.deepaganesh.fooddelivery.common.exception.ResourceNotFoundException;
import io.github.deepaganesh.fooddelivery.restaurant.common.MenuItemMapper;
import io.github.deepaganesh.fooddelivery.restaurant.dto.MenuItemDTO;
import io.github.deepaganesh.fooddelivery.restaurant.entity.MenuItem;
import io.github.deepaganesh.fooddelivery.restaurant.repository.MenuItemRepository;
import io.github.deepaganesh.fooddelivery.restaurant.service.MenuItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuItemServiceImpl implements MenuItemService {

    private final MenuItemRepository menuItemRepository;

    private final MenuItemMapper menuItemMapper;

    @Override
    public MenuItemDTO createMenuItem(MenuItemDTO dto) {
        MenuItem menuItem = menuItemMapper.toEntity(dto);
        return menuItemMapper.toDTO(menuItemRepository.save(menuItem));
    }

    @Override
    public List<MenuItemDTO> getAllMenuItems() {
        return menuItemMapper.toDTOList(menuItemRepository.findAll());
    }

    @Override
    public List<MenuItemDTO> getMenuItemsByRestaurantId(Long restaurantId) {
        return menuItemMapper.toDTOList(menuItemRepository.findByRestaurantId(restaurantId));
    }

    @Override
    public MenuItemDTO getMenuItemById(Long id) {
        MenuItem menuItem = findById(id);
        return menuItemMapper.toDTO(menuItem);
    }

    @Override
    public MenuItemDTO updateMenuItem(Long id, MenuItemDTO menuItemDTO) {
        MenuItem menuItem = findById(id);
        menuItemMapper.updateEntityFromDTO(menuItemDTO, menuItem);
        return menuItemMapper.toDTO(menuItemRepository.save(menuItem));
    }

    @Override
    public void deleteMenuItem(Long id) {
        boolean exists = menuItemRepository.existsById(id);

        if (!exists) {
            throw new ResourceNotFoundException("Menu item not found with ID: " + id);
        }

        menuItemRepository.deleteById(id);
    }

    private MenuItem findById(Long id) {
        return menuItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Menu item not found with ID: " + id));
    }
}
