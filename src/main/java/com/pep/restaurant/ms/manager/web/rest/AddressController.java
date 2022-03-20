package com.pep.restaurant.ms.manager.web.rest;

import com.pep.restaurant.ms.manager.service.AddressService;
import com.pep.restaurant.ms.manager.service.mapper.AddressMapper;
import com.pep.restaurant.ms.manager.service.model.AddressDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;

@RestController
public class AddressController implements ApiController {

    public static final int OK = 200;
    public static final int INTERNAL_SERVER_ERROR = 500;
    public static final String MENU_MENU_ID = "/address/{addressId}";
    public static final String MENU = "/address";
    private final AddressService addressService;
    private final AddressMapper addressMapper;

    /**
     * Constructor for Address Controller.
     *
     * @param addressService Address Service.
     * @param addressMapper  Address mapper.
     */
    public AddressController(final AddressService addressService, final AddressMapper addressMapper) {
        this.addressService = addressService;
        this.addressMapper = addressMapper;
    }

    /**
     * Controller to get a address by id.
     *
     * @param addressId id of address to get.
     * @return AddressDTO with the provided id.
     */
    @ApiOperation(
            value = "Get Address by id",
            notes = "This method allows us to get address by id")
    @ApiResponses(value = {
            @ApiResponse(code = OK, message = "Return address got",
                    response = AddressDTO.class, responseContainer = "Address"),
            @ApiResponse(code = INTERNAL_SERVER_ERROR, message = "Address not exists",
                    response = AddressDTO.class, responseContainer = "Address")
    })
    @GetMapping(value = MENU_MENU_ID,
            produces = {"application/json"},
            consumes = {"application/json"})
    public ResponseEntity<AddressDTO> getAddress(@PathVariable final long addressId) {
        return ResponseEntity.ok(addressMapper.mapAddressToAddressDTO(
                addressService.getAddress(addressId)));
    }

    /**
     * Controller to create a address.
     *
     * @param addressDTO addressDTO to create.
     * @return AddressDTO created.
     */
    @PostMapping(value = MENU,
            produces = {"application/json"},
            consumes = {"application/json"})
    public ResponseEntity<AddressDTO> createAddress(@RequestBody final AddressDTO addressDTO) {
        return ResponseEntity.ok(addressMapper.mapAddressToAddressDTO(
                addressService.createAddress(addressMapper.mapAddressDTOToAddress(addressDTO))));
    }

    /**
     * Controller to edit a address by id.
     *
     * @param addressId     address id to edit.
     * @param addressToEdit address update.
     * @return AddressDTO edited.
     */
    @PutMapping(value = MENU_MENU_ID,
            produces = {"application/json"},
            consumes = {"application/json"})
    public ResponseEntity<AddressDTO> editAddress(@PathVariable final long addressId,
                                                        @RequestBody final AddressDTO addressToEdit) {
        return ResponseEntity.ok(addressMapper.mapAddressToAddressDTO(
                addressService.editAddress(addressId,
                        addressMapper.mapAddressDTOToAddress(addressToEdit))));
    }

    /**
     * Controller to delete a address by id.
     *
     * @param addressId address id to be deleted.
     * @return AddressDTO deleted.
     */
    @DeleteMapping(value = MENU_MENU_ID,
            produces = {"application/json"},
            consumes = {"application/json"})
    public ResponseEntity<AddressDTO> deleteAddress(@PathVariable final long addressId) {
        return ResponseEntity.ok(addressMapper.mapAddressToAddressDTO(
                addressService.deleteAddress(addressId)));
    }

    /**
     * Controller to get a list with all addresss.
     *
     * @return AddresssDTO list.
     */
    @GetMapping(value = MENU,
            produces = {"application/json"},
            consumes = {"application/json"})
    public ResponseEntity<List<AddressDTO>> getAllAddresses() {
        return ResponseEntity.ok(addressMapper.mapAddressListToAddressDTOList(
                addressService.getAllAddresses()));
    }

}
