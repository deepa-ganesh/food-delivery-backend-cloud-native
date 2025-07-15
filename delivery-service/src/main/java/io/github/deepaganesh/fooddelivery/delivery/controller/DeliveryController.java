package io.github.deepaganesh.fooddelivery.delivery.controller;

import io.github.deepaganesh.fooddelivery.delivery.dto.DeliveryDTO;
import io.github.deepaganesh.fooddelivery.delivery.dto.DeliveryStatusUpdate;
import io.github.deepaganesh.fooddelivery.delivery.entity.Delivery;
import io.github.deepaganesh.fooddelivery.delivery.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/deliveries")
public class DeliveryController {

    @Autowired
    private DeliveryService deliveryService;

    @GetMapping
    public ResponseEntity<List<DeliveryDTO>> getAllDeliveries() {
        return new ResponseEntity<>(deliveryService.getAllDeliveries(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeliveryDTO> getDeliveryById(@PathVariable Long id) {
        return new ResponseEntity<>(deliveryService.getDeliveryById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Void> updateDeliveryStatus(@PathVariable Long id,
                                                     @RequestBody DeliveryStatusUpdate deliveryStatusUpdate) {
        deliveryService.updateDeliveryStatus(id, deliveryStatusUpdate.getDeliveryStatus());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
