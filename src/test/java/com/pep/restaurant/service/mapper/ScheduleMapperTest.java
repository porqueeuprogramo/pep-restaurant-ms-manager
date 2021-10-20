package com.pep.restaurant.service.mapper;

import com.pep.restaurant.ApplicationDataProvider;
import com.pep.restaurant.domain.Employee;
import com.pep.restaurant.domain.Restaurant;
import com.pep.restaurant.domain.Schedule;
import com.pep.restaurant.service.model.EmployeeDTO;
import com.pep.restaurant.service.model.RestaurantDTO;
import com.pep.restaurant.service.model.ScheduleDTO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class ScheduleMapperTest {

    @InjectMocks
    ScheduleMapper scheduleMapper;

    ApplicationDataProvider applicationDataProvider = new ApplicationDataProvider();

    @Test
    public void passingAScheduleList_checkThatScheduleDTOListIsEquals() {
        //Given
        List<Schedule> scheduleGivenList = Collections.singletonList(applicationDataProvider
                .getScheduleWithEmployeeList());
        List<Restaurant> scheduleGivenEmployeeRestaurantList = new ArrayList<>(scheduleGivenList.get(0)
                .getEmployeeList().get(0).getRestaurantList());

        //When
        List<ScheduleDTO> scheduleDTOResultList = scheduleMapper
                .mapScheduleListToScheduleDTOList(scheduleGivenList);
        List<RestaurantDTO> scheduleResultEmployeeRestaurantList = new ArrayList<>(scheduleDTOResultList
                .get(0).getEmployeeList().get(0).getRestaurantList());

        //Then
        Assert.assertEquals(scheduleGivenList.get(0).getId(), scheduleDTOResultList.get(0).getId());
        Assert.assertEquals(scheduleGivenList.get(0).getType(), scheduleDTOResultList.get(0).getType());
        Assert.assertEquals(scheduleGivenList.get(0).getEmployeeList().get(0).getId(),
                scheduleDTOResultList.get(0).getEmployeeList().get(0).getId());
        Assert.assertEquals(scheduleGivenList.get(0).getEmployeeList().get(0).getRole(),
                scheduleDTOResultList.get(0).getEmployeeList().get(0).getRole());
        Assert.assertEquals(scheduleGivenEmployeeRestaurantList.get(0).getId(),
                scheduleResultEmployeeRestaurantList.get(0).getId());
        Assert.assertEquals(scheduleGivenEmployeeRestaurantList.get(0).getLocation(),
                scheduleResultEmployeeRestaurantList.get(0).getLocation());
        Assert.assertEquals(scheduleGivenEmployeeRestaurantList.get(0).getCapacity(),
                scheduleResultEmployeeRestaurantList.get(0).getCapacity());
        Assert.assertEquals(scheduleGivenEmployeeRestaurantList.get(0).getMenu().getLanguage(),
                scheduleResultEmployeeRestaurantList.get(0).getMenu().getLanguage());
    }

    @Test
    public void passingAnScheduleListNull_checkThatScheduleDTOListIsNull() {
        Assert.assertNull(scheduleMapper.mapScheduleListToScheduleDTOList(null));
    }


    @Test
    public void passingASchedule_checkThatScheduleDTOIsEquals() {
        //Given
        Schedule scheduleGiven = applicationDataProvider.getScheduleWithEmployeeList();
        List<Restaurant> scheduleGivenEmployeeRestaurant =
                new ArrayList<>(scheduleGiven.getEmployeeList().get(0).getRestaurantList());

        //When
        ScheduleDTO scheduleDTOResult = scheduleMapper.mapScheduleToScheduleDTO(scheduleGiven);
        List<RestaurantDTO> scheduleResultEmployeeRestaurant =
                new ArrayList<>(scheduleDTOResult.getEmployeeList().get(0).getRestaurantList());

        //Then
        Assert.assertEquals(scheduleGiven.getId(), scheduleDTOResult.getId());
        Assert.assertEquals(scheduleGiven.getType(), scheduleDTOResult.getType());
        Assert.assertEquals(scheduleGiven.getEmployeeList().get(0).getId(),
                scheduleDTOResult.getEmployeeList().get(0).getId());
        Assert.assertEquals(scheduleGiven.getEmployeeList().get(0).getRole(),
                scheduleDTOResult.getEmployeeList().get(0).getRole());
        Assert.assertEquals(scheduleGivenEmployeeRestaurant.get(0).getId(),
                scheduleResultEmployeeRestaurant.get(0).getId());
        Assert.assertEquals(scheduleGivenEmployeeRestaurant.get(0).getLocation(),
                scheduleResultEmployeeRestaurant.get(0).getLocation());
        Assert.assertEquals(scheduleGivenEmployeeRestaurant.get(0).getCapacity(),
                scheduleResultEmployeeRestaurant.get(0).getCapacity());
        Assert.assertEquals(scheduleGivenEmployeeRestaurant.get(0).getMenu().getLanguage(),
                scheduleResultEmployeeRestaurant.get(0).getMenu().getLanguage());

    }

    @Test
    public void passingAScheduleNull_checkThatScheduleDTOIsNull() {
        Assert.assertNull(scheduleMapper.mapScheduleToScheduleDTO(null));
    }

    @Test
    public void passingAScheduleDTO_checkThatScheduleIsEquals() {
        //Given
        ScheduleDTO scheduleDTOGiven = applicationDataProvider.getScheduleDTOWithEmployeeDTOList();
        List<RestaurantDTO> scheduleGivenEmployeeRestaurant =
                new ArrayList<>(scheduleDTOGiven.getEmployeeList().get(0).getRestaurantList());

        //When
        Schedule scheduleResult = scheduleMapper.mapScheduleDTOToSchedule(scheduleDTOGiven);
        List<Restaurant> scheduleResultEmployeeRestaurant =
                new ArrayList<>(scheduleResult.getEmployeeList().get(0).getRestaurantList());

        //Then
        Assert.assertEquals(scheduleDTOGiven.getId(), scheduleResult.getId());
        Assert.assertEquals(scheduleDTOGiven.getType(), scheduleResult.getType());
        Assert.assertEquals(scheduleDTOGiven.getEmployeeList().get(0).getId(),
                scheduleResult.getEmployeeList().get(0).getId());
        Assert.assertEquals(scheduleDTOGiven.getEmployeeList().get(0).getRole(),
                scheduleResult.getEmployeeList().get(0).getRole());
        Assert.assertEquals(scheduleGivenEmployeeRestaurant.get(0).getId(),
                scheduleResultEmployeeRestaurant.get(0).getId());
        Assert.assertEquals(scheduleGivenEmployeeRestaurant.get(0).getLocation(),
                scheduleResultEmployeeRestaurant.get(0).getLocation());
        Assert.assertEquals(scheduleGivenEmployeeRestaurant.get(0).getCapacity(),
                scheduleResultEmployeeRestaurant.get(0).getCapacity());
        Assert.assertEquals(scheduleGivenEmployeeRestaurant.get(0).getMenu().getLanguage(),
                scheduleResultEmployeeRestaurant.get(0).getMenu().getLanguage());

    }

    @Test
    public void passingAScheduleDTONull_checkThatScheduleIsNull() {
        Assert.assertNull(scheduleMapper.mapScheduleDTOToSchedule(null));
    }
}