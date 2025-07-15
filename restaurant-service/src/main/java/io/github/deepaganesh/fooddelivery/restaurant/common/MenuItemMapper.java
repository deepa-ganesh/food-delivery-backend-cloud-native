package io.github.deepaganesh.fooddelivery.restaurant.common;

import io.github.deepaganesh.fooddelivery.restaurant.dto.MenuItemDTO;
import io.github.deepaganesh.fooddelivery.restaurant.entity.MenuItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MenuItemMapper {

    @Mapping(source = "restaurantId", target = "restaurant.id")
    MenuItem toEntity(MenuItemDTO dto);

    @Mapping(source = "restaurant.id", target = "restaurantId")
    MenuItemDTO toDTO(MenuItem entity);

    List<MenuItemDTO> toDTOList(List<MenuItem> entities);

    @Mapping(source = "restaurantId", target = "restaurant.id")
    void updateEntityFromDTO(MenuItemDTO menuItemDTO, @MappingTarget MenuItem menuItem);
}
