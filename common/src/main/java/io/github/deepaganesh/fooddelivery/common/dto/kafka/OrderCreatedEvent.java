package io.github.deepaganesh.fooddelivery.common.dto.kafka;

import io.github.deepaganesh.fooddelivery.common.dto.OrderItem;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderCreatedEvent {
    private Long orderId;
    private Long customerId;
    private Long restaurantId;
    private BigDecimal totalPrice;
    private List<OrderItem> items;
}
