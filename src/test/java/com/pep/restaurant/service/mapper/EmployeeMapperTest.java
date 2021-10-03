package com.pep.restaurant.service.mapper;

import com.pep.restaurant.ApplicationDataProvider;
import com.pep.restaurant.domain.Employee;
import com.pep.restaurant.service.model.EmployeeDTO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

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

        //When
        List<EmployeeDTO> employeeDTOResultList= employeeMapper.mapEmployeeListToEmployeeDTOList(employeeGivenList);

        //Then
        Assert.assertEquals(employeeGivenList.get(0).getId(), employeeDTOResultList.get(0).getId());
        Assert.assertEquals(employeeGivenList.get(0).getRole(), employeeDTOResultList.get(0).getRole());
        Assert.assertEquals(employeeGivenList.get(0).getSchedule().getId(), employeeDTOResultList.get(0).getId());
        Assert.assertEquals(employeeGivenList.get(0).getSchedule().getType(), employeeDTOResultList.get(0).getSchedule().getType());
        Assert.assertEquals(employeeGivenList.get(0).getRestaurantList().get(0).getId(), employeeDTOResultList.get(0).getRestaurantList().get(0).getId());
        Assert.assertEquals(employeeGivenList.get(0).getRestaurantList().get(0).getLocation(), employeeDTOResultList.get(0).getRestaurantList().get(0).getLocation());
        Assert.assertEquals(employeeGivenList.get(0).getRestaurantList().get(0).getCapacity(), employeeDTOResultList.get(0).getRestaurantList().get(0).getCapacity());
        Assert.assertEquals(employeeGivenList.get(0).getRestaurantList().get(0).getMenu().getLanguage(), employeeDTOResultList.get(0).getRestaurantList().get(0).getMenu().getLanguage());

    }

    @Test
    public void passingAnEmployeeListNull_checkThatEmployeeDTOListIsNull() {
        Assert.assertNull(employeeMapper.mapEmployeeListToEmployeeDTOList(null));
    }


    @Test
    public void passingAnEmployee_checkThatEmployeeDTOIsEquals() {
        //Given
        Employee employeeGiven = applicationDataProvider.getEmployee();

        //When
        EmployeeDTO employeeDTOResult = employeeMapper.mapEmployeeToEmployeeDTO(employeeGiven);

        //Then
        Assert.assertEquals(employeeGiven.getId(), employeeDTOResult.getId());
        Assert.assertEquals(employeeGiven.getRole(), employeeDTOResult.getRole());
        Assert.assertEquals(employeeGiven.getSchedule().getId(), employeeDTOResult.getId());
        Assert.assertEquals(employeeGiven.getSchedule().getType(), employeeDTOResult.getSchedule().getType());
        Assert.assertEquals(employeeGiven.getRestaurantList().get(0).getId(), employeeDTOResult.getRestaurantList().get(0).getId());
        Assert.assertEquals(employeeGiven.getRestaurantList().get(0).getLocation(), employeeDTOResult.getRestaurantList().get(0).getLocation());
        Assert.assertEquals(employeeGiven.getRestaurantList().get(0).getCapacity(), employeeDTOResult.getRestaurantList().get(0).getCapacity());
        Assert.assertEquals(employeeGiven.getRestaurantList().get(0).getMenu().getLanguage(), employeeDTOResult.getRestaurantList().get(0).getMenu().getLanguage());
    }

    @Test
    public void passingAnEmployeeNull_checkThatEmployeeDTOIsNull() {
        Assert.assertNull(employeeMapper.mapEmployeeToEmployeeDTO(null));
    }

    @Test
    public void passingAnEmployeeDTO_checkThatEmployeeIsEquals() {
        //Given
        EmployeeDTO employeeDTOGiven = applicationDataProvider.getEmployeeDTO();

        //When
        Employee employeeResult = employeeMapper.mapEmployeeDTOToEmployee(employeeDTOGiven);

        //Then
        Assert.assertEquals(employeeDTOGiven.getId(), employeeResult.getId());
        Assert.assertEquals(employeeDTOGiven.getRole(), employeeResult.getRole());
        Assert.assertEquals(employeeDTOGiven.getSchedule().getId(), employeeResult.getId());
        Assert.assertEquals(employeeDTOGiven.getSchedule().getType(), employeeResult.getSchedule().getType());
        Assert.assertEquals(employeeDTOGiven.getRestaurantList().get(0).getId(), employeeResult.getRestaurantList().get(0).getId());
        Assert.assertEquals(employeeDTOGiven.getRestaurantList().get(0).getLocation(), employeeResult.getRestaurantList().get(0).getLocation());
        Assert.assertEquals(employeeDTOGiven.getRestaurantList().get(0).getCapacity(), employeeResult.getRestaurantList().get(0).getCapacity());
        Assert.assertEquals(employeeDTOGiven.getRestaurantList().get(0).getMenu().getLanguage(), employeeResult.getRestaurantList().get(0).getMenu().getLanguage());
    }

    @Test
    public void passingAnEmployeeDTONull_checkThatEmployeeIsNull() {
        Assert.assertNull(employeeMapper.mapEmployeeDTOToEmployee(null));
    }
}