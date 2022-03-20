package com.pep.restaurant.ms.manager.service.mapper;

import com.pep.restaurant.ms.manager.ApplicationDataProvider;
import com.pep.restaurant.ms.manager.domain.Address;
import com.pep.restaurant.ms.manager.service.model.AddressDTO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class AddressMapperTest {

    @InjectMocks
    AddressMapper addressMapper;

    ApplicationDataProvider applicationDataProvider = new ApplicationDataProvider();


    @Test
    public void passingAnAddressList_checkThatAddressDTOListIsEquals() {

        //Given
        List<Address> addressListGiven = Collections.singletonList(applicationDataProvider.getAddress());
        List<AddressDTO> addressDTOListToCompare = Collections.singletonList(applicationDataProvider.getAddressDTO());

        //when
        List<AddressDTO> addressDTOListResult = addressMapper.mapAddressListToAddressDTOList(addressListGiven);

        //Then
        Assert.assertEquals(addressDTOListToCompare.get(0).getName(), addressDTOListResult.get(0).getName());
        Assert.assertEquals(addressDTOListToCompare.get(0).getCity(), addressDTOListResult.get(0).getCity());
        Assert.assertEquals(addressDTOListToCompare.get(0).getCountry(), addressDTOListResult.get(0).getCountry());
        Assert.assertEquals(addressDTOListToCompare.get(0).getPostalCode(), addressDTOListResult.get(0).getPostalCode());

    }

    @Test
    public void passingAnAddress_checkThatAddressDTOIsEquals() {

        //Given
        Address addressGiven = applicationDataProvider.getAddress();
        AddressDTO addressDTOToCompare = applicationDataProvider.getAddressDTO();

        //when
        AddressDTO addressDTOResult = addressMapper.mapAddressToAddressDTO(addressGiven);

        //Then
        Assert.assertEquals(addressDTOToCompare.getName(), addressDTOResult.getName());
        Assert.assertEquals(addressDTOToCompare.getCity(), addressDTOResult.getCity());
        Assert.assertEquals(addressDTOToCompare.getCountry(), addressDTOResult.getCountry());
        Assert.assertEquals(addressDTOToCompare.getPostalCode(), addressDTOResult.getPostalCode());

    }

    @Test
    public void passingAnAddressDTO_checkThatAddressIsEquals() {

        //Given
        AddressDTO addressDTOGiven = applicationDataProvider.getAddressDTO();
        Address addressToCompare = applicationDataProvider.getAddress();

        //when
        Address addressResult = addressMapper.mapAddressDTOToAddress(addressDTOGiven);

        //Then
        Assert.assertEquals(addressToCompare.getName(), addressResult.getName());
        Assert.assertEquals(addressToCompare.getCity(), addressResult.getCity());
        Assert.assertEquals(addressToCompare.getCountry(), addressResult.getCountry());
        Assert.assertEquals(addressToCompare.getPostalCode(), addressResult.getPostalCode());

    }


}
