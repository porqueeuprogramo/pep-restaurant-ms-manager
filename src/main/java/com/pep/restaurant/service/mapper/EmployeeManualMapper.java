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
public class EmployeeManualMapper {
    public List<EmployeeDTO> mapEmployeeListToEmployeeDTOList(final List<Employee> employeeList) {
        if ( employeeList == null ) {
            return null;
        }

        final List<EmployeeDTO> list = new ArrayList<EmployeeDTO>( employeeList.size() );
        for ( Employee employee : employeeList ) {
            list.add( mapEmployeeToEmployeeDTO( employee ) );
        }

        return list;
    }

    public EmployeeDTO mapEmployeeToEmployeeDTO(final Employee employee) {
        if ( employee == null ) {
            return null;
        }

        final EmployeeDTO employeeDTO = new EmployeeDTO();

        employeeDTO.setId( employee.getId() );
        employeeDTO.role( employee.getRole() );
        employeeDTO.setRestaurantList( restaurantListToRestaurantDTOList( employee.getRestaurantList() ) );
        employeeDTO.schedule( scheduleToScheduleDTO( employee.getSchedule() ) );

        return employeeDTO;
    }

    private List<RestaurantDTO> restaurantListToRestaurantDTOList(final List<Restaurant> list) {
        if ( list == null ) {
            return null;
        }

        final List<RestaurantDTO> list1 = new ArrayList<RestaurantDTO>( list.size() );
        for ( Restaurant restaurant : list ) {
            list1.add( restaurantToRestaurantDTO( restaurant ) );
        }

        return list1;
    }

    private RestaurantDTO restaurantToRestaurantDTO(final Restaurant restaurant) {
        if ( restaurant == null ) {
            return null;
        }

        final RestaurantDTO restaurantDTO = new RestaurantDTO();

        restaurantDTO.setId( restaurant.getId() );
        restaurantDTO.setName( restaurant.getName() );
        restaurantDTO.setLocation( restaurant.getLocation() );
        restaurantDTO.setCapacity( restaurant.getCapacity() );
        restaurantDTO.setMenu( menuToMenuDTO( restaurant.getMenu() ) );
        restaurantDTO.setEmployeeList( null );

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

    private ScheduleDTO scheduleToScheduleDTO(final Schedule schedule) {
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
