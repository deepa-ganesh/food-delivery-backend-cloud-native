package io.github.deepaganesh.fooddelivery.delivery.service;

import io.github.deepaganesh.fooddelivery.common.dto.DeliveryStatus;
import io.github.deepaganesh.fooddelivery.common.dto.kafka.OrderCreatedEvent;
import io.github.deepaganesh.fooddelivery.delivery.dto.DeliveryDTO;

import java.util.List;

public interface DeliveryService {

    void createNewOrder(OrderCreatedEvent event);

    List<DeliveryDTO> getAllDeliveries();

    DeliveryDTO getDeliveryById(Long id);

    void updateDeliveryStatus(Long id, DeliveryStatus status);
}
