package com.pep.restaurant.ms.manager.service.mapper;

import com.pep.restaurant.ms.manager.ApplicationDataProvider;
import com.pep.restaurant.ms.manager.domain.Location;
import com.pep.restaurant.ms.manager.service.model.LocationDTO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class LocationMapperTest {

    @InjectMocks
    LocationMapper locationMapper;

    ApplicationDataProvider applicationDataProvider = new ApplicationDataProvider();


    @Test
    public void passingALocationList_checkThatLocationDTOListIsEquals() {

        //Given
        List<Location> locationListGiven = Collections.singletonList(applicationDataProvider.getLocation());
        List<LocationDTO> locationListExpected = Collections.singletonList(applicationDataProvider.getLocationDTO());

        //When
        List<LocationDTO> locationListRetrieved = locationMapper.mapLocationListToLocationDTOList(locationListGiven);

        //Then
        Assert.assertEquals(locationListExpected.get(0).getLocationCoordinate().getLatitude(),
                locationListRetrieved.get(0).getLocationCoordinate().getLatitude(), 0);
        Assert.assertEquals(locationListExpected.get(0).getLocationCoordinate().getLongitude(),
                locationListRetrieved.get(0).getLocationCoordinate().getLongitude(), 0);
        Assert.assertEquals(locationListExpected.get(0).getAddress().getName(),
                locationListRetrieved.get(0).getAddress().getName());
        Assert.assertEquals(locationListExpected.get(0).getAddress().getCountry(),
                locationListRetrieved.get(0).getAddress().getCountry());
        Assert.assertEquals(locationListExpected.get(0).getAddress().getPostalCode(),
                locationListRetrieved.get(0).getAddress().getPostalCode());
        Assert.assertEquals(locationListExpected.get(0).getAddress().getCity(),
                locationListRetrieved.get(0).getAddress().getCity());

    }

    @Test
    public void passingALocationDTO_checkThatLocationIsEquals() {

        //Given
        LocationDTO locationDTOGiven = applicationDataProvider.getLocationDTO();
        Location locationExpected = applicationDataProvider.getLocation();

        //Then
        Location locationResult = locationMapper.mapLocationDTOToLocation(locationDTOGiven);

        //Then
        Assert.assertEquals(locationExpected.getCoordinate().getLatitude(),
                locationResult.getCoordinate().getLatitude(), 0);
        Assert.assertEquals(locationExpected.getCoordinate().getLongitude(),
                locationResult.getCoordinate().getLongitude(), 0);
        Assert.assertEquals(locationExpected.getAddress().getName(),
                locationResult.getAddress().getName());
        Assert.assertEquals(locationExpected.getAddress().getCountry(),
                locationResult.getAddress().getCountry());
        Assert.assertEquals(locationExpected.getAddress().getPostalCode(),
                locationResult.getAddress().getPostalCode());
        Assert.assertEquals(locationExpected.getAddress().getCity(),
                locationResult.getAddress().getCity());


    }




}
