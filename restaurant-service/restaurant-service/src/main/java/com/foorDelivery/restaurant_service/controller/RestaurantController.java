package com.foorDelivery.restaurant_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class RestaurantController {
    @GetMapping("/restaurant")
    public List<Map<String, Object>> getRestaurants() {
        return List.of(
                Map.of("id", 1, "name", "Dominos", "city", "Bangalore"),
                Map.of("id", 2, "name", "KFC", "city", "Hyderabad"),
                Map.of("id", 3, "name", "Burger King", "city", "Delhi")
        );
    }
}
