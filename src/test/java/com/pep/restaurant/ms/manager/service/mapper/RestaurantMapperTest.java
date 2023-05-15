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
        Assert.assertEquals(restaurantGivenList.get(0).getUid(),
                restaurantDTOResultList.get(0).getUid());
        Assert.assertEquals(restaurantGivenList.get(0).getMenu().getLanguage(),
                restaurantDTOResultList.get(0).getMenu().getLanguage());
        Assert.assertEquals(restaurantGivenList.get(0).getLocation().getAddress().getName()
                , restaurantDTOResultList.get(0).getLocation().getAddress().getName());
        Assert.assertEquals(restaurantGivenList.get(0).getLocation().getAddress().getCountry()
                , restaurantDTOResultList.get(0).getLocation().getAddress().getCountry());
        Assert.assertEquals(restaurantGivenList.get(0).getLocation().getAddress().getCity()
                , restaurantDTOResultList.get(0).getLocation().getAddress().getCity());
        Assert.assertEquals(restaurantGivenList.get(0).getLocation().getAddress().getPostalCode()
                , restaurantDTOResultList.get(0).getLocation().getAddress().getPostalCode());
        Assert.assertEquals(restaurantGivenList.get(0).getLocation().getCoordinate().getLatitude()
                , restaurantDTOResultList.get(0).getLocation().getLocationCoordinate().getLatitude(), 0);
        Assert.assertEquals(restaurantGivenList.get(0).getLocation().getCoordinate().getLongitude()
                , restaurantDTOResultList.get(0).getLocation().getLocationCoordinate().getLongitude(),0);
        Assert.assertEquals(restaurantGivenList.get(0).getName(), restaurantDTOResultList.get(0).getName());
        Assert.assertEquals(restaurantGivenList.get(0).getHereId(), restaurantDTOResultList.get(0).getHereId());
        Assert.assertEquals(restaurantGivenList.get(0).getScheduleRoutine().getScheduleRoutine().size(),
                restaurantDTOResultList.get(0).getSchedule().getScheduleRoutine().size());
        Assert.assertEquals(restaurantGivenList.get(0).getCapacity(), restaurantDTOResultList.get(0).getCapacity());
        Assert.assertEquals(restaurantGivenEmployeeList.get(0).getUid(), restaurantResultEmployeeList.get(0).getUid());
        Assert.assertEquals(restaurantGivenEmployeeList.get(0).getRole(),
                restaurantResultEmployeeList.get(0).getRole());
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
        Assert.assertEquals(restaurantGiven.getUid(), restaurantDTOResult.getUid());
        Assert.assertEquals(restaurantGiven.getMenu().getLanguage(), restaurantDTOResult.getMenu().getLanguage());
        Assert.assertEquals(restaurantGiven.getLocation().getAddress().getName()
                , restaurantDTOResult.getLocation().getAddress().getName());
        Assert.assertEquals(restaurantGiven.getLocation().getAddress().getCountry()
                , restaurantDTOResult.getLocation().getAddress().getCountry());
        Assert.assertEquals(restaurantGiven.getLocation().getAddress().getCity()
                , restaurantDTOResult.getLocation().getAddress().getCity());
        Assert.assertEquals(restaurantGiven.getLocation().getAddress().getPostalCode()
                , restaurantDTOResult.getLocation().getAddress().getPostalCode());
        Assert.assertEquals(restaurantGiven.getLocation().getCoordinate().getLatitude()
                , restaurantDTOResult.getLocation().getLocationCoordinate().getLatitude(), 0);
        Assert.assertEquals(restaurantGiven.getLocation().getCoordinate().getLongitude()
                , restaurantDTOResult.getLocation().getLocationCoordinate().getLongitude(),0);
        Assert.assertEquals(restaurantGiven.getCapacity(), restaurantDTOResult.getCapacity());
        Assert.assertEquals(restaurantGiven.getName(), restaurantDTOResult.getName());
        Assert.assertEquals(restaurantGiven.getHereId(), restaurantDTOResult.getHereId());
        Assert.assertEquals(restaurantGiven.getScheduleRoutine().getScheduleRoutine().size(),
                restaurantDTOResult.getSchedule().getScheduleRoutine().size());
        Assert.assertEquals(restaurantGivenEmployee.get(0).getUid(), restaurantResultEmployee.get(0).getUid());
        Assert.assertEquals(restaurantGivenEmployee.get(0).getRole(), restaurantResultEmployee.get(0).getRole());
    }

    @Test
    public void passingAnRestaurantNull_checkThatRestaurantDTOIsNull() {
        Assert.assertNull(restaurantMapper.mapRestaurantToRestaurantDTO(null));
    }

    @Test
    public void passingARestaurantDTO_checkThatRestaurantIsEquals() {
        //Given
        RestaurantDTO restaurantDTOGiven = applicationDataProvider.getRestaurantDTOWithEmployeeListDTO();
        List<EmployeeDTO> restaurantDTOGivenEmployee = new ArrayList<>(restaurantDTOGiven.getEmployeeList());

        //When
        Restaurant restaurantResult = restaurantMapper.mapRestaurantDTOToRestaurant(restaurantDTOGiven);
        List<Employee> restaurantResultEmployee = new ArrayList<>(restaurantResult.getEmployeeList());

        //Then
        Assert.assertEquals(restaurantDTOGiven.getUid(), restaurantResult.getUid());
        Assert.assertEquals(restaurantDTOGiven.getMenu().getLanguage(), restaurantResult.getMenu().getLanguage());
        Assert.assertEquals(restaurantDTOGiven.getLocation().getAddress().getName()
                , restaurantResult.getLocation().getAddress().getName());
        Assert.assertEquals(restaurantDTOGiven.getLocation().getAddress().getCountry()
                , restaurantResult.getLocation().getAddress().getCountry());
        Assert.assertEquals(restaurantDTOGiven.getLocation().getAddress().getCity()
                , restaurantResult.getLocation().getAddress().getCity());
        Assert.assertEquals(restaurantDTOGiven.getLocation().getAddress().getPostalCode()
                , restaurantResult.getLocation().getAddress().getPostalCode());
        Assert.assertEquals(restaurantDTOGiven.getLocation().getLocationCoordinate().getLatitude()
                , restaurantResult.getLocation().getCoordinate().getLatitude(), 0);
        Assert.assertEquals(restaurantDTOGiven.getLocation().getLocationCoordinate().getLongitude()
                , restaurantResult.getLocation().getCoordinate().getLongitude(),0);
        Assert.assertEquals(restaurantDTOGiven.getCapacity(), restaurantResult.getCapacity());
        Assert.assertEquals(restaurantDTOGiven.getName(), restaurantResult.getName());
        Assert.assertEquals(restaurantDTOGiven.getHereId(), restaurantResult.getHereId());
        Assert.assertEquals(restaurantDTOGiven.getSchedule().getScheduleRoutine().size(),
                restaurantResult.getScheduleRoutine().getScheduleRoutine().size());
        Assert.assertEquals(restaurantDTOGivenEmployee.get(0).getUid(), restaurantResultEmployee.get(0).getUid());
        Assert.assertEquals(restaurantDTOGivenEmployee.get(0).getRole(), restaurantResultEmployee.get(0).getRole());
    }

    @Test
    public void passingAnRestaurantDTONull_checkThatRestaurantIsNull() {
        Assert.assertNull(restaurantMapper.mapRestaurantDTOToRestaurant(null));
    }
}
