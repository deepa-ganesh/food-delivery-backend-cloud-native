package io.github.deepaganesh.fooddelivery.common;

import lombok.Getter;

public enum UserRole {
    CUSTOMER ("Customer"),
    RESTAURANT_OWNER ("Restaurant Owner"),
    DELIVERER_AGENT ("Delivery Agent"),
    ADMIN ("Admin");

    @Getter
    private final String description;

    UserRole(String description) {
        this.description = description;
    }
}
