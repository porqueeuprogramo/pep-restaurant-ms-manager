package com.pep.restaurant.ms.manager.service;

import com.pep.restaurant.ms.manager.ApplicationDataProvider;
import com.pep.restaurant.ms.manager.domain.Location;
import com.pep.restaurant.ms.manager.repository.LocationRepository;
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
public class LocationServiceTest {

    @InjectMocks
    LocationService locationService;

    @Mock
    LocationRepository locationRepository;

    ApplicationDataProvider applicationDataProvider = new ApplicationDataProvider();


    @Test
    public void passingALocationThatAlreadyExists_throwAnException() {
        //Given
        Location locationGiven = applicationDataProvider.getLocation();

        //When
        Mockito.when(locationRepository.findById(locationGiven.getId())).thenReturn(Optional.of(locationGiven));
        Assert.assertThrows(NullPointerException.class, () -> locationService.createLocation(locationGiven));

    }

    @Test
    public void passingALocationThatNotExists_ReturnLocationSaved() {
        //Given
        Location locationGiven = applicationDataProvider.getLocation();

        //When
        Mockito.when(locationRepository.findById(locationGiven.getId())).thenReturn(Optional.empty());
        Mockito.when(locationRepository.save(locationGiven)).thenReturn(locationGiven);
        Location locationResult = locationService.createLocation(locationGiven);

        //Then
        Assert.assertEquals(locationGiven, locationResult);

    }

    @Test
    public void receivingALocationAndAnId_returnEditedLocation() {
        //Given
        Location locationToEdit = applicationDataProvider.getLocation();

        Location locationEdited = applicationDataProvider.getLocation()
                .address(applicationDataProvider.getAddress())
                .locationCoordinate(applicationDataProvider.getCoordinate());

        //When
        Mockito.when(locationRepository.findById(Mockito.any())).thenReturn(Optional.of(locationToEdit));
        Mockito.when(locationRepository.save(Mockito.any())).thenReturn(locationEdited);
        Location locationResult = locationService.editLocation(1L, locationToEdit);

        //Then
        Assert.assertEquals(locationEdited, locationResult);

    }

    @Test
    public void receivingALocationAndAnId_thrownAnException() {
        //Given
        Location locationToEdit = applicationDataProvider.getLocation();

        //When
        Mockito.when(locationRepository.findById(Mockito.any())).thenReturn(Optional.empty());
        Assert.assertThrows(NullPointerException.class, () -> locationService
                .editLocation(1L, locationToEdit));

    }
}
