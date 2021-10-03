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
import java.util.stream.Collectors;

@Component
public class EmployeeMapper {

    /**
     * Map Employee List to Employee DTO List.
     * @param employeeList employee List.
     * @return Employee List DTO.
     */
    public List<EmployeeDTO> mapEmployeeListToEmployeeDTOList(final List<Employee> employeeList) {
        return employeeList != null
                ? employeeList
                .stream()
                .map(this::mapEmployeeToEmployeeDTO)
                .collect(Collectors.toList())
                : null;
    }

    /**
     * Map employee to Employee DTO.
     * @param employee employee.
     * @return Employee DTO.
     */
    public EmployeeDTO mapEmployeeToEmployeeDTO(final Employee employee) {
        return employee != null ?
                new EmployeeDTO()
                        .id(employee.getId())
                        .role(employee.getRole())
                        .restaurantList(mapRestaurantListToRestaurantDTOList(employee.getRestaurantList()))
                        .schedule(mapScheduleToScheduleDTO(employee.getSchedule()))
                : null;

    }

    /**
     * Map Employee DTO to Employee.
     * @param employeeDTO employee DTO.
     * @return employee.
     */
    public Employee mapEmployeeDTOToEmployee(final EmployeeDTO employeeDTO) {
        return employeeDTO != null ?
                new Employee()
                        .id(employeeDTO.getId())
                        .role(employeeDTO.getRole())
                        .restaurantList(mapRestaurantDTOListToRestaurantList(employeeDTO.getRestaurantList()))
                        .schedule(mapScheduleDTOToSchedule(employeeDTO.getSchedule()))
                : null;
    }

    private List<RestaurantDTO> mapRestaurantListToRestaurantDTOList(final List<Restaurant> restaurantList) {
        return restaurantList != null
                ? restaurantList
                .stream()
                .map(this::mapRestaurantToRestaurantDTO)
                .collect(Collectors.toList())
                : null;
    }

    private RestaurantDTO mapRestaurantToRestaurantDTO(final Restaurant restaurant) {
        return restaurant != null ?
                new RestaurantDTO()
                        .id(restaurant.getId())
                        .name(restaurant.getName())
                        .location(restaurant.getLocation())
                        .capacity(restaurant.getCapacity())
                        .menu(mapMenuToMenuDTO(restaurant.getMenu()))
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

    private Schedule mapScheduleDTOToSchedule(final ScheduleDTO scheduleDTO) {
        return scheduleDTO != null ?
                new Schedule()
                        .id(scheduleDTO.getId())
                        .type(scheduleDTO.getType())
                        .employeeList(null)
                : null;

    }

    private List<Restaurant> mapRestaurantDTOListToRestaurantList(final List<RestaurantDTO> restaurantDTOList) {
        return restaurantDTOList != null
                ? restaurantDTOList
                .stream()
                .map(this::mapRestaurantDTOToRestaurant)
                .collect(Collectors.toList())
                : null;
    }

    private Restaurant mapRestaurantDTOToRestaurant(final RestaurantDTO restaurantDTO) {
        return restaurantDTO != null ?
                new Restaurant()
                        .id(restaurantDTO.getId())
                        .name(restaurantDTO.getName())
                        .location(restaurantDTO.getLocation())
                        .capacity(restaurantDTO.getCapacity())
                        .menu(mapMenuDTOToMenu(restaurantDTO.getMenu()))
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
