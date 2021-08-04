package com.pep.restaurant.service;

import com.pep.restaurant.domain.Employee;
import com.pep.restaurant.domain.Menu;
import com.pep.restaurant.domain.Restaurant;
import com.pep.restaurant.repository.EmployeeRepository;
import com.pep.restaurant.repository.MenuRepository;
import liquibase.pro.packaged.E;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService {

    private final RestaurantService restaurantService;
    private final MenuRepository menuRepository;
    private final EmployeeRepository employeeRepository;

    @Autowired
    public TestService(RestaurantService restaurantService, MenuRepository menuRepository,
                       EmployeeRepository employeeRepository) {
        this.restaurantService = restaurantService;
        this.menuRepository = menuRepository;
        this.employeeRepository = employeeRepository;
    }

    @Autowired
    public void testService() {

        Menu menu = new Menu();
        menu.setLanguage("Portuguese");
        menuRepository.save(menu);

        Restaurant restaurant = new Restaurant()
                .name("Francesinhas")
                .location("Porto")
                .capacity(100);

        restaurant.setMenu(menu);
        Restaurant restaurantPersited = restaurantService.createRestaurant(restaurant);

        Employee employee = new Employee();
        employee.setRole("CHEF");
        employee.setRestaurant(restaurantPersited);
        employeeRepository.save(employee);

        restaurantPersited.addEmployee(employee);
        restaurantService.editRestaurant(1, restaurantPersited);


        //restaurantService.deleteRestaurant(1L);

    }


}
