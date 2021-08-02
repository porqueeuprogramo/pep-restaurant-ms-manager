package com.pep.restaurant;

import com.pep.restaurant.domain.Restaurant;
import com.pep.restaurant.domain.User;

public class ApplicationDataProvider {

    public Restaurant getRestaurant(){
        return new Restaurant()
                .name("Francesinhas")
                .location("Porto")
                .capacity(100);
    }

    public User getUser(){
        return new User()
                .id("1")
                .username("joaobarbosa")
                .firstName("Joao")
                .lastName("Barbosa")
                .email("joaobarbosa@porqueeuprogramo.com")
                .password("1234");
    }

}
