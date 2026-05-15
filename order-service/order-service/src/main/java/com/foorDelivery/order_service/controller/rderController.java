package com.foorDelivery.order_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class rderController {
    @GetMapping("/orders")
    public List<Map<String, Object>> getOrders() {
        return List.of(
                Map.of("orderId", 101, "status", "CREATED"),
                Map.of("orderId", 102, "status", "PAID")
        );
    }
}
