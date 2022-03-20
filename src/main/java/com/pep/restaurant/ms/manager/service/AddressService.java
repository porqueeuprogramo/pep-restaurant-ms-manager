package com.pep.restaurant.ms.manager.service;

import com.pep.restaurant.ms.manager.domain.Address;
import com.pep.restaurant.ms.manager.logging.Logger;
import com.pep.restaurant.ms.manager.logging.enumeration.LogTag;
import com.pep.restaurant.ms.manager.repository.AddressRepository;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    private static final Logger LOGGER = new Logger(AddressService.class);
    private final AddressRepository addressRepository;

    @Autowired
    public AddressService(final AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    /**
     * Create Address.
     * @param address address.
     * @return address created.
     */
    public Address createAddress(final Address address){
        final Optional<Address> addressOptional = addressRepository.findById(address.getId());
        if(addressOptional.isEmpty()){

            LOGGER.info(MDC.get("correlationId"), Arrays.asList(LogTag.ADDRESS, LogTag.PERSISTED),
                    "Create Address: " + address.toString());

            return addressRepository.save(address);
        }
        throw new NullPointerException("Address already exists!!!");
    }

    /**
     * Get Address.
     * @param addressId address id.
     * @return address retrieved.
     */
    public Address getAddress(final long addressId){
        final Optional<Address> addressOptional = addressRepository.findById(addressId);
        if(addressOptional.isEmpty()){
            throw new NullPointerException("Address to get not exists!!!");
        }

        LOGGER.info(MDC.get("correlationId"), Arrays.asList(LogTag.ADDRESS, LogTag.RETRIEVED),
                "Get Address by id: " + addressId);

        return addressOptional.get();
    }

    /**
     * Edit Address.
     * @param addressId address id.
     * @param addressNew address new.
     * @return address edited.
     */
    public Address editAddress(final long addressId,  final Address addressNew){
        final Optional<Address> addressOptional = addressRepository.findById(addressId);
        if(addressOptional.isEmpty()){
            throw new NullPointerException("Address to be edited not exists!!!");
        }
        //edit address
        addressOptional.get()
                        .name(addressNew.getName())
                        .postalCode(addressNew.getPostalCode())
                        .city(addressNew.getCity())
                        .country(addressNew.getCountry());

        LOGGER.info(MDC.get("correlationId"),  Arrays.asList(LogTag.ADDRESS, LogTag.EDITED),
                "Edit Address by id " + addressId);

        return addressRepository.save(addressOptional.get());
    }

    /**
     * Delete Address.
     * @param addressId address id.
     * @return address deleted.
     */
    public Address deleteAddress(final long addressId){
        final Optional<Address> addressOptional = addressRepository.findById(addressId);
        if(addressOptional.isEmpty()){
            throw new NullPointerException("Address to be deleted not exists!!!");
        }
        addressRepository.deleteById(addressId);

        LOGGER.info(MDC.get("correlationId"), Arrays.asList(LogTag.ADDRESS, LogTag.DELETED),
                "Delete Address by id: " + addressId);

        return addressOptional.get();
    }

    /**
     * Get All Address.
     * @return List of addresses.
     */
    public List<Address> getAllAddresses(){
        final List<Address> addressList = addressRepository.findAll();
        if(addressList.isEmpty()){
            throw new NullPointerException("No Address persisted!!!");
        }

        LOGGER.info(MDC.get("correlationId"), Arrays.asList(LogTag.ADDRESS, LogTag.RETRIEVED),
                "Get All Address from db");

        return addressList;
    }

}
