package com.pep.restaurant.service;

import com.pep.restaurant.domain.Employee;
import com.pep.restaurant.domain.Menu;
import com.pep.restaurant.domain.Restaurant;
import com.pep.restaurant.domain.Schedule;
import com.pep.restaurant.repository.EmployeeRepository;
import com.pep.restaurant.repository.MenuRepository;
import com.pep.restaurant.repository.ScheduleRepository;
import liquibase.pro.packaged.S;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class TestService {

    private final RestaurantService restaurantService;
    private final MenuRepository menuRepository;
    private final EmployeeRepository employeeRepository;
    private final ScheduleRepository scheduleRepository;

    @Autowired
    public TestService(RestaurantService restaurantService, MenuRepository menuRepository,
                       EmployeeRepository employeeRepository, ScheduleRepository scheduleRepository) {
        this.restaurantService = restaurantService;
        this.menuRepository = menuRepository;
        this.employeeRepository = employeeRepository;
        this.scheduleRepository = scheduleRepository;
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
        Employee employeePersisted = employeeRepository.save(employee);

        restaurantPersited.addEmployee(employee);
        restaurantService.editRestaurant(1, restaurantPersited);

        Schedule schedule = new Schedule();
        schedule.setType("FULL-TIME");
        schedule.setEmployeeList(Collections.singletonList(employee));
        scheduleRepository.save(schedule);

        employee.setScheduleList(Collections.singletonList(schedule));
        employeeRepository.save(employeePersisted);

        //restaurantService.deleteRestaurant(1L);

    }


}
