package com.pep.restaurant.service;

import com.pep.restaurant.domain.Menu;
import com.pep.restaurant.domain.Restaurant;
import com.pep.restaurant.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService {

    private final RestaurantService restaurantService;
    private final MenuRepository menuRepository;

    @Autowired
    public TestService(RestaurantService restaurantService, MenuRepository menuRepository) {
        this.restaurantService = restaurantService;
        this.menuRepository = menuRepository;
    }

    @Autowired
    public void testService(){

        /*Menu menu = new Menu();
        menu.setLanguage("Portuguese");
        menuRepository.save(menu);

        Restaurant restaurant = new Restaurant()
                  .name("Francesinhas")
                  .location("Porto")
                  .capacity(100);

        restaurant.setMenu(menu);

        restaurantService.createRestaurant(restaurant);*/

        restaurantService.deleteRestaurant(1L);

    }


}
