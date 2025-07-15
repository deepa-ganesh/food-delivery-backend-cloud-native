package io.github.deepaganesh.fooddelivery.controller;

import io.github.deepaganesh.fooddelivery.dto.OrderRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @PostMapping(
            value = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_XML_VALUE,
            headers = "Content-Type=" + MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<String> createOrder(
            @PathVariable("id") Integer id,
            @RequestBody OrderRequest orderRequest,
            @RequestParam(value = "active", required = true) Boolean active) {

    }
}
