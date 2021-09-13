package com.pep.restaurant.service;

import com.pep.restaurant.repository.EmployeeRepository;
import com.pep.restaurant.repository.MenuRepository;
import com.pep.restaurant.repository.ScheduleRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService {

    private final RestaurantService restaurantService;
    private final MenuRepository menuRepository;
    private final EmployeeRepository employeeRepository;
    private final ScheduleRepository scheduleRepository;

    @Autowired
    public TestService(final RestaurantService restaurantService,
                       final MenuRepository menuRepository,
                       final EmployeeRepository employeeRepository,
                       final ScheduleRepository scheduleRepository) {
        this.restaurantService = restaurantService;
        this.menuRepository = menuRepository;
        this.employeeRepository = employeeRepository;
        this.scheduleRepository = scheduleRepository;
    }

    @Autowired
    public void testService() {

        LoggerFactory.getLogger("TestService").error("Message Info Log");

/*
        Schedule schedule = new Schedule();
        schedule.setId(1L);
        schedule.setType(ScheduleType.FULLTIME);

        long start = System.nanoTime();

        if(schedule.getType().equals(ScheduleType.FULLTIME)){
            int typeSchedule = 1;
        }

        long end = System.nanoTime() - start;

        long ok = 1;

*/
/*
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
        employeeRepository.save(employeePersisted);*/

        //restaurantService.deleteRestaurant(1L);

    }


}
