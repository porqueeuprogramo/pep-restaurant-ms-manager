package com.pep.restaurant.ms.manager.service;

import com.pep.restaurant.ms.manager.ApplicationDataProvider;
import com.pep.restaurant.ms.manager.domain.Employee;
import com.pep.restaurant.ms.manager.domain.Restaurant;
import com.pep.restaurant.ms.manager.repository.RestaurantRepository;
import com.pep.restaurant.ms.manager.repository.EmployeeRepository;
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
public class EmployeeServiceTest {

    @InjectMocks
    EmployeeService employeeService;

    @Mock
    EmployeeRepository employeeRepository;

    @Mock
    RestaurantRepository restaurantRepository;

    ApplicationDataProvider applicationDataProvider = new ApplicationDataProvider();


    @Test
    public void passingAEmployeeThatAlreadyExists_throwAnException() {
        //Given
        Employee employeeGiven = applicationDataProvider.getEmployee();

        //When
        Mockito.when(employeeRepository.findById(employeeGiven.getId())).thenReturn(Optional.of(employeeGiven));
        Assert.assertThrows(NullPointerException.class, () -> employeeService.createEmployee(employeeGiven));

    }

    @Test
    public void passingAEmployeeThatNotExists_ReturnEmployeeSaved() {
        //Given
        Employee employeeGiven = applicationDataProvider.getEmployee();

        //When
        Mockito.when(employeeRepository.findById(employeeGiven.getId())).thenReturn(Optional.empty());
        Mockito.when(employeeRepository.save(employeeGiven)).thenReturn(employeeGiven);
        Employee employeeResult = employeeService.createEmployee(employeeGiven);

        //Then
        Assert.assertEquals(employeeGiven, employeeResult);

    }

    @Test
    public void receivingAEmployeeAndAnId_returnEditedEmployee() {
        //Given
        Employee employeeToEdit = applicationDataProvider.getEmployee();

        Employee employeeEdited = applicationDataProvider.getEmployee()
                .role("GARÇON");

        //When
        Mockito.when(employeeRepository.findById(Mockito.any())).thenReturn(Optional.of(employeeToEdit));
        Mockito.when(employeeRepository.save(Mockito.any())).thenReturn(employeeEdited);
        Employee employeeResult = employeeService.editEmployee(1L,employeeToEdit);

        //Then
        Assert.assertEquals(employeeEdited,employeeResult);

    }

    @Test
    public void receivingAEmployeeAndAnId_thrownAnException() {
        //Given
        Employee employeeToEdit = applicationDataProvider.getEmployee();

        //When
        Mockito.when(employeeRepository.findById(Mockito.any())).thenReturn(Optional.empty());
        Assert.assertThrows(NullPointerException.class, () -> employeeService.editEmployee(1L,employeeToEdit));

    }

    @Test
    public void receivingAEmployeeAndAnId_deleteEmployee() {
        //Given
        Employee employeeToDelete = applicationDataProvider.getEmployee();

        //When
        Mockito.when(employeeRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(employeeToDelete));
        Mockito.doNothing().when(employeeRepository).deleteById(Mockito.anyLong());
        employeeService.deleteEmployee(1L);

        //Then
        Mockito.verify(employeeRepository, Mockito.times(1)).findById(Mockito.anyLong());
        Mockito.verify(employeeRepository,  Mockito.times(1)).deleteById(Mockito.anyLong());

    }

    @Test
    public void deleteEmployeeById_thrownAnException() {
        //Given
        //When
        Mockito.when(employeeRepository.findById(Mockito.any())).thenReturn(Optional.empty());
        Assert.assertThrows(NullPointerException.class, () -> employeeService.deleteEmployee(1L));
    }


    @Test
    public void passingAEmployeeId_thrownAnException() {
        //Given
        //When
        Mockito.when(employeeRepository.findById(Mockito.any())).thenReturn(Optional.empty());
        Assert.assertThrows(NullPointerException.class, () -> employeeService.getEmployee(1L));
    }

    @Test
    public void passingAEmployeeId_getEmployee() {
        //Given
        Employee employeeToGet = applicationDataProvider.getEmployee();

        //When
        Mockito.when(employeeRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(employeeToGet));
        Employee employeeResult = employeeService.getEmployee(1L);

        //Then
        Assert.assertEquals(employeeToGet,employeeResult);
    }

    @Test
    public void getAllEmployees_CheckIfEmployeeListHasTheResultExpected() {
        //Given
        Employee employeeToGet = applicationDataProvider.getEmployee();

        //When
        Mockito.when(employeeRepository.findAll()).thenReturn(Collections.singletonList(employeeToGet));
        List<Employee> employeeListResult = employeeService.getAllEmployees();

        //Then
        Assert.assertEquals(employeeToGet.getId(),employeeListResult.get(0).getId());
        Assert.assertEquals(employeeToGet.getRole(),employeeListResult.get(0).getRole());

    }

    @Test
    public void getAllEmployees_throwAnException() {

        //When
        Mockito.when(employeeRepository.findAll()).thenReturn(new ArrayList<>());

        //Then
        Assert.assertThrows(NullPointerException.class, () -> employeeService.getAllEmployees());

    }

    @Test
    public void requestingEmployeeIdAndRestaurantId_checkEmployeeWithRestaurantPersisted() {

        //Given
        Employee employeeGiven = applicationDataProvider.getEmployeeWithId();
        Restaurant restaurantGiven = applicationDataProvider.getRestaurant();
        restaurantGiven.setId(1L);

        //When
        Mockito.when(employeeRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(employeeGiven));
        Mockito.when(restaurantRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(restaurantGiven));
        Mockito.when(employeeRepository.save(Mockito.any())).thenReturn(employeeGiven);
        Employee employeeResult = employeeService.addRestaurant(1L,1L);

        //Then
        Assert.assertEquals(employeeGiven.getId(), employeeResult.getId());
        Assert.assertEquals(employeeGiven.getRole(), employeeResult.getRole());
        Assert.assertEquals(1, employeeResult.getRestaurantList().size());

    }

    @Test
    public void requestingEmployeeIdAndRestaurantId_checkEmployeeWithRestaurantRemoved() {

        //Given
        Employee employeeGiven = applicationDataProvider.getEmployeeWithId();
        Restaurant restaurantGiven = applicationDataProvider.getRestaurant();
        restaurantGiven.setId(1L);
        employeeGiven.addRestaurant(restaurantGiven);

        //When
        Mockito.when(employeeRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(employeeGiven));
        Mockito.when(restaurantRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(restaurantGiven));
        Mockito.when(employeeRepository.save(Mockito.any())).thenReturn(employeeGiven);
        Employee employeeResult = employeeService.removeRestaurant(1L,1L);

        //Then
        Assert.assertEquals(employeeGiven.getId(), employeeResult.getId());
        Assert.assertEquals(employeeGiven.getRole(), employeeResult.getRole());
        Assert.assertEquals(0, employeeResult.getRestaurantList().size());

    }
}
