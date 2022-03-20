package com.pep.restaurant.ms.manager.web.rest;

import com.pep.restaurant.ms.manager.ApplicationDataProvider;
import com.pep.restaurant.ms.manager.RestaurantMsManagerApplication;
import com.pep.restaurant.ms.manager.domain.Address;
import com.pep.restaurant.ms.manager.repository.AddressRepository;
import com.pep.restaurant.ms.manager.service.mapper.AddressMapper;
import com.pep.restaurant.ms.manager.service.model.AddressDTO;
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
import java.util.List;
import java.util.Objects;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RestaurantMsManagerApplication.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class AddressControllerIntegrationTest {

    @Autowired
    private AddressController addressController;

    @Autowired
    private AddressMapper addressMapper;

    @Autowired
    private AddressRepository addressRepository;

    ApplicationDataProvider applicationDataProvider = new ApplicationDataProvider();

    @Before
    public void clearDBAddress() {
        addressRepository.deleteAll();
    }

    @Test
    public void requestingAnAddressDTO_checkAddressSaved(){

        //Given
        Address address = applicationDataProvider.getAddress();

        AddressDTO addressDTO = addressMapper.mapAddressToAddressDTO(address);

        //When
        ResponseEntity<AddressDTO> addressDTOResponseEntity =
                addressController.createAddress(addressDTO);

        //Then
        Assert.assertEquals(address.getName(),
                Objects.requireNonNull(addressDTOResponseEntity.getBody()).getName());
        Assert.assertEquals(address.getPostalCode(),
                addressDTOResponseEntity.getBody().getPostalCode());
        Assert.assertEquals(address.getCity(),
                addressDTOResponseEntity.getBody().getCity());
        Assert.assertEquals(address.getCountry(),
                addressDTOResponseEntity.getBody().getCountry());
    }

    @Test
    public void requestingAnAddressId_getAddress(){

        //Given
        Address address = applicationDataProvider.getAddress();
        addressRepository.save(address);

        //When
        ResponseEntity<AddressDTO> addressDTOResponseEntity =
                addressController.getAddress(1L);

        //Then
        Assert.assertEquals(address.getName(),
                Objects.requireNonNull(addressDTOResponseEntity.getBody()).getName());
        Assert.assertEquals(address.getPostalCode(),
                addressDTOResponseEntity.getBody().getPostalCode());
        Assert.assertEquals(address.getCity(),
                addressDTOResponseEntity.getBody().getCity());
        Assert.assertEquals(address.getCountry(),
                addressDTOResponseEntity.getBody().getCountry());
    }

    @Test
    public void requestingAnAddressIdAndAnAddressToEdit_editAddress(){

        //Given
        Address address = applicationDataProvider.getAddress();
        addressRepository.save(address);

        //Address to edit
        Address addressToEdit = applicationDataProvider.getAddress2();

        AddressDTO addressToEditDTO = addressMapper.mapAddressToAddressDTO(addressToEdit);

        //When
        ResponseEntity<AddressDTO> addressDTOResponseEntity =
                addressController.editAddress(1L, addressToEditDTO);

        //Then
        Assert.assertEquals(address.getName(),
                Objects.requireNonNull(addressDTOResponseEntity.getBody()).getName());
        Assert.assertEquals(address.getPostalCode(),
                addressDTOResponseEntity.getBody().getPostalCode());
        Assert.assertEquals(address.getCity(),
                addressDTOResponseEntity.getBody().getCity());
        Assert.assertEquals(address.getCountry(),
                addressDTOResponseEntity.getBody().getCountry());
    }

    @Test
    public void requestingAAddressId_deleteAddress(){

        //Given
        Address address = applicationDataProvider.getAddress();
        addressRepository.save(address);

        //When
        ResponseEntity<AddressDTO> addressDTOResponseEntity =
                addressController.deleteAddress(1L);

        //Then
        Assert.assertEquals(address.getName(),
                Objects.requireNonNull(addressDTOResponseEntity.getBody()).getName());
        Assert.assertEquals(address.getPostalCode(),
                addressDTOResponseEntity.getBody().getPostalCode());
        Assert.assertEquals(address.getCity(),
                addressDTOResponseEntity.getBody().getCity());
        Assert.assertEquals(address.getCountry(),
                addressDTOResponseEntity.getBody().getCountry());
    }

    @Test
    public void callingGetAllAddress_checkAddressList(){

        //Given
        //Address 1
        Address address = applicationDataProvider.getAddress();
        addressRepository.save(address);

        //Address 2
        Address address2 = applicationDataProvider.getAddress2();
        addressRepository.save(address2);

        //When
        ResponseEntity<List<AddressDTO>> addressDTOResponseEntity =
                addressController.getAllAddresses();

        //Then
        Assert.assertEquals(2,
                Objects.requireNonNull(addressDTOResponseEntity.getBody()).size());
    }
    
}
