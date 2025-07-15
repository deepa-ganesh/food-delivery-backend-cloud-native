package io.github.deepaganesh.fooddelivery.restaurant.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class MenuItemDTO {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Boolean isAvailable;
    private Long restaurantId;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
