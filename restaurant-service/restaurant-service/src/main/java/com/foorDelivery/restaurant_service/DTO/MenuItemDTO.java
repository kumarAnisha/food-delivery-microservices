package com.foorDelivery.restaurant_service.DTO;

import com.foorDelivery.restaurant_service.entity.MenuItem;
import com.foorDelivery.restaurant_service.entity.Restaurant;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuItemDTO {
    private Long id;
    private String name;
    private Double price;
    private String description;
    private String imageUrl;

    // constructor
    public MenuItemDTO(MenuItem menuItem) {
        this.id = menuItem.getId();
        this.name = menuItem.getName();
        this.price = menuItem.getPrice();
        this.description = menuItem.getDescription();
        this.imageUrl = menuItem.getImageUrl();
    }

}
