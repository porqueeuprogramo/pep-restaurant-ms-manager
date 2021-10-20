package com.pep.restaurant;

import com.pep.restaurant.domain.Employee;
import com.pep.restaurant.domain.Restaurant;
import com.pep.restaurant.domain.Menu;
import com.pep.restaurant.domain.User;
import com.pep.restaurant.domain.Schedule;
import com.pep.restaurant.domain.enumeration.ScheduleType;
import com.pep.restaurant.service.model.EmployeeDTO;
import com.pep.restaurant.service.model.MenuDTO;
import com.pep.restaurant.service.model.RestaurantDTO;
import com.pep.restaurant.service.model.ScheduleDTO;

import java.util.Collections;
import java.util.Set;

public class ApplicationDataProvider {


    public Restaurant getRestaurantWithEmployees(){
        return new Restaurant()
                .id(1L)
                .name("Francesinhas")
                .location("Porto")
                .capacity(100)
                .menu(getMenu())
                .employeeList(Set.of(getEmployeeWithoutRestaurantListWithId()));
    }

    public Employee getEmployeeWithId(){
        return new Employee()
                .id(1L)
                .role("CHEF");
    }

    public Employee getEmployeeWithRestaurant(){
        Employee employee = new Employee()
                .id(1L)
                .role("CHEF");
        employee.setRestaurantList(Set.of(getRestaurantWithEmployees()));
        return employee;
    }



    public Restaurant getRestaurant(){
        return new Restaurant()
                .name("Francesinhas")
                .location("Porto")
                .capacity(100)
                .menu(getMenu());
    }

    public Menu getMenu(){
        return new Menu()
                .language("PORTUGUESE");
    }

    public Employee getEmployee(){
        Employee employee = new Employee()
                .role("CHEF")
                .schedule(getSchedule());
        employee.setRestaurantList(Set.of(getRestaurant()));
        return employee;
    }

    public Schedule getSchedule(){
        return new Schedule()
                .type(ScheduleType.FULLTIME);
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

    public RestaurantDTO getRestaurantDTO(){
        return new RestaurantDTO()
                .name("Francesinhas")
                .location("Porto")
                .capacity(100)
                .menu(getMenuDTO());
    }

    public MenuDTO getMenuDTO(){
        return new MenuDTO()
                .language("PORTUGUESE");
    }

    public EmployeeDTO getEmployeeDTO(){
        EmployeeDTO employee = new EmployeeDTO()
                .role("CHEF")
                .schedule(getScheduleDTO());
        employee.setRestaurantList(Set.of(getRestaurantDTO()));
        return employee;
    }

    public ScheduleDTO getScheduleDTO(){
        return new ScheduleDTO()
                .type(ScheduleType.FULLTIME);
    }

    public Restaurant getRestaurantWithEmployeeList(){
        return new Restaurant()
                .name("Francesinhas")
                .location("Porto")
                .capacity(100)
                .menu(getMenu())
                .employeeList(Set.of(getEmployeeWithoutRestaurantList()));
    }

    public Restaurant getRestaurantWithEmployeeListOnlyWithRole(){
        return new Restaurant()
                .name("Francesinhas")
                .location("Porto")
                .capacity(100)
                .menu(getMenu())
                .employeeList(Set.of(getEmployeeWithoutRestaurantListWithId()));
    }

    public Employee getEmployeeWithoutRestaurantListAndWithoutSchedule(){
        return new Employee()
                .role("CHEF");
    }

    public Employee getEmployeeWithoutRestaurantList(){
        return new Employee()
                .role("CHEF")
                .schedule(getSchedule());
    }

    public Employee getEmployeeWithoutRestaurantListWithId(){
        return new Employee()
                .id(1L)
                .role("CHEF");
    }

    public RestaurantDTO getRestaurantDTOWithEmployeeListDTO(){
        return new RestaurantDTO()
                .name("Francesinhas")
                .location("Porto")
                .capacity(100)
                .menu(getMenuDTO())
                .employeeList(Set.of(getEmployeeDTOWithoutRestaurantListDTO()));
    }

    public EmployeeDTO getEmployeeDTOWithoutRestaurantListDTO(){
        return new EmployeeDTO()
                .role("CHEF")
                .schedule(getScheduleDTO());
    }

    public Employee getEmployeeWithoutSchedule(){
        Employee employee = new Employee()
                .role("CHEF");
        employee.setRestaurantList(Set.of(getRestaurant()));
        return employee;
    }

    public EmployeeDTO getEmployeeDTOWithoutScheduleDTO(){
        EmployeeDTO employeeDTO = new EmployeeDTO()
                .role("CHEF");
        employeeDTO.setRestaurantList(Set.of(getRestaurantDTO()));
        return employeeDTO;
    }

    public Schedule getScheduleWithEmployeeList(){
        return new Schedule()
                .type(ScheduleType.FULLTIME)
                .employeeList(Collections.singletonList(getEmployeeWithoutSchedule()));
    }

    public ScheduleDTO getScheduleDTOWithEmployeeDTOList(){
        return new ScheduleDTO()
                .type(ScheduleType.FULLTIME)
                .employeeList(Collections.singletonList(getEmployeeDTOWithoutScheduleDTO()));
    }

}
