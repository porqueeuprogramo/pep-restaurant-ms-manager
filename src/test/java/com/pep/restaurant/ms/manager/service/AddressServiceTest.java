package com.pep.restaurant.ms.manager.service;

import com.pep.restaurant.ms.manager.ApplicationDataProvider;
import com.pep.restaurant.ms.manager.domain.Address;
import com.pep.restaurant.ms.manager.repository.AddressRepository;
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
public class AddressServiceTest {

    @InjectMocks
    AddressService addressService;

    @Mock
    AddressRepository addressRepository;

    ApplicationDataProvider applicationDataProvider = new ApplicationDataProvider();

    @Test
    public void passingAAddressThatNotExists_ReturnAddressSaved() {
        //Given
        Address addressGiven = applicationDataProvider.getAddress();

        //When
        Mockito.when(addressRepository.save(addressGiven)).thenReturn(addressGiven);
        Address addressResult = addressService.createAddress(addressGiven);

        //Then
        Assert.assertEquals(addressGiven, addressResult);

    }

    @Test
    public void receivingAAddressAndAnId_returnEditedAddress() {
        //Given
        Address addressToEdit = applicationDataProvider.getAddress();

        Address addressEdited = applicationDataProvider.getAddress()
                .name("Rua Test 2")
                .city("Porto")
                .country("Country")
                .postalCode("9999-999");

        //When
        Mockito.when(addressRepository.findById(Mockito.any())).thenReturn(Optional.of(addressToEdit));
        Mockito.when(addressRepository.save(Mockito.any())).thenReturn(addressEdited);
        Address addressResult = addressService.editAddress(1L, addressToEdit);

        //Then
        Assert.assertEquals(addressEdited, addressResult);

    }

    @Test
    public void receivingAAddressAndAnId_thrownAnException() {
        //Given
        Address addressToEdit = applicationDataProvider.getAddress();

        //When
        Mockito.when(addressRepository.findById(Mockito.any())).thenReturn(Optional.empty());
        Assert.assertThrows(NullPointerException.class, () -> addressService.editAddress(1L, addressToEdit));

    }

}
