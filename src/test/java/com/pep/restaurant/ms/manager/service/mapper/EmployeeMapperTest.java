package com.pep.restaurant.ms.manager.service.mapper;

import com.pep.restaurant.ms.manager.ApplicationDataProvider;
import com.pep.restaurant.ms.manager.domain.Employee;
import com.pep.restaurant.ms.manager.domain.Restaurant;
import com.pep.restaurant.ms.manager.service.model.EmployeeDTO;
import com.pep.restaurant.ms.manager.service.model.RestaurantDTO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeMapperTest {

    @InjectMocks
    EmployeeMapper employeeMapper;

    ApplicationDataProvider applicationDataProvider = new ApplicationDataProvider();

    @Test
    public void passingAnEmployeeList_checkThatEmployeeDTOListIsEquals() {
        //Given
        List<Employee> employeeGivenList = Collections.singletonList(applicationDataProvider.getEmployee());
        List<Restaurant> employeeGivenRestaurantList = new ArrayList<>(employeeGivenList.get(0).getRestaurantList());

        //When
        List<EmployeeDTO> employeeDTOResultList= employeeMapper
                .mapEmployeeListToEmployeeDTOList(employeeGivenList);
        List<RestaurantDTO> employeeResultRestaurantList =
                new ArrayList<>(employeeDTOResultList.get(0).getRestaurantList());

        //Then
        Assert.assertEquals(employeeGivenList.get(0).getId(), employeeDTOResultList.get(0).getId());
        Assert.assertEquals(employeeGivenList.get(0).getRole(), employeeDTOResultList.get(0).getRole());
        Assert.assertEquals(employeeGivenRestaurantList.get(0).getId(), employeeResultRestaurantList.get(0).getId());
        Assert.assertEquals(employeeGivenRestaurantList.get(0).getLocation().getAddress().getName()
                , employeeResultRestaurantList.get(0).getLocation().getAddress().getName());
        Assert.assertEquals(employeeGivenRestaurantList.get(0).getLocation().getAddress().getCountry()
                , employeeResultRestaurantList.get(0).getLocation().getAddress().getCountry());
        Assert.assertEquals(employeeGivenRestaurantList.get(0).getLocation().getAddress().getCity()
                , employeeResultRestaurantList.get(0).getLocation().getAddress().getCity());
        Assert.assertEquals(employeeGivenRestaurantList.get(0).getLocation().getAddress().getPostalCode()
                , employeeResultRestaurantList.get(0).getLocation().getAddress().getPostalCode());
        Assert.assertEquals(employeeGivenRestaurantList.get(0).getLocation().getCoordinate().getLatitude()
                , employeeResultRestaurantList.get(0).getLocation().getLocationCoordinate().getLatitude(), 0);
        Assert.assertEquals(employeeGivenRestaurantList.get(0).getLocation().getCoordinate().getLongitude()
                , employeeResultRestaurantList.get(0).getLocation().getLocationCoordinate().getLongitude(),0);
        Assert.assertEquals(employeeGivenRestaurantList.get(0).getCapacity(),
                employeeResultRestaurantList.get(0).getCapacity());
        Assert.assertEquals(employeeGivenRestaurantList.get(0).getName(), employeeResultRestaurantList.get(0).getName());
        Assert.assertEquals(employeeGivenRestaurantList.get(0).getHereId(), employeeResultRestaurantList.get(0).getHereId());
        Assert.assertEquals(employeeGivenRestaurantList.get(0).getScheduleRoutine().getScheduleRoutine().size(),
                employeeResultRestaurantList.get(0).getSchedule().getScheduleRoutine().size());
        Assert.assertEquals(employeeGivenRestaurantList.get(0).getMenu().getLanguage(),
                employeeResultRestaurantList.get(0).getMenu().getLanguage());

    }

    @Test
    public void passingAnEmployeeListNull_checkThatEmployeeDTOListIsNull() {
        Assert.assertNull(employeeMapper.mapEmployeeListToEmployeeDTOList(null));
    }

    @Test
    public void passingAnEmployee_checkThatEmployeeDTOIsEquals() {
        //Given
        Employee employeeGiven = applicationDataProvider.getEmployee();
        List<Restaurant> employeeGivenRestaurantList = new ArrayList<>(employeeGiven.getRestaurantList());

        //When
        EmployeeDTO employeeDTOResult = employeeMapper.mapEmployeeToEmployeeDTO(employeeGiven);
        List<RestaurantDTO> employeeResultRestaurant = new ArrayList<>(employeeDTOResult.getRestaurantList());

        //Then
        Assert.assertEquals(employeeGiven.getId(), employeeDTOResult.getId());
        Assert.assertEquals(employeeGiven.getRole(), employeeDTOResult.getRole());
        Assert.assertEquals(employeeGivenRestaurantList.get(0).getId(), employeeResultRestaurant.get(0).getId());
        Assert.assertEquals(employeeGivenRestaurantList.get(0).getLocation().getAddress().getName()
                , employeeResultRestaurant.get(0).getLocation().getAddress().getName());
        Assert.assertEquals(employeeGivenRestaurantList.get(0).getLocation().getAddress().getCountry()
                , employeeResultRestaurant.get(0).getLocation().getAddress().getCountry());
        Assert.assertEquals(employeeGivenRestaurantList.get(0).getLocation().getAddress().getCity()
                , employeeResultRestaurant.get(0).getLocation().getAddress().getCity());
        Assert.assertEquals(employeeGivenRestaurantList.get(0).getLocation().getAddress().getPostalCode()
                , employeeResultRestaurant.get(0).getLocation().getAddress().getPostalCode());
        Assert.assertEquals(employeeGivenRestaurantList.get(0).getLocation().getCoordinate().getLatitude()
                , employeeResultRestaurant.get(0).getLocation().getLocationCoordinate().getLatitude(), 0);
        Assert.assertEquals(employeeGivenRestaurantList.get(0).getLocation().getCoordinate().getLongitude()
                , employeeResultRestaurant.get(0).getLocation().getLocationCoordinate().getLongitude(), 0);
        Assert.assertEquals(employeeGivenRestaurantList.get(0).getCapacity(),
                employeeResultRestaurant.get(0).getCapacity());
        Assert.assertEquals(employeeGivenRestaurantList.get(0).getName(), employeeResultRestaurant.get(0).getName());
        Assert.assertEquals(employeeGivenRestaurantList.get(0).getHereId(), employeeResultRestaurant.get(0).getHereId());
        Assert.assertEquals(employeeGivenRestaurantList.get(0).getScheduleRoutine().getScheduleRoutine().size(),
                employeeResultRestaurant.get(0).getSchedule().getScheduleRoutine().size());
        Assert.assertEquals(employeeGivenRestaurantList.get(0).getMenu().getLanguage(),
                employeeResultRestaurant.get(0).getMenu().getLanguage());
    }

    @Test
    public void passingAnEmployeeNull_checkThatEmployeeDTOIsNull() {
        Assert.assertNull(employeeMapper.mapEmployeeToEmployeeDTO(null));
    }

    @Test
    public void passingAnEmployeeDTO_checkThatEmployeeIsEquals() {
        //Given
        EmployeeDTO employeeDTOGiven = applicationDataProvider.getEmployeeDTO();
        List<RestaurantDTO> employeeGivenRestaurantList = new ArrayList<>(employeeDTOGiven.getRestaurantList());

        //When
        Employee employeeResult = employeeMapper.mapEmployeeDTOToEmployee(employeeDTOGiven);
        List<Restaurant> employeeResultRestaurant = new ArrayList<>(employeeResult.getRestaurantList());

        //Then
        Assert.assertEquals(employeeDTOGiven.getId(), employeeResult.getId());
        Assert.assertEquals(employeeDTOGiven.getRole(), employeeResult.getRole());
        Assert.assertEquals(employeeGivenRestaurantList.get(0).getId(), employeeResultRestaurant.get(0).getId());
        Assert.assertEquals(employeeGivenRestaurantList.get(0).getLocation().getAddress().getName()
                , employeeResultRestaurant.get(0).getLocation().getAddress().getName());
        Assert.assertEquals(employeeGivenRestaurantList.get(0).getLocation().getAddress().getCountry()
                , employeeResultRestaurant.get(0).getLocation().getAddress().getCountry());
        Assert.assertEquals(employeeGivenRestaurantList.get(0).getLocation().getAddress().getCity()
                , employeeResultRestaurant.get(0).getLocation().getAddress().getCity());
        Assert.assertEquals(employeeGivenRestaurantList.get(0).getLocation().getAddress().getPostalCode()
                , employeeResultRestaurant.get(0).getLocation().getAddress().getPostalCode());
        Assert.assertEquals(employeeGivenRestaurantList.get(0).getLocation().getLocationCoordinate().getLatitude()
                , employeeResultRestaurant.get(0).getLocation().getCoordinate().getLatitude(), 0);
        Assert.assertEquals(employeeGivenRestaurantList.get(0).getLocation().getLocationCoordinate().getLongitude()
                , employeeResultRestaurant.get(0).getLocation().getCoordinate().getLongitude(), 0);
        Assert.assertEquals(employeeGivenRestaurantList.get(0).getCapacity(),
                employeeResultRestaurant.get(0).getCapacity());
        Assert.assertEquals(employeeGivenRestaurantList.get(0).getName(), employeeResultRestaurant.get(0).getName());
        Assert.assertEquals(employeeGivenRestaurantList.get(0).getHereId(), employeeResultRestaurant.get(0).getHereId());
        Assert.assertEquals(employeeGivenRestaurantList.get(0).getSchedule().getScheduleRoutine().size(),
                employeeResultRestaurant.get(0).getScheduleRoutine().getScheduleRoutine().size());
        Assert.assertEquals(employeeGivenRestaurantList.get(0).getMenu().getLanguage(),
                employeeResultRestaurant.get(0).getMenu().getLanguage());
    }

    @Test
    public void passingAnEmployeeDTONull_checkThatEmployeeIsNull() {
        Assert.assertNull(employeeMapper.mapEmployeeDTOToEmployee(null));
    }

}
