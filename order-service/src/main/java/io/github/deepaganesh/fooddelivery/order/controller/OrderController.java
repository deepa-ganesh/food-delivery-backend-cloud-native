package io.github.deepaganesh.fooddelivery.order.controller;

import io.github.deepaganesh.fooddelivery.order.dto.OrderRequest;
import io.github.deepaganesh.fooddelivery.order.entity.Order;
import io.github.deepaganesh.fooddelivery.order.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody @Valid OrderRequest orderRequest) {
        return new ResponseEntity<>(orderService.createOrder(orderRequest), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id,
                                              @RequestParam("status") String status) {
        Order order = orderService.getOrderById(id);

        return new ResponseEntity<>(order, HttpStatus.OK);
    }
}
