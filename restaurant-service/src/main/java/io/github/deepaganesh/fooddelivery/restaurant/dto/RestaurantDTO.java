package io.github.deepaganesh.fooddelivery.restaurant.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RestaurantDTO {
    private Long id;
    private String name;
    private String address;
    private String city;
    private Double latitude;
    private Double longitude;
    private Boolean active;
    private Long ownerId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
