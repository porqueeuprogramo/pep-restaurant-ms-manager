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
public class ScheduleManualMapper {

    public List<ScheduleDTO> mapScheduleListToScheduleDTOList(final List<Schedule> scheduleList) {
        if ( scheduleList == null ) {
            return null;
        }

        final List<ScheduleDTO> list = new ArrayList<ScheduleDTO>( scheduleList.size() );
        for ( Schedule schedule : scheduleList ) {
            list.add( mapScheduleToScheduleDTO( schedule ) );
        }

        return list;
    }

    public ScheduleDTO mapScheduleToScheduleDTO(final Schedule schedule) {
        if ( schedule == null ) {
            return null;
        }

        final ScheduleDTO scheduleDTO = new ScheduleDTO();

        scheduleDTO.setId( schedule.getId() );
        scheduleDTO.type( schedule.getType() );
        scheduleDTO.setEmployeeList( employeeListToEmployeeDTOList( schedule.getEmployeeList() ) );

        return scheduleDTO;
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

    private EmployeeDTO employeeToEmployeeDTO(final Employee employee) {
        if ( employee == null ) {
            return null;
        }

        final EmployeeDTO employeeDTO = new EmployeeDTO();

        employeeDTO.setId( employee.getId() );
        employeeDTO.role( employee.getRole() );
        employeeDTO.setRestaurantList( restaurantListToRestaurantDTOList( employee.getRestaurantList() ) );
        employeeDTO.schedule( null );

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

}
