package io.github.deepaganesh.fooddelivery.order.client;

import io.github.deepaganesh.fooddelivery.common.dto.RestaurantDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "restaurant-service", path = "/restaurants")
public interface RestaurantAPIClient {

    @GetMapping("/id")
    RestaurantDTO getRestaurantById(@PathVariable("id") Long id);
}
