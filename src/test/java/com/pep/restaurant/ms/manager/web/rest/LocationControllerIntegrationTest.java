package com.pep.restaurant.ms.manager.web.rest;

import com.pep.restaurant.ms.manager.ApplicationDataProvider;
import com.pep.restaurant.ms.manager.RestaurantMsManagerApplication;
import com.pep.restaurant.ms.manager.domain.*;
import com.pep.restaurant.ms.manager.repository.*;
import com.pep.restaurant.ms.manager.service.mapper.LocationMapper;
import com.pep.restaurant.ms.manager.service.mapper.RestaurantMapper;
import com.pep.restaurant.ms.manager.service.model.LocationDTO;
import com.pep.restaurant.ms.manager.service.model.MenuDTO;
import com.pep.restaurant.ms.manager.service.model.RestaurantDTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RestaurantMsManagerApplication.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class LocationControllerIntegrationTest {

    @Autowired
    private LocationController locationController;

    @Autowired
    private LocationMapper locationMapper;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private AddressRepository addressRepository;

    ApplicationDataProvider applicationDataProvider = new ApplicationDataProvider();

    @Before
    public void clearDBLocation() {
        addressRepository.deleteAll();
        locationRepository.deleteAll();
    }

    @Test
    public void requestingALocationDTO_checkLocationSaved(){

        //Given
        Address address = applicationDataProvider.getAddress();
        Location location = applicationDataProvider.getLocation();
        location.setAddress(address);

        LocationDTO locationDTO = locationMapper.mapLocationToLocationDTO(location);

        //When
        ResponseEntity<LocationDTO> locationDTOResponseEntity =
                locationController.createLocation(locationDTO);

        //Then
        Assert.assertEquals(location.getCoordinate().getLatitude(),
                Objects.requireNonNull(locationDTOResponseEntity.getBody()).getLocationCoordinate().getLatitude(), 0);
        Assert.assertEquals(location.getCoordinate().getLongitude(),
                Objects.requireNonNull(locationDTOResponseEntity.getBody()).getLocationCoordinate().getLongitude(), 0);
        Assert.assertEquals(location.getAddress().getName(),
                locationDTOResponseEntity.getBody().getAddress().getName());
        Assert.assertEquals(location.getAddress().getPostalCode(),
                locationDTOResponseEntity.getBody().getAddress().getPostalCode());
        Assert.assertEquals(location.getAddress().getCity(),
                locationDTOResponseEntity.getBody().getAddress().getCity());
        Assert.assertEquals(location.getAddress().getCountry(),
                locationDTOResponseEntity.getBody().getAddress().getCountry());
    }

    @Test
    public void requestingALocationId_getLocation(){

        //Given
        Address address = applicationDataProvider.getAddress();
        addressRepository.save(address);

        Location location = applicationDataProvider.getLocation();
        locationRepository.save(location);

        location.setAddress(address);

        //When
        ResponseEntity<LocationDTO> locationDTOResponseEntity =
                locationController.getLocation(1L);

        //Then
        Assert.assertEquals(location.getCoordinate().getLatitude(),
                Objects.requireNonNull(locationDTOResponseEntity.getBody()).getLocationCoordinate().getLatitude(), 0);
        Assert.assertEquals(location.getCoordinate().getLongitude(),
                Objects.requireNonNull(locationDTOResponseEntity.getBody()).getLocationCoordinate().getLongitude(), 0);
        Assert.assertEquals(location.getAddress().getName(),
                locationDTOResponseEntity.getBody().getAddress().getName());
        Assert.assertEquals(location.getAddress().getPostalCode(),
                locationDTOResponseEntity.getBody().getAddress().getPostalCode());
        Assert.assertEquals(location.getAddress().getCity(),
                locationDTOResponseEntity.getBody().getAddress().getCity());
        Assert.assertEquals(location.getAddress().getCountry(),
                locationDTOResponseEntity.getBody().getAddress().getCountry());
    }

    @Test
    public void requestingALocationIdAndALocationToEdit_editLocation(){

        //Given
        Address address = applicationDataProvider.getAddress();
        addressRepository.save(address);

        Location location = applicationDataProvider.getLocation();
        locationRepository.save(location);

        location.setAddress(address);

        //Location to edit
        Address addressToEdit = applicationDataProvider.getAddress2();
        Location locationToEdit = applicationDataProvider.getLocation2();
        locationToEdit.setAddress(addressToEdit);

        LocationDTO locationToEditDTO = locationMapper.mapLocationToLocationDTO(locationToEdit);

        //When
        ResponseEntity<LocationDTO> locationDTOResponseEntity =
                locationController.editLocation(1L, locationToEditDTO);

        //Then
        Assert.assertEquals(locationToEdit.getCoordinate().getLatitude(),
                Objects.requireNonNull(locationDTOResponseEntity.getBody()).getLocationCoordinate().getLatitude(), 0);
        Assert.assertEquals(locationToEdit.getCoordinate().getLongitude(),
                Objects.requireNonNull(locationDTOResponseEntity.getBody()).getLocationCoordinate().getLongitude(), 0);
        Assert.assertEquals(locationToEdit.getAddress().getName(),
                locationDTOResponseEntity.getBody().getAddress().getName());
        Assert.assertEquals(locationToEdit.getAddress().getPostalCode(),
                locationDTOResponseEntity.getBody().getAddress().getPostalCode());
        Assert.assertEquals(locationToEdit.getAddress().getCity(),
                locationDTOResponseEntity.getBody().getAddress().getCity());
        Assert.assertEquals(locationToEdit.getAddress().getCountry(),
                locationDTOResponseEntity.getBody().getAddress().getCountry());
    }

    @Test
    public void requestingALocationId_deleteLocation(){

        //Given
        Address address = applicationDataProvider.getAddress();
        addressRepository.save(address);

        Location location = applicationDataProvider.getLocation();
        locationRepository.save(location);

        location.setAddress(address);

        //When
        ResponseEntity<LocationDTO> locationDTOResponseEntity =
                locationController.deleteLocation(1L);

        //Then
        Assert.assertEquals(location.getCoordinate().getLatitude(),
                Objects.requireNonNull(locationDTOResponseEntity.getBody()).getLocationCoordinate().getLatitude(), 0);
        Assert.assertEquals(location.getCoordinate().getLongitude(),
                Objects.requireNonNull(locationDTOResponseEntity.getBody()).getLocationCoordinate().getLongitude(), 0);
        Assert.assertEquals(location.getAddress().getName(),
                locationDTOResponseEntity.getBody().getAddress().getName());
        Assert.assertEquals(location.getAddress().getPostalCode(),
                locationDTOResponseEntity.getBody().getAddress().getPostalCode());
        Assert.assertEquals(location.getAddress().getCity(),
                locationDTOResponseEntity.getBody().getAddress().getCity());
        Assert.assertEquals(location.getAddress().getCountry(),
                locationDTOResponseEntity.getBody().getAddress().getCountry());
    }

    @Test
    public void callingGetAllLocation_checkLocationList(){

        //Given

        //Location 1
        Address address = applicationDataProvider.getAddress();
        addressRepository.save(address);

        Location location = applicationDataProvider.getLocation();
        locationRepository.save(location);

        location.setAddress(address);

        //Location 2
        Address address2 = applicationDataProvider.getAddress2();
        addressRepository.save(address2);

        Location location2 = applicationDataProvider.getLocation2();
        locationRepository.save(location2);

        location2.setAddress(address2);

        //When
        ResponseEntity<List<LocationDTO>> locationDTOResponseEntity =
                locationController.getAllLocations();

        //Then
        Assert.assertEquals(2,
                Objects.requireNonNull(locationDTOResponseEntity.getBody()).size());
    }

}
