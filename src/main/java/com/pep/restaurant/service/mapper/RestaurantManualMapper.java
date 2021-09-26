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

import java.util.ArrayList;
import java.util.List;

@Component
public class RestaurantManualMapper {
    public List<RestaurantDTO> mapRestaurantListToRestaurantDTOList(final List<Restaurant> restaurantList) {
        if ( restaurantList == null ) {
            return null;
        }

        final List<RestaurantDTO> list = new ArrayList<RestaurantDTO>( restaurantList.size() );
        for ( Restaurant restaurant : restaurantList ) {
            list.add( mapRestaurantToRestaurantDTO( restaurant ) );
        }

        return list;
    }

    public RestaurantDTO mapRestaurantToRestaurantDTO(final Restaurant restaurant) {
        if ( restaurant == null ) {
            return null;
        }

        final RestaurantDTO restaurantDTO = new RestaurantDTO();

        restaurantDTO.setId( restaurant.getId() );
        restaurantDTO.setName( restaurant.getName() );
        restaurantDTO.setLocation( restaurant.getLocation() );
        restaurantDTO.setCapacity( restaurant.getCapacity() );
        restaurantDTO.setMenu( menuToMenuDTO( restaurant.getMenu() ) );
        restaurantDTO.setEmployeeList( employeeListToEmployeeDTOList( restaurant.getEmployeeList() ) );

        return restaurantDTO;
    }

    private MenuDTO menuToMenuDTO(final Menu menu) {
        if ( menu == null ) {
            return null;
        }

        final MenuDTO menuDTO = new MenuDTO();

        menuDTO.setId( menu.getId() );
        menuDTO.setLanguage( menu.getLanguage() );

        return menuDTO;
    }

    private List<EmployeeDTO> employeeListToEmployeeDTOList(final List<Employee> list) {
        if ( list == null ) {
            return null;
        }

        final List<EmployeeDTO> list1 = new ArrayList<EmployeeDTO>( list.size() );
        for ( Employee employee : list ) {
            list1.add( employeeToEmployeeDTO( employee ) );
        }

        return list1;
    }

    protected EmployeeDTO employeeToEmployeeDTO(final Employee employee) {
        if ( employee == null ) {
            return null;
        }

        final EmployeeDTO employeeDTO = new EmployeeDTO();

        employeeDTO.setId( employee.getId() );
        employeeDTO.role( employee.getRole() );
        employeeDTO.setRestaurantList( null );
        employeeDTO.schedule( scheduleToScheduleDTO( employee.getSchedule() ) );

        return employeeDTO;
    }

    protected ScheduleDTO scheduleToScheduleDTO(final Schedule schedule) {
        if ( schedule == null ) {
            return null;
        }

        final ScheduleDTO scheduleDTO = new ScheduleDTO();

        scheduleDTO.setId( schedule.getId() );
        scheduleDTO.type( schedule.getType() );
        scheduleDTO.setEmployeeList( null );

        return scheduleDTO;
    }

}
