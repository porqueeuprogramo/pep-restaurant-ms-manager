package com.pep.restaurant.ms.manager.service;

import com.pep.restaurant.ms.manager.ApplicationDataProvider;
import com.pep.restaurant.ms.manager.domain.*;
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

import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
public class RestaurantServiceTest {

    @InjectMocks
    RestaurantService restaurantService;

    @Mock
    AddressService addressService;

    @Mock
    LocationService locationService;

    @Mock
    MenuService menuService;
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
        Mockito.when(restaurantRepository.findByUid(restaurantGiven.getUid())).thenReturn(Optional.of(restaurantGiven));
        Assert.assertThrows(NullPointerException.class, () -> restaurantService.createRestaurant(restaurantGiven));

    }

    @Test
    public void passingARestaurantThatNotExists_ReturnRestaurantSaved() {
        //Given
        Restaurant restaurantGiven = applicationDataProvider.getRestaurant();

        //When
        Mockito.when(restaurantRepository.findByUid(restaurantGiven.getUid())).thenReturn(Optional.empty());
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
                .capacity(100);

        Address address = applicationDataProvider.getAddressWithId();
        Location location = applicationDataProvider.getLocationWithId();
        Menu menu = applicationDataProvider.getMenuWithId();

        //When
        Mockito.when(restaurantRepository.findByUid(any())).thenReturn(Optional.of(restaurantToEdit));
        Mockito.when(addressService.editAddress(Mockito.anyLong(), Mockito.any())).thenReturn(address);
        Mockito.when(locationService.editLocation(Mockito.anyLong(), Mockito.any())).thenReturn(location);
        Mockito.when(menuService.editMenu(Mockito.anyLong(), Mockito.any())).thenReturn(menu);
        Mockito.when(restaurantRepository.save(any())).thenReturn(restaurantEdited);
        Restaurant restaurantResult = restaurantService.editRestaurant("1L",restaurantToEdit);

        //Then
        Assert.assertEquals(restaurantEdited,restaurantResult);

    }

    @Test
    public void receivingARestaurantAndAnId_thrownAnException() {
        //Given
        Restaurant restaurantToEdit = applicationDataProvider.getRestaurant();

        //When
        Mockito.when(restaurantRepository.findByUid(any())).thenReturn(Optional.empty());
        Assert.assertThrows(NullPointerException.class, () -> restaurantService.editRestaurant("1L",restaurantToEdit));

    }

    @Test
    public void receivingARestaurantId_deleteRestaurant() {
        //Given
        Restaurant restaurantToDelete = applicationDataProvider.getRestaurant();

        //When
        Mockito.when(restaurantRepository.findByUid(Mockito.anyString())).thenReturn(Optional.of(restaurantToDelete));
        Mockito.doNothing().when(restaurantRepository).delete(any());
        restaurantService.deleteRestaurant("1L");

        //Then
        Mockito.verify(restaurantRepository, Mockito.times(1)).findByUid(Mockito.anyString());
        Mockito.verify(restaurantRepository,  Mockito.times(1)).delete(any());

    }

    @Test
    public void deleteRestaurantById_thrownAnException() {
        //Given
        //When
        Mockito.when(restaurantRepository.findByUid(any())).thenReturn(Optional.empty());
        Assert.assertThrows(NullPointerException.class, () -> restaurantService.deleteRestaurant("1L"));
    }


    @Test
    public void passingARestaurantId_thrownAnException() {
        //Given
        //When
        Mockito.when(restaurantRepository.findByUid(any())).thenReturn(Optional.empty());
        Assert.assertThrows(NullPointerException.class, () -> restaurantService.getRestaurant("1L"));
    }

    @Test
    public void passingARestaurantId_getRestaurant() {
        //Given
        Restaurant restaurantToGet = applicationDataProvider.getRestaurant();

        //When
        Mockito.when(restaurantRepository.findByUid(Mockito.any())).thenReturn(Optional.of(restaurantToGet));
        Restaurant restaurantResult = restaurantService.getRestaurant("1L");

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
            Assert.assertEquals(restaurantToGet.getEmployeeList(),restaurantListResult.get(0).getEmployeeList());
            Assert.assertEquals(restaurantToGet.getHereId(),restaurantListResult.get(0).getHereId());
            Assert.assertEquals(restaurantToGet.getSchedule(),restaurantListResult.get(0).getSchedule());
            Assert.assertEquals(restaurantToGet.getMenu(),restaurantListResult.get(0).getMenu());

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
        Mockito.when(restaurantRepository.findByUid(Mockito.any())).thenReturn(Optional.of(restaurantGiven));
        Mockito.when(employeeRepository.findByUid(Mockito.any())).thenReturn(Optional.of(employeeGiven));
        Mockito.when(restaurantRepository.save(any())).thenReturn(restaurantGiven);
        Restaurant restaurantResult = restaurantService.addEmployee("1L","1L");

        //Then
        Assert.assertEquals(restaurantGiven.getId(),restaurantResult.getId());
        Assert.assertEquals(restaurantGiven.getName(),restaurantResult.getName());
        Assert.assertEquals(restaurantGiven.getCapacity(),restaurantResult.getCapacity());
        Assert.assertEquals(restaurantGiven.getLocation(),restaurantResult.getLocation());
        Assert.assertEquals(restaurantGiven.getEmployeeList(),restaurantResult.getEmployeeList());
        Assert.assertEquals(restaurantGiven.getHereId(),restaurantResult.getHereId());
        Assert.assertEquals(restaurantGiven.getSchedule(),restaurantResult.getSchedule());
        Assert.assertEquals(restaurantGiven.getMenu(),restaurantResult.getMenu());
        Assert.assertEquals(1, restaurantResult.getEmployeeList().size());
    }

    @Test
    public void requestingEmployeeIdAndRestaurantIdToAddEmployeeToRestaurant_throwAnException() {

        //Given
        Restaurant restaurantGiven = applicationDataProvider.getRestaurant();

        //When
        Mockito.when(restaurantRepository.findByUid(Mockito.any())).thenReturn(Optional.of(restaurantGiven));

        //Then
        Assert.assertThrows(NullPointerException.class, () ->
                restaurantService.addEmployee("1L","1L"));

    }

    @Test
    public void requestingEmployeeIdAndRestaurantId_checkRestaurantWithEmployeeRemoved() {
        //Given
        Restaurant restaurantGiven = applicationDataProvider.getRestaurant();
        Employee employeeGiven = applicationDataProvider.getEmployeeWithId();
        restaurantGiven.addEmployee(employeeGiven);

        //When
        Mockito.when(restaurantRepository.findByUid(Mockito.any())).thenReturn(Optional.of(restaurantGiven));
        Mockito.when(employeeRepository.findByUid(Mockito.any())).thenReturn(Optional.of(employeeGiven));
        Mockito.when(restaurantRepository.save(any())).thenReturn(restaurantGiven);
        Restaurant restaurantResult = restaurantService.removeEmployee("1L","1L");

        //Then
        Assert.assertEquals(restaurantGiven.getId(),restaurantResult.getId());
        Assert.assertEquals(restaurantGiven.getName(),restaurantResult.getName());
        Assert.assertEquals(restaurantGiven.getCapacity(),restaurantResult.getCapacity());
        Assert.assertEquals(restaurantGiven.getLocation(),restaurantResult.getLocation());
        Assert.assertEquals(restaurantGiven.getEmployeeList(),restaurantResult.getEmployeeList());
        Assert.assertEquals(restaurantGiven.getHereId(),restaurantResult.getHereId());
        Assert.assertEquals(restaurantGiven.getSchedule(),restaurantResult.getSchedule());
        Assert.assertEquals(restaurantGiven.getMenu(),restaurantResult.getMenu());
        Assert.assertEquals(0, restaurantResult.getEmployeeList().size());
    }

    @Test
    public void requestingEmployeeIdAndRestaurantIdToRemoveEmployeeToRestaurant_throwAnException() {

        //Given
        Restaurant restaurantGiven = applicationDataProvider.getRestaurant();

        //When
        Mockito.when(restaurantRepository.findByUid(Mockito.any())).thenReturn(Optional.of(restaurantGiven));

        //Then
        Assert.assertThrows(NullPointerException.class, () ->
                restaurantService.removeEmployee("1L","1L"));

    }
}
