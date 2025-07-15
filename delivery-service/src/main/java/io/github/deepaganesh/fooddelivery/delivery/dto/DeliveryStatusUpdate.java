package io.github.deepaganesh.fooddelivery.delivery.dto;

import io.github.deepaganesh.fooddelivery.common.dto.DeliveryStatus;

public class DeliveryStatusUpdate {

    private DeliveryStatus deliveryStatus;

    public DeliveryStatus getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(DeliveryStatus deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }
}
