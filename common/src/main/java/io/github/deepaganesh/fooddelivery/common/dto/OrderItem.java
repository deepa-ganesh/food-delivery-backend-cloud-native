package io.github.deepaganesh.fooddelivery.common.dto;

import lombok.Data;

@Data
public class OrderItem {

    private Long menuItem;

    private Integer quantity;
}
