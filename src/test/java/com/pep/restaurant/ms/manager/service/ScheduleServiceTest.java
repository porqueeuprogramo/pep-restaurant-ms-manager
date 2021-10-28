package com.pep.restaurant.ms.manager.service;

import com.pep.restaurant.ms.manager.ApplicationDataProvider;
import com.pep.restaurant.ms.manager.domain.Employee;
import com.pep.restaurant.ms.manager.domain.Schedule;
import com.pep.restaurant.ms.manager.domain.enumeration.ScheduleType;
import com.pep.restaurant.ms.manager.repository.EmployeeRepository;
import com.pep.restaurant.ms.manager.repository.ScheduleRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class ScheduleServiceTest {

    @InjectMocks
    ScheduleService scheduleService;

    @Mock
    ScheduleRepository scheduleRepository;

    @Mock
    EmployeeRepository employeeRepository;

    ApplicationDataProvider applicationDataProvider = new ApplicationDataProvider();

    @Test
    public void passingAScheduleThatAlreadyExists_throwAnException() {
        //Given
        Schedule scheduleGiven = applicationDataProvider.getSchedule();

        //When
        Mockito.when(scheduleRepository.findById(scheduleGiven.getId())).thenReturn(Optional.of(scheduleGiven));
        Assert.assertThrows(NullPointerException.class, () -> scheduleService.createSchedule(scheduleGiven));

    }

    @Test
    public void passingAScheduleThatNotExists_ReturnScheduleSaved() {
        //Given
        Schedule scheduleGiven = applicationDataProvider.getSchedule();

        //When
        Mockito.when(scheduleRepository.findById(scheduleGiven.getId())).thenReturn(Optional.empty());
        Mockito.when(scheduleRepository.save(scheduleGiven)).thenReturn(scheduleGiven);
        Schedule scheduleResult = scheduleService.createSchedule(scheduleGiven);

        //Then
        Assert.assertEquals(scheduleGiven, scheduleResult);

    }

    @Test
    public void receivingAScheduleAndAnId_returnEditedSchedule() {
        //Given
        Schedule scheduleToEdit = applicationDataProvider.getSchedule();

        Schedule scheduleEdited = applicationDataProvider.getSchedule()
                .type(ScheduleType.PARTTIME);

        //When
        Mockito.when(scheduleRepository.findById(Mockito.any())).thenReturn(Optional.of(scheduleToEdit));
        Mockito.when(scheduleRepository.save(Mockito.any())).thenReturn(scheduleEdited);
        Schedule scheduleResult = scheduleService.editSchedule(1L,scheduleToEdit);

        //Then
        Assert.assertEquals(scheduleEdited,scheduleResult);

    }

    @Test
    public void receivingAScheduleAndAnId_thrownAnException() {
        //Given
        Schedule scheduleToEdit = applicationDataProvider.getSchedule();

        //When
        Mockito.when(scheduleRepository.findById(Mockito.any())).thenReturn(Optional.empty());
        Assert.assertThrows(NullPointerException.class, () -> scheduleService.editSchedule(1L,scheduleToEdit));

    }

    @Test
    public void receivingAScheduleAndAnId_deleteSchedule() {
        //Given
        Schedule scheduleToDelete = applicationDataProvider.getSchedule();

        //When
        Mockito.when(scheduleRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(scheduleToDelete));
        Mockito.doNothing().when(scheduleRepository).deleteById(Mockito.anyLong());
        scheduleService.deleteSchedule(1L);

        //Then
        Mockito.verify(scheduleRepository, Mockito.times(1)).findById(Mockito.anyLong());
        Mockito.verify(scheduleRepository,  Mockito.times(1)).deleteById(Mockito.anyLong());

    }

    @Test
    public void deleteScheduleById_thrownAnException() {
        //Given
        //When
        Mockito.when(scheduleRepository.findById(Mockito.any())).thenReturn(Optional.empty());
        Assert.assertThrows(NullPointerException.class, () -> scheduleService.deleteSchedule(1L));
    }


    @Test
    public void passingAScheduleId_thrownAnException() {
        //Given
        //When
        Mockito.when(scheduleRepository.findById(Mockito.any())).thenReturn(Optional.empty());
        Assert.assertThrows(NullPointerException.class, () -> scheduleService.getSchedule(1L));
    }

    @Test
    public void passingAScheduleId_getSchedule() {
        //Given
        Schedule scheduleToGet = applicationDataProvider.getSchedule();

        //When
        Mockito.when(scheduleRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(scheduleToGet));
        Schedule scheduleResult = scheduleService.getSchedule(1L);

        //Then
        Assert.assertEquals(scheduleToGet,scheduleResult);
    }

    @Test
    public void getAllSchedules_CheckIfScheduleListHasTheResultExpected() {
        //Given
        Schedule scheduleToGet = applicationDataProvider.getSchedule();

        //When
        Mockito.when(scheduleRepository.findAll()).thenReturn(Collections.singletonList(scheduleToGet));
        List<Schedule> scheduleListResult = scheduleService.getAllSchedules();

        //Then
        Assert.assertEquals(scheduleToGet.getId(),scheduleListResult.get(0).getId());
        Assert.assertEquals(scheduleToGet.getType(),scheduleListResult.get(0).getType());

    }

    @Test
    public void getAllSchedules_throwAnException() {

        //When
        Mockito.when(scheduleRepository.findAll()).thenReturn(new ArrayList<>());

        //Then
        Assert.assertThrows(NullPointerException.class, () -> scheduleService.getAllSchedules());

    }

    @Test
    public void requestingEmployeeIdAndRestaurantId_checkScheduleWithEmployeePersisted() {

        //Given
        Schedule scheduleGiven = applicationDataProvider.getSchedule();
        scheduleGiven.setId(1L);
        Employee employeeGiven = applicationDataProvider.getEmployeeWithoutRestaurantListAndWithoutSchedule();
        employeeGiven.id(1L);

        //When
        Mockito.when(scheduleRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(scheduleGiven));
        Mockito.when(employeeRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(employeeGiven));
        Mockito.when(scheduleRepository.save(Mockito.any())).thenReturn(scheduleGiven);
        Schedule scheduleResult = scheduleService.addEmployee(1L,1L);

        //Then
        Assert.assertEquals(scheduleGiven.getId(), scheduleResult.getId());
        Assert.assertEquals(scheduleGiven.getType(), scheduleGiven.getType());
        Assert.assertEquals(1, scheduleResult.getEmployeeList().size());

    }

    @Test
    public void requestingEmployeeIdAndRestaurantId_checkScheduleWithEmployeeRemoved() {

        //Given
        Schedule scheduleGiven = applicationDataProvider.getSchedule();
        scheduleGiven.setId(1L);
        Employee employeeGiven = applicationDataProvider.getEmployeeWithoutRestaurantListAndWithoutSchedule();
        employeeGiven.id(1L);
        scheduleGiven.addEmployee(employeeGiven);

        //When
        Mockito.when(scheduleRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(scheduleGiven));
        Mockito.when(employeeRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(employeeGiven));
        Mockito.when(scheduleRepository.save(Mockito.any())).thenReturn(scheduleGiven);
        Schedule scheduleResult = scheduleService.removeEmployee(1L,1L);

        //Then
        Assert.assertEquals(scheduleGiven.getId(), scheduleResult.getId());
        Assert.assertEquals(scheduleGiven.getType(), scheduleGiven.getType());
        Assert.assertEquals(0, scheduleResult.getEmployeeList().size());

    }


}
