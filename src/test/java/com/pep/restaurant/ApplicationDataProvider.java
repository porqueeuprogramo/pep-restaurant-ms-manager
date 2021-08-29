package com.pep.restaurant;

import com.pep.restaurant.domain.Employee;
import com.pep.restaurant.domain.Restaurant;
import com.pep.restaurant.domain.Menu;
import com.pep.restaurant.domain.User;
import com.pep.restaurant.domain.Schedule;

public class ApplicationDataProvider {

    public Restaurant getRestaurant(){
        return new Restaurant()
                .name("Francesinhas")
                .location("Porto")
                .capacity(100);
    }

    public Menu getMenu(){
        return new Menu()
                .language("PORTUGUESE");
    }

    public Employee getEmployee(){
        return new Employee()
                .role("CHEF")
                .restaurant(getRestaurant());
    }

    public Schedule getSchedule(){
        return new Schedule()
                .type("FULL-TIME");
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
