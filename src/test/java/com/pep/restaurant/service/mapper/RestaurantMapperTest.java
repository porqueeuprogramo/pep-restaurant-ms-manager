package com.pep.restaurant.service.mapper;

import com.pep.restaurant.ApplicationDataProvider;
import com.pep.restaurant.domain.Employee;
import com.pep.restaurant.domain.Restaurant;
import com.pep.restaurant.service.model.EmployeeDTO;
import com.pep.restaurant.service.model.RestaurantDTO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class RestaurantMapperTest {

    @InjectMocks
    RestaurantMapper restaurantMapper;

    ApplicationDataProvider applicationDataProvider = new ApplicationDataProvider();


    @Test
    public void passingARestaurantList_checkThatRestaurantDTOListIsEquals() {
        //Given
        List<Restaurant> restaurantGivenList = Collections.singletonList(applicationDataProvider
                .getRestaurantWithEmployeeList());
        List<Employee> restaurantGivenEmployeeList =
                new ArrayList<>(restaurantGivenList.get(0).getEmployeeList());

        //When
        List<RestaurantDTO> restaurantDTOResultList = restaurantMapper
                .mapRestaurantListToRestaurantDTOList(restaurantGivenList);
        List<EmployeeDTO> restaurantResultEmployeeList =
                new ArrayList<>(restaurantDTOResultList.get(0).getEmployeeList());

        //Then
        Assert.assertEquals(restaurantGivenList.get(0).getId(),
                restaurantDTOResultList.get(0).getId());
        Assert.assertEquals(restaurantGivenList.get(0).getMenu().getId(),
                restaurantDTOResultList.get(0).getMenu().getId());
        Assert.assertEquals(restaurantGivenList.get(0).getMenu().getLanguage(),
                restaurantDTOResultList.get(0).getMenu().getLanguage());
        Assert.assertEquals(restaurantGivenList.get(0).getLocation(), restaurantDTOResultList.get(0).getLocation());
        Assert.assertEquals(restaurantGivenList.get(0).getCapacity(), restaurantDTOResultList.get(0).getCapacity());
        Assert.assertEquals(restaurantGivenEmployeeList.get(0).getId(), restaurantResultEmployeeList.get(0).getId());
        Assert.assertEquals(restaurantGivenEmployeeList.get(0).getRole(),
                restaurantResultEmployeeList.get(0).getRole());
        Assert.assertEquals(restaurantGivenEmployeeList.get(0).getSchedule().getId(),
                restaurantResultEmployeeList.get(0).getSchedule().getId());
        Assert.assertEquals(restaurantGivenEmployeeList.get(0).getSchedule().getType(),
                restaurantResultEmployeeList.get(0).getSchedule().getType());
    }

    @Test
    public void passingAnRestaurantListNull_checkThatRestaurantDTOListIsNull() {
        Assert.assertNull(restaurantMapper.mapRestaurantListToRestaurantDTOList(null));
    }

    @Test
    public void passingARestaurant_checkThatRestaurantDTOIsEquals() {
        //Given
        Restaurant restaurantGiven = applicationDataProvider.getRestaurantWithEmployeeList();
        List<Employee> restaurantGivenEmployee = new ArrayList<>(restaurantGiven.getEmployeeList());

        //When
        RestaurantDTO restaurantDTOResult = restaurantMapper.mapRestaurantToRestaurantDTO(restaurantGiven);
        List<EmployeeDTO> restaurantResultEmployee = new ArrayList<>(restaurantDTOResult.getEmployeeList());

        //Then
        Assert.assertEquals(restaurantGiven.getId(), restaurantDTOResult.getId());
        Assert.assertEquals(restaurantGiven.getMenu().getId(), restaurantDTOResult.getMenu().getId());
        Assert.assertEquals(restaurantGiven.getMenu().getLanguage(), restaurantDTOResult.getMenu().getLanguage());
        Assert.assertEquals(restaurantGiven.getLocation(), restaurantDTOResult.getLocation());
        Assert.assertEquals(restaurantGiven.getCapacity(), restaurantDTOResult.getCapacity());
        Assert.assertEquals(restaurantGivenEmployee.get(0).getId(), restaurantResultEmployee.get(0).getId());
        Assert.assertEquals(restaurantGivenEmployee.get(0).getRole(), restaurantResultEmployee.get(0).getRole());
        Assert.assertEquals(restaurantGivenEmployee.get(0).getSchedule().getId(),
                restaurantResultEmployee.get(0).getSchedule().getId());
        Assert.assertEquals(restaurantGivenEmployee.get(0).getSchedule().getType(),
                restaurantResultEmployee.get(0).getSchedule().getType());
    }

    @Test
    public void passingAnRestaurantNull_checkThatRestaurantDTOIsNull() {
        Assert.assertNull(restaurantMapper.mapRestaurantToRestaurantDTO(null));
    }

    @Test
    public void mapRestaurantDTOToRestaurant() {
        //Given
        RestaurantDTO restaurantGiven = applicationDataProvider.getRestaurantDTOWithEmployeeListDTO();
        List<EmployeeDTO> restaurantGivenEmployee = new ArrayList<>(restaurantGiven.getEmployeeList());

        //When
        Restaurant restaurantDTOResult = restaurantMapper.mapRestaurantDTOToRestaurant(restaurantGiven);
        List<Employee> restaurantResultEmployee = new ArrayList<>(restaurantDTOResult.getEmployeeList());

        //Then
        Assert.assertEquals(restaurantGiven.getId(), restaurantDTOResult.getId());
        Assert.assertEquals(restaurantGiven.getMenu().getId(), restaurantDTOResult.getMenu().getId());
        Assert.assertEquals(restaurantGiven.getMenu().getLanguage(), restaurantDTOResult.getMenu().getLanguage());
        Assert.assertEquals(restaurantGiven.getLocation(), restaurantDTOResult.getLocation());
        Assert.assertEquals(restaurantGiven.getCapacity(), restaurantDTOResult.getCapacity());
        Assert.assertEquals(restaurantGivenEmployee.get(0).getId(), restaurantResultEmployee.get(0).getId());
        Assert.assertEquals(restaurantGivenEmployee.get(0).getRole(), restaurantResultEmployee.get(0).getRole());
        Assert.assertEquals(restaurantGivenEmployee.get(0).getSchedule().getId(),
                restaurantResultEmployee.get(0).getSchedule().getId());
        Assert.assertEquals(restaurantGivenEmployee.get(0).getSchedule().getType(),
                restaurantResultEmployee.get(0).getSchedule().getType());
    }

    @Test
    public void passingAnRestaurantDTONull_checkThatRestaurantIsNull() {
        Assert.assertNull(restaurantMapper.mapRestaurantDTOToRestaurant(null));
    }
}