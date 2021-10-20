package com.pep.restaurant.service.mapper;

import com.pep.restaurant.domain.Employee;
import com.pep.restaurant.domain.Menu;
import com.pep.restaurant.domain.Restaurant;
import com.pep.restaurant.domain.Schedule;
import com.pep.restaurant.service.model.EmployeeDTO;
import com.pep.restaurant.service.model.MenuDTO;
import com.pep.restaurant.service.model.RestaurantDTO;
import com.pep.restaurant.service.model.ScheduleDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class RestaurantMapper {

    /**
     * Map Restaurant List to Restaurant List DTO.
     * @param restaurantList restaurant List.
     * @return Restaurant List DTO.
     */
    public List<RestaurantDTO> mapRestaurantListToRestaurantDTOList(final List<Restaurant> restaurantList) {
        return restaurantList != null
                ? restaurantList
                    .stream()
                    .map(this::mapRestaurantToRestaurantDTO)
                    .collect(Collectors.toList())
                : null;
    }

    /**
     * Map Restaurant To Restaurant DTO.
     * @param restaurant restaurant.
     * @return Restaurant DTO.
     */
    public RestaurantDTO mapRestaurantToRestaurantDTO(final Restaurant restaurant) {
        return restaurant != null ?
                new RestaurantDTO()
                        .id(restaurant.getId())
                        .name(restaurant.getName())
                        .location(restaurant.getLocation())
                        .capacity(restaurant.getCapacity())
                        .menu(mapMenuToMenuDTO(restaurant.getMenu()))
                        .employeeList(mapEmployeeListToEmployeeDTOList(restaurant.getEmployeeList()))
                : null;
    }

    /**
     * Map Restaurant DTO to Restaurant.
     * @param restaurantDTO restaurant DTO.
     * @return Restaurant.
     */
    public Restaurant mapRestaurantDTOToRestaurant(final RestaurantDTO restaurantDTO) {
        return restaurantDTO != null ?
                new Restaurant()
                        .id(restaurantDTO.getId())
                        .name(restaurantDTO.getName())
                        .location(restaurantDTO.getLocation())
                        .capacity(restaurantDTO.getCapacity())
                        .menu(mapMenuDTOToMenu(restaurantDTO.getMenu()))
                        .employeeList(mapEmployeeDTOListToEmployeeList(restaurantDTO.getEmployeeList()))
                : null;
    }

    private Set<EmployeeDTO> mapEmployeeListToEmployeeDTOList(final Set<Employee> employeeList) {
        return employeeList != null
                ? employeeList
                    .stream()
                    .map(this::mapEmployeeToEmployeeDTO)
                    .collect(Collectors.toSet())
                : null;
    }

    private Set<Employee> mapEmployeeDTOListToEmployeeList(final Set<EmployeeDTO> employeeDTOList) {
        return employeeDTOList != null
                ? employeeDTOList
                    .stream()
                    .map(this::mapEmployeeDTOToEmployee)
                    .collect(Collectors.toSet())
                : null;
    }

    private EmployeeDTO mapEmployeeToEmployeeDTO(final Employee employee) {
        return employee != null ?
                new EmployeeDTO()
                        .id(employee.getId())
                        .role(employee.getRole())
                        .restaurantList(null)
                        .schedule(mapScheduleToScheduleDTO(employee.getSchedule()))
                : null;
    }

    private Employee mapEmployeeDTOToEmployee(final EmployeeDTO employeeDTO) {
        return employeeDTO != null ?
                new Employee()
                        .id(employeeDTO.getId())
                        .role(employeeDTO.getRole())
                        .restaurantList(null)
                        .schedule(mapScheduleDTOToSchedule(employeeDTO.getSchedule()))
                : null;
    }

    private Schedule mapScheduleDTOToSchedule(final ScheduleDTO scheduleDTO) {
        return scheduleDTO != null ?
                new Schedule()
                        .id(scheduleDTO.getId())
                        .type(scheduleDTO.getType())
                        .employeeList(null)
                : null;
    }

    private ScheduleDTO mapScheduleToScheduleDTO(final Schedule schedule) {
        return schedule != null ?
                new ScheduleDTO()
                        .id(schedule.getId())
                        .type(schedule.getType())
                        .employeeList(null)
                : null;
    }

    private MenuDTO mapMenuToMenuDTO(final Menu menu) {
        return menu != null ?
                new MenuDTO()
                        .id(menu.getId())
                        .language(menu.getLanguage())
                : null;
    }

    private Menu mapMenuDTOToMenu(final MenuDTO menuDTO) {
        return menuDTO != null ?
                new Menu()
                        .id(menuDTO.getId())
                        .language(menuDTO.getLanguage())
                : null;
    }

}
