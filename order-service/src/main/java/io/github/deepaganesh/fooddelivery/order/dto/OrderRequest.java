package io.github.deepaganesh.fooddelivery.order.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class OrderRequest {

    @NotNull(message = "Customer ID cannot be null")
    private Long customerId;

    @NotNull(message = "Restaurant ID cannot be null")
    private Long restaurantId;

    @NotBlank(message = "Delivery Address cannot be blank")
    private String deliveryAddress;

    @NotEmpty(message = "Items should have at least one item")
    private List<@Valid OrderItemRequest> items;
}
