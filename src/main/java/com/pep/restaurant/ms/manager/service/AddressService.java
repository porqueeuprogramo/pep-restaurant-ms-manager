package com.pep.restaurant.ms.manager.service;

import com.pep.restaurant.ms.manager.domain.Address;
import com.pep.restaurant.ms.manager.logging.Logger;
import com.pep.restaurant.ms.manager.logging.enumeration.LogTag;
import com.pep.restaurant.ms.manager.repository.AddressRepository;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
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
        LOGGER.info(MDC.get("correlationId"), Arrays.asList(LogTag.ADDRESS, LogTag.PERSISTED),
                "Create Address: " + address.toString());

        return addressRepository.save(address);
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

}
