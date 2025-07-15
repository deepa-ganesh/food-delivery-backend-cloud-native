package io.github.deepaganesh.fooddelivery.order.entity;

import io.github.deepaganesh.fooddelivery.common.dto.OrderStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long customerId;

    private Long restaurantId;

    @Column(columnDefinition = "TEXT")
    private String deliveryAddress;

    private BigDecimal totalPrice;

    @Enumerated(EnumType.STRING)
    private OrderStatus currentStatus;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
