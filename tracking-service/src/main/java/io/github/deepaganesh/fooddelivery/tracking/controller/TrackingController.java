package io.github.deepaganesh.fooddelivery.tracking.controller;

import io.github.deepaganesh.fooddelivery.tracking.dto.TrackingInfoDTO;
import io.github.deepaganesh.fooddelivery.tracking.service.TrackingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tracking")
public class TrackingController {

    @Autowired
    private TrackingService service;

    @GetMapping("/{orderId}")
    public TrackingInfoDTO getTracking(@PathVariable Long orderId) {
        return service.getTrackingByOrderId(orderId);
    }
}
