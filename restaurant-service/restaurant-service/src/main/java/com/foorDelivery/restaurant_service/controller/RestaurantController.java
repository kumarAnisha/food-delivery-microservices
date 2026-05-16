package com.foorDelivery.restaurant_service.controller;

import com.foorDelivery.restaurant_service.entity.MenuItem;
import com.foorDelivery.restaurant_service.entity.Restaurant;
import com.foorDelivery.restaurant_service.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {
    @Autowired
    private RestaurantService restaurantService;

    @GetMapping
    public List<Restaurant> getRestaurants() {
        return restaurantService.getAllRestaurant();
    }

    @GetMapping("/{id}")
    public List<MenuItem> getMenuItems(@PathVariable("id")Long id){
        return restaurantService.getMenuItemById(id);
    }

    @PostMapping
    public String addRestaurant(@RequestBody Restaurant restaurant){
        restaurantService.addRestaurant(restaurant);
        return "Added restaurant";
    }
}
