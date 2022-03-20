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
    public void passingAAddressThatAlreadyExists_throwAnException() {
        //Given
        Address addressGiven = applicationDataProvider.getAddress();

        //When
        Mockito.when(addressRepository.findById(addressGiven.getId())).thenReturn(Optional.of(addressGiven));
        Assert.assertThrows(NullPointerException.class, () -> addressService.createAddress(addressGiven));

    }

    @Test
    public void passingAAddressThatNotExists_ReturnAddressSaved() {
        //Given
        Address addressGiven = applicationDataProvider.getAddress();

        //When
        Mockito.when(addressRepository.findById(addressGiven.getId())).thenReturn(Optional.empty());
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
        Address addressResult = addressService.editAddress(1L,addressToEdit);

        //Then
        Assert.assertEquals(addressEdited, addressResult);

    }

    @Test
    public void receivingAAddressAndAnId_thrownAnException() {
        //Given
        Address addressToEdit = applicationDataProvider.getAddress();

        //When
        Mockito.when(addressRepository.findById(Mockito.any())).thenReturn(Optional.empty());
        Assert.assertThrows(NullPointerException.class, () -> addressService.editAddress(1L,addressToEdit));

    }

    @Test
    public void receivingAAddressAndAnId_deleteAddress() {
        //Given
        Address addressToDelete = applicationDataProvider.getAddress();

        //When
        Mockito.when(addressRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(addressToDelete));
        Mockito.doNothing().when(addressRepository).deleteById(Mockito.anyLong());
        addressService.deleteAddress(1L);

        //Then
        Mockito.verify(addressRepository, Mockito.times(1)).findById(Mockito.anyLong());
        Mockito.verify(addressRepository,  Mockito.times(1)).deleteById(Mockito.anyLong());

    }

    @Test
    public void deleteAddressById_thrownAnException() {
        //Given
        //When
        Mockito.when(addressRepository.findById(Mockito.any())).thenReturn(Optional.empty());
        Assert.assertThrows(NullPointerException.class, () -> addressService.deleteAddress(1L));
    }


    @Test
    public void passingAAddressId_thrownAnException() {
        //Given
        //When
        Mockito.when(addressRepository.findById(Mockito.any())).thenReturn(Optional.empty());
        Assert.assertThrows(NullPointerException.class, () -> addressService.getAddress(1L));
    }

    @Test
    public void passingAAddressId_getAddress() {
        //Given
        Address addressToGet = applicationDataProvider.getAddress();

        //When
        Mockito.when(addressRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(addressToGet));
        Address addressResult = addressService.getAddress(1L);

        //Then
        Assert.assertEquals(addressToGet, addressResult);
    }

    @Test
    public void getAllAddresss_CheckIfAddressListHasTheResultExpected() {
        //Given
        Address addressToGet = applicationDataProvider.getAddress();

        //When
        Mockito.when(addressRepository.findAll()).thenReturn(Collections.singletonList(addressToGet));
        List<Address> addressListResult = addressService.getAllAddresses();

        //Then
        Assert.assertEquals(addressToGet.getId(),addressListResult.get(0).getId());
        Assert.assertEquals(addressToGet.getCity(),addressListResult.get(0).getCity());
        Assert.assertEquals(addressToGet.getCountry(),addressListResult.get(0).getCountry());
        Assert.assertEquals(addressToGet.getName(),addressListResult.get(0).getName());
        Assert.assertEquals(addressToGet.getPostalCode(),addressListResult.get(0).getPostalCode());
    }

    @Test
    public void getAllAddresss_throwAnException() {

        //When
        Mockito.when(addressRepository.findAll()).thenReturn(new ArrayList<>());

        //Then
        Assert.assertThrows(NullPointerException.class, () -> addressService.getAllAddresses());

    }
}
