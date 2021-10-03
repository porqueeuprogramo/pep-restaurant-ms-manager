package com.pep.restaurant.service.mapper;

import com.pep.restaurant.ApplicationDataProvider;
import com.pep.restaurant.domain.Schedule;
import com.pep.restaurant.service.model.ScheduleDTO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

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
        List<Schedule> scheduleGivenList = Collections.singletonList(applicationDataProvider.getScheduleWithEmployeeList());

        //When
        List<ScheduleDTO> scheduleDTOResultList = scheduleMapper.mapScheduleListToScheduleDTOList(scheduleGivenList);

        //Then
        Assert.assertEquals(scheduleGivenList.get(0).getId(), scheduleDTOResultList.get(0).getId());
        Assert.assertEquals(scheduleGivenList.get(0).getType(), scheduleDTOResultList.get(0).getType());
        Assert.assertEquals(scheduleGivenList.get(0).getEmployeeList().get(0).getId(), scheduleDTOResultList.get(0).getEmployeeList().get(0).getId());
        Assert.assertEquals(scheduleGivenList.get(0).getEmployeeList().get(0).getRole(), scheduleDTOResultList.get(0).getEmployeeList().get(0).getRole());
        Assert.assertEquals(scheduleGivenList.get(0).getEmployeeList().get(0).getRestaurantList().get(0).getId(), scheduleDTOResultList.get(0).getEmployeeList().get(0).getRestaurantList().get(0).getId());
        Assert.assertEquals(scheduleGivenList.get(0).getEmployeeList().get(0).getRestaurantList().get(0).getLocation(), scheduleDTOResultList.get(0).getEmployeeList().get(0).getRestaurantList().get(0).getLocation());
        Assert.assertEquals(scheduleGivenList.get(0).getEmployeeList().get(0).getRestaurantList().get(0).getCapacity(), scheduleDTOResultList.get(0).getEmployeeList().get(0).getRestaurantList().get(0).getCapacity());
        Assert.assertEquals(scheduleGivenList.get(0).getEmployeeList().get(0).getRestaurantList().get(0).getMenu().getLanguage(), scheduleDTOResultList.get(0).getEmployeeList().get(0).getRestaurantList().get(0).getMenu().getLanguage());
    }

    @Test
    public void passingAnScheduleListNull_checkThatScheduleDTOListIsNull() {
        Assert.assertNull(scheduleMapper.mapScheduleListToScheduleDTOList(null));
    }


    @Test
    public void passingASchedule_checkThatScheduleDTOIsEquals() {
        //Given
        Schedule scheduleGiven = applicationDataProvider.getScheduleWithEmployeeList();

        //When
        ScheduleDTO scheduleDTOResult = scheduleMapper.mapScheduleToScheduleDTO(scheduleGiven);

        //Then
        Assert.assertEquals(scheduleGiven.getId(), scheduleDTOResult.getId());
        Assert.assertEquals(scheduleGiven.getType(), scheduleDTOResult.getType());
        Assert.assertEquals(scheduleGiven.getEmployeeList().get(0).getId(), scheduleDTOResult.getEmployeeList().get(0).getId());
        Assert.assertEquals(scheduleGiven.getEmployeeList().get(0).getRole(), scheduleDTOResult.getEmployeeList().get(0).getRole());
        Assert.assertEquals(scheduleGiven.getEmployeeList().get(0).getRestaurantList().get(0).getId(), scheduleDTOResult.getEmployeeList().get(0).getRestaurantList().get(0).getId());
        Assert.assertEquals(scheduleGiven.getEmployeeList().get(0).getRestaurantList().get(0).getLocation(), scheduleDTOResult.getEmployeeList().get(0).getRestaurantList().get(0).getLocation());
        Assert.assertEquals(scheduleGiven.getEmployeeList().get(0).getRestaurantList().get(0).getCapacity(), scheduleDTOResult.getEmployeeList().get(0).getRestaurantList().get(0).getCapacity());
        Assert.assertEquals(scheduleGiven.getEmployeeList().get(0).getRestaurantList().get(0).getMenu().getLanguage(), scheduleDTOResult.getEmployeeList().get(0).getRestaurantList().get(0).getMenu().getLanguage());

    }

    @Test
    public void passingAScheduleNull_checkThatScheduleDTOIsNull() {
        Assert.assertNull(scheduleMapper.mapScheduleToScheduleDTO(null));
    }

    @Test
    public void passingAScheduleDTO_checkThatScheduleIsEquals() {
        //Given
        ScheduleDTO scheduleDTOGiven = applicationDataProvider.getScheduleDTOWithEmployeeDTOList();

        //When
        Schedule scheduleResult = scheduleMapper.mapScheduleDTOToSchedule(scheduleDTOGiven);

        //Then
        Assert.assertEquals(scheduleDTOGiven.getId(), scheduleResult.getId());
        Assert.assertEquals(scheduleDTOGiven.getType(), scheduleResult.getType());
        Assert.assertEquals(scheduleDTOGiven.getEmployeeList().get(0).getId(), scheduleResult.getEmployeeList().get(0).getId());
        Assert.assertEquals(scheduleDTOGiven.getEmployeeList().get(0).getRole(), scheduleResult.getEmployeeList().get(0).getRole());
        Assert.assertEquals(scheduleDTOGiven.getEmployeeList().get(0).getRestaurantList().get(0).getId(), scheduleResult.getEmployeeList().get(0).getRestaurantList().get(0).getId());
        Assert.assertEquals(scheduleDTOGiven.getEmployeeList().get(0).getRestaurantList().get(0).getLocation(), scheduleResult.getEmployeeList().get(0).getRestaurantList().get(0).getLocation());
        Assert.assertEquals(scheduleDTOGiven.getEmployeeList().get(0).getRestaurantList().get(0).getCapacity(), scheduleResult.getEmployeeList().get(0).getRestaurantList().get(0).getCapacity());
        Assert.assertEquals(scheduleDTOGiven.getEmployeeList().get(0).getRestaurantList().get(0).getMenu().getLanguage(), scheduleResult.getEmployeeList().get(0).getRestaurantList().get(0).getMenu().getLanguage());

    }

    @Test
    public void passingAScheduleDTONull_checkThatScheduleIsNull() {
        Assert.assertNull(scheduleMapper.mapScheduleDTOToSchedule(null));
    }
}