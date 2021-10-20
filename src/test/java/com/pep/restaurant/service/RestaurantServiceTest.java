package com.pep.restaurant.service;

import com.pep.restaurant.ApplicationDataProvider;
import com.pep.restaurant.domain.Employee;
import com.pep.restaurant.domain.Restaurant;
import com.pep.restaurant.repository.EmployeeRepository;
import com.pep.restaurant.repository.RestaurantRepository;
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
public class RestaurantServiceTest {

    @InjectMocks
    RestaurantService restaurantService;

    @Mock
    RestaurantRepository restaurantRepository;

    @Mock
    EmployeeRepository employeeRepository;

    ApplicationDataProvider applicationDataProvider = new ApplicationDataProvider();


    @Test
    public void passingARestaurantThatAlreadyExists_throwAnException() {
        //Given
        Restaurant restaurantGiven = applicationDataProvider.getRestaurant();

        //When
        Mockito.when(restaurantRepository.findRestaurantByName(restaurantGiven.getName())).thenReturn(Optional.of(restaurantGiven));
        Assert.assertThrows(NullPointerException.class, () -> restaurantService.createRestaurant(restaurantGiven));

    }

    @Test
    public void passingARestaurantThatNotExists_ReturnRestaurantSaved() {
        //Given
        Restaurant restaurantGiven = applicationDataProvider.getRestaurant();

        //When
        Mockito.when(restaurantRepository.findRestaurantByName(restaurantGiven.getName())).thenReturn(Optional.empty());
        Mockito.when(restaurantRepository.save(restaurantGiven)).thenReturn(restaurantGiven);
        Restaurant restaurantResult = restaurantService.createRestaurant(restaurantGiven);

        //Then
        Assert.assertEquals(restaurantGiven, restaurantResult);

    }

    @Test
    public void receivingARestaurantAndAnId_returnEditedRestaurant() {
        //Given
        Restaurant restaurantToEdit = applicationDataProvider.getRestaurant();

        Restaurant restaurantEdited = applicationDataProvider.getRestaurant()
                .capacity(100)
                .location("Lisboa");

        //When
        Mockito.when(restaurantRepository.findById(Mockito.any())).thenReturn(Optional.of(restaurantToEdit));
        Mockito.when(restaurantRepository.save(Mockito.any())).thenReturn(restaurantEdited);
        Restaurant restaurantResult = restaurantService.editRestaurant(1L,restaurantToEdit);

        //Then
        Assert.assertEquals(restaurantEdited,restaurantResult);

    }

    @Test
    public void receivingARestaurantAndAnId_thrownAnException() {
        //Given
        Restaurant restaurantToEdit = applicationDataProvider.getRestaurant();

        //When
        Mockito.when(restaurantRepository.findById(Mockito.any())).thenReturn(Optional.empty());
        Assert.assertThrows(NullPointerException.class, () -> restaurantService.editRestaurant(1L,restaurantToEdit));

    }

    @Test
    public void receivingARestaurantId_deleteRestaurant() {
        //Given
        Restaurant restaurantToDelete = applicationDataProvider.getRestaurant();

        //When
        Mockito.when(restaurantRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(restaurantToDelete));
        Mockito.doNothing().when(restaurantRepository).deleteById(Mockito.anyLong());
        restaurantService.deleteRestaurant(1L);

        //Then
        Mockito.verify(restaurantRepository, Mockito.times(1)).findById(Mockito.anyLong());
        Mockito.verify(restaurantRepository,  Mockito.times(1)).deleteById(Mockito.anyLong());

    }

    @Test
    public void deleteRestaurantById_thrownAnException() {
        //Given
        //When
        Mockito.when(restaurantRepository.findById(Mockito.any())).thenReturn(Optional.empty());
        Assert.assertThrows(NullPointerException.class, () -> restaurantService.deleteRestaurant(1L));
    }


    @Test
    public void passingARestaurantId_thrownAnException() {
        //Given
        //When
        Mockito.when(restaurantRepository.findById(Mockito.any())).thenReturn(Optional.empty());
        Assert.assertThrows(NullPointerException.class, () -> restaurantService.getRestaurant(1L));
    }

    @Test
    public void passingARestaurantId_getRestaurant() {
        //Given
        Restaurant restaurantToGet = applicationDataProvider.getRestaurant();

        //When
        Mockito.when(restaurantRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(restaurantToGet));
        Restaurant restaurantResult = restaurantService.getRestaurant(1L);

        //Then
        Assert.assertEquals(restaurantToGet,restaurantResult);
    }

    @Test
    public void requestAllRestaurants_CheckIfRestaurantListHasTheResultExpected() {
        //Given
        Restaurant restaurantToGet = applicationDataProvider.getRestaurant();

        //When
        Mockito.when(restaurantRepository.findAll()).thenReturn(Collections.singletonList(restaurantToGet));
        List<Restaurant> restaurantListResult = restaurantService.getAllRestaurants();

        //Then
        Assert.assertEquals(restaurantToGet.getId(),restaurantListResult.get(0).getId());
        Assert.assertEquals(restaurantToGet.getName(),restaurantListResult.get(0).getName());
        Assert.assertEquals(restaurantToGet.getCapacity(),restaurantListResult.get(0).getCapacity());
        Assert.assertEquals(restaurantToGet.getLocation(),restaurantListResult.get(0).getLocation());

    }

    @Test
    public void requestAllRestaurants_throwAnException() {

        //When
        Mockito.when(restaurantRepository.findAll()).thenReturn(new ArrayList<>());

        //Then
        Assert.assertThrows(NullPointerException.class, () -> restaurantService.getAllRestaurants());

    }

    @Test
    public void requestingEmployeeIdAndRestaurantId_checkRestaurantWithEmployeePersisted() {

        //Given
        Restaurant restaurantGiven = applicationDataProvider.getRestaurant();
        restaurantGiven.id(1L);
        Employee employeeGiven = applicationDataProvider.getEmployeeWithoutRestaurantListAndWithoutSchedule();
        employeeGiven.id(1L);

        //When
        Mockito.when(restaurantRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(restaurantGiven));
        Mockito.when(employeeRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(employeeGiven));
        Mockito.when(restaurantRepository.save(Mockito.any())).thenReturn(restaurantGiven);
        Restaurant restaurantResult = restaurantService.addEmployee(1L,1L);

        //Then
        Assert.assertEquals(restaurantGiven.getId(),restaurantResult.getId());
        Assert.assertEquals(restaurantGiven.getName(),restaurantResult.getName());
        Assert.assertEquals(restaurantGiven.getCapacity(),restaurantResult.getCapacity());
        Assert.assertEquals(restaurantGiven.getLocation(),restaurantResult.getLocation());
        Assert.assertEquals(1, restaurantResult.getEmployeeList().size());

    }

    @Test
    public void requestingEmployeeIdAndRestaurantId_checkRestaurantWithEmployeeRemoved() {
        //Given
        Restaurant restaurantGiven = applicationDataProvider.getRestaurant();
        Employee employeeGiven = applicationDataProvider.getEmployeeWithId();
        restaurantGiven.addEmployee(employeeGiven);

        //When
        Mockito.when(restaurantRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(restaurantGiven));
        Mockito.when(employeeRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(employeeGiven));
        Mockito.when(restaurantRepository.save(Mockito.any())).thenReturn(restaurantGiven);
        Restaurant restaurantResult = restaurantService.removeEmployee(1L,1L);

        //Then
        Assert.assertEquals(restaurantGiven.getId(),restaurantResult.getId());
        Assert.assertEquals(restaurantGiven.getName(),restaurantResult.getName());
        Assert.assertEquals(restaurantGiven.getCapacity(),restaurantResult.getCapacity());
        Assert.assertEquals(restaurantGiven.getLocation(),restaurantResult.getLocation());
        Assert.assertEquals(0, restaurantResult.getEmployeeList().size());
    }
}