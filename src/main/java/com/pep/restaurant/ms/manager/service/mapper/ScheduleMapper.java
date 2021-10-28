package com.pep.restaurant.ms.manager.service.mapper;

import com.pep.restaurant.ms.manager.domain.Employee;
import com.pep.restaurant.ms.manager.domain.Menu;
import com.pep.restaurant.ms.manager.domain.Restaurant;
import com.pep.restaurant.ms.manager.domain.Schedule;
import com.pep.restaurant.ms.manager.service.model.MenuDTO;
import com.pep.restaurant.ms.manager.service.model.EmployeeDTO;
import com.pep.restaurant.ms.manager.service.model.RestaurantDTO;
import com.pep.restaurant.ms.manager.service.model.ScheduleDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ScheduleMapper {

    /**
     * Map Schedule List to Schedule List DTO.
     *
     * @param scheduleList schedule list to map.
     * @return Schedule DTO list.
     */
    public List<ScheduleDTO> mapScheduleListToScheduleDTOList(final List<Schedule> scheduleList) {
        return scheduleList != null
                ? scheduleList
                .stream()
                .map(this::mapScheduleToScheduleDTO)
                .collect(Collectors.toList())
                : null;
    }

    /**
     * Map Schedule to Schedule DTO.
     *
     * @param schedule schedule.
     * @return Schedule DTO.
     */
    public ScheduleDTO mapScheduleToScheduleDTO(final Schedule schedule) {
        return schedule != null ?
                new ScheduleDTO()
                        .id(schedule.getId())
                        .type(schedule.getType())
                        .employeeList(mapEmployeeListToEmployeeDTOList(schedule.getEmployeeList()))
                : null;

    }

    /**
     * Map Schedule DTO to Schedule.
     *
     * @param scheduleDTO Schedule DTO.
     * @return Schedule.
     */
    public Schedule mapScheduleDTOToSchedule(final ScheduleDTO scheduleDTO) {
        return scheduleDTO != null ?
                new Schedule()
                        .id(scheduleDTO.getId())
                        .type(scheduleDTO.getType())
                        .employeeList(mapEmployeeDTOListToEmployeeList(scheduleDTO.getEmployeeList()))
                : null;
    }

    private Set<RestaurantDTO> mapRestaurantListToRestaurantDTOList(final Set<Restaurant> restaurantList) {
        return restaurantList != null
                ? restaurantList
                .stream()
                .map(this::mapRestaurantToRestaurantDTO)
                .collect(Collectors.toSet())
                : null;
    }

    private List<EmployeeDTO> mapEmployeeListToEmployeeDTOList(final List<Employee> employeeList) {
        return employeeList != null
                ? employeeList
                .stream()
                .map(this::mapEmployeeToEmployeeDTO)
                .collect(Collectors.toList())
                : null;
    }

    private EmployeeDTO mapEmployeeToEmployeeDTO(final Employee employee) {
        return employee != null ?
                new EmployeeDTO()
                        .id(employee.getId())
                        .role(employee.getRole())
                        .restaurantList(mapRestaurantListToRestaurantDTOList(employee.getRestaurantList()))
                        .schedule(null)
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

    private List<Employee> mapEmployeeDTOListToEmployeeList(final List<EmployeeDTO> employeeDTOList) {
        return employeeDTOList != null
                ? employeeDTOList
                .stream()
                .map(this::mapEmployeeDTOToEmployee)
                .collect(Collectors.toList())
                : null;
    }

    private Employee mapEmployeeDTOToEmployee(final EmployeeDTO employeeDTO) {
        return employeeDTO != null ?
                new Employee()
                        .id(employeeDTO.getId())
                        .role(employeeDTO.getRole())
                        .restaurantList(mapRestaurantDTOListToRestaurantList(employeeDTO.getRestaurantList()))
                        .schedule(mapScheduleDTOToSchedule(employeeDTO.getSchedule()))
                : null;
    }

    private Set<Restaurant> mapRestaurantDTOListToRestaurantList(final Set<RestaurantDTO> restaurantDTOList) {
        return restaurantDTOList != null
                ? restaurantDTOList
                .stream()
                .map(this::mapRestaurantDTOToRestaurant)
                .collect(Collectors.toSet())
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
