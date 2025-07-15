package io.github.deepaganesh.fooddelivery.common.dto;

import lombok.Getter;

public enum OrderStatus {
    CREATED ("Order Created"),
    ACCEPTED ("Order Accepted"),
    PREPARING ("Restaurant is preparing your food"),
    READY_FOR_PICKUP ("Order is ready for pickup"),
    PICKED_UP ("Delivery Agent has picked up your order"),
    DELIVERED ("Order is delivered"),
    CANCELLED ("Order is cancelled");

    @Getter
    private final String description;

    OrderStatus(String description) {
        this.description = description;
    }
}
