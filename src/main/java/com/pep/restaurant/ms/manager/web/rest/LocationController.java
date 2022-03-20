package com.pep.restaurant.ms.manager.web.rest;

import com.pep.restaurant.ms.manager.service.LocationService;
import com.pep.restaurant.ms.manager.service.mapper.LocationMapper;
import com.pep.restaurant.ms.manager.service.model.LocationDTO;
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
public class LocationController implements ApiController {

    public static final int OK = 200;
    public static final int INTERNAL_SERVER_ERROR = 500;
    public static final String MENU_MENU_ID = "/location/{locationId}";
    public static final String MENU = "/location";
    private final LocationService locationService;
    private final LocationMapper locationMapper;

    /**
     * Constructor for Location Controller.
     *
     * @param locationService Location Service.
     * @param locationMapper  Location mapper.
     */
    public LocationController(final LocationService locationService, final LocationMapper locationMapper) {
        this.locationService = locationService;
        this.locationMapper = locationMapper;
    }

    /**
     * Controller to get a location by id.
     *
     * @param locationId id of location to get.
     * @return LocationDTO with the provided id.
     */
    @ApiOperation(
            value = "Get Location by id",
            notes = "This method allows us to get location by id")
    @ApiResponses(value = {
            @ApiResponse(code = OK, message = "Return location got",
                    response = LocationDTO.class, responseContainer = "Location"),
            @ApiResponse(code = INTERNAL_SERVER_ERROR, message = "Location not exists",
                    response = LocationDTO.class, responseContainer = "Location")
    })
    @GetMapping(value = MENU_MENU_ID,
            produces = {"application/json"},
            consumes = {"application/json"})
    public ResponseEntity<LocationDTO> getLocation(@PathVariable final long locationId) {
        return ResponseEntity.ok(locationMapper.mapLocationToLocationDTO(
                locationService.getLocation(locationId)));
    }

    /**
     * Controller to create a location.
     *
     * @param locationDTO locationDTO to create.
     * @return LocationDTO created.
     */
    @PostMapping(value = MENU,
            produces = {"application/json"},
            consumes = {"application/json"})
    public ResponseEntity<LocationDTO> createLocation(@RequestBody final LocationDTO locationDTO) {
        return ResponseEntity.ok(locationMapper.mapLocationToLocationDTO(
                locationService.createLocation(locationMapper.mapLocationDTOToLocation(locationDTO))));
    }

    /**
     * Controller to edit a location by id.
     *
     * @param locationId     location id to edit.
     * @param locationToEdit location update.
     * @return LocationDTO edited.
     */
    @PutMapping(value = MENU_MENU_ID,
            produces = {"application/json"},
            consumes = {"application/json"})
    public ResponseEntity<LocationDTO> editLocation(@PathVariable final long locationId,
                                                        @RequestBody final LocationDTO locationToEdit) {
        return ResponseEntity.ok(locationMapper.mapLocationToLocationDTO(
                locationService.editLocation(locationId,
                        locationMapper.mapLocationDTOToLocation(locationToEdit))));
    }

    /**
     * Controller to delete a location by id.
     *
     * @param locationId location id to be deleted.
     * @return LocationDTO deleted.
     */
    @DeleteMapping(value = MENU_MENU_ID,
            produces = {"application/json"},
            consumes = {"application/json"})
    public ResponseEntity<LocationDTO> deleteLocation(@PathVariable final long locationId) {
        return ResponseEntity.ok(locationMapper.mapLocationToLocationDTO(
                locationService.deleteLocation(locationId)));
    }

    /**
     * Controller to get a list with all locations.
     *
     * @return LocationsDTO list.
     */
    @GetMapping(value = MENU,
            produces = {"application/json"},
            consumes = {"application/json"})
    public ResponseEntity<List<LocationDTO>> getAllLocations() {
        return ResponseEntity.ok(locationMapper.mapLocationListToLocationDTOList(
                locationService.getAllLocations()));
    }

}
