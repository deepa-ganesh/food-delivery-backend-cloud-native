package io.github.deepaganesh.fooddelivery.entity;

import io.github.deepaganesh.fooddelivery.common.OrderStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class OrderStatusHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @ManyToOne
    @JoinColumn(name = "changed_by")
    private User changedBy;

    private LocalDate changedAt;

    private String notes;
}
