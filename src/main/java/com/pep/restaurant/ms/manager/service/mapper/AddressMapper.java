package com.pep.restaurant.ms.manager.service.mapper;

import com.pep.restaurant.ms.manager.domain.Address;
import com.pep.restaurant.ms.manager.service.model.AddressDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AddressMapper {

    /**
     * Map address list to address list DTO.
     * @param addressList address list.
     * @return Address List DTO.
     */
    public List<AddressDTO> mapAddressListToAddressDTOList(final List<Address> addressList) {
        return addressList != null
                ? addressList
                .stream()
                .map(this::mapAddressToAddressDTO)
                .collect(Collectors.toList())
                : null;
    }

    /**
     * Map Address to Address DTO.
     * @param address address.
     * @return AddressDTO.
     */
    public AddressDTO mapAddressToAddressDTO(final Address address) {
        return address != null ?
                new AddressDTO()
                        .name(address.getName())
                        .postalCode(address.getPostalCode())
                        .city(address.getCity())
                        .country(address.getCountry())
                : null;
    }

    /**
     * Map address DTO to address.
     * @param addressDTO address DTO.
     * @return Address.
     */
    public Address mapAddressDTOToAddress(final AddressDTO addressDTO) {
        return addressDTO != null ?
                new Address()
                        .name(addressDTO.getName())
                        .postalCode(addressDTO.getPostalCode())
                        .city(addressDTO.getCity())
                        .country(addressDTO.getCountry())
                : null;
    }

}
