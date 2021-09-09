package com.pep.restaurant.web.rest;

import com.pep.restaurant.service.RestaurantService;
import com.pep.restaurant.service.mapper.RestaurantMapper;
import com.pep.restaurant.service.model.RestaurantDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import java.util.List;

@RestController
public class RestaurantController {

    public static final String RESTAURANT_RESTAURANT_ID = "/restaurant/{restaurantId}";
    public static final String RESTAURANT = "/restaurant";
    private final RestaurantService restaurantService;
    private final RestaurantMapper restaurantMapper;

    /**
     * Constructor for Restaurant Controller.
     *
     * @param restaurantService Restaurant Service.
     * @param restaurantMapper  Restaurant mapper.
     */
    public RestaurantController(final RestaurantService restaurantService, final RestaurantMapper restaurantMapper) {
        this.restaurantService = restaurantService;
        this.restaurantMapper = restaurantMapper;
    }

    /**
     * Controller to get a restaurant by id.
     *
     * @param restaurantId id of restaurant to get.
     * @return RestaurantDTO with the provided id.
     */
    @ApiOperation(
            value = "Get Restaurant by id",
            notes = "This method allows us to get restaurant by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Return restaurant got",
                    response = RestaurantDTO.class, responseContainer = "Restaurant"),
            @ApiResponse(code = 500, message = "Restaurant not exists",
                    response = RestaurantDTO.class, responseContainer = "Restaurant")
    })
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping(value = RESTAURANT_RESTAURANT_ID,
            produces = {"application/json"},
            consumes = {"application/json"})
    public ResponseEntity<RestaurantDTO> getRestaurant(@PathVariable final long restaurantId) {
        return ResponseEntity.ok(restaurantMapper.mapRestaurantToRestaurantDTO(
                restaurantService.getRestaurant(restaurantId)));
    }

    /**
     * Controller to create a restaurant.
     *
     * @param restaurantDTO restaurantDTO to create.
     * @return RestaurantDTO created.
     */
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PostMapping(value = RESTAURANT,
            produces = {"application/json"},
            consumes = {"application/json"})
    public ResponseEntity<RestaurantDTO> createRestaurant(@RequestBody final RestaurantDTO restaurantDTO) {
        return ResponseEntity.ok(restaurantMapper.mapRestaurantToRestaurantDTO(
                restaurantService.createRestaurant(restaurantMapper.mapRestaurantDTOToRestaurant(restaurantDTO))));
    }

    /**
     * Controller to edit a restaurant by id.
     *
     * @param restaurantId     restaurant id to edit.
     * @param restaurantToEdit restaurant update.
     * @return RestaurantDTO edited.
     */
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PutMapping(value = RESTAURANT_RESTAURANT_ID,
            produces = {"application/json"},
            consumes = {"application/json"})
    public ResponseEntity<RestaurantDTO> editRestaurant(@PathVariable final long restaurantId,
                                                        @RequestBody final RestaurantDTO restaurantToEdit) {
        return ResponseEntity.ok(restaurantMapper.mapRestaurantToRestaurantDTO(
                restaurantService.editRestaurant(restaurantId,
                        restaurantMapper.mapRestaurantDTOToRestaurant(restaurantToEdit))));
    }

    /**
     * Controller to delete a restaurant by id.
     *
     * @param restaurantId restaurant id to be deleted.
     * @return RestaurantDTO deleted.
     */
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @DeleteMapping(value = RESTAURANT_RESTAURANT_ID,
            produces = {"application/json"},
            consumes = {"application/json"})
    public ResponseEntity<RestaurantDTO> deleteRestaurant(@PathVariable final long restaurantId) {
        return ResponseEntity.ok(restaurantMapper.mapRestaurantToRestaurantDTO(
                restaurantService.deleteRestaurant(restaurantId)));
    }

    /**
     * Controller to get a list with all restaurants.
     *
     * @return RestaurantsDTO list.
     */
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping(value = RESTAURANT,
            produces = {"application/json"},
            consumes = {"application/json"})
    public ResponseEntity<List<RestaurantDTO>> getAllRestaurants() {
        return ResponseEntity.ok(restaurantMapper.mapRestaurantListToRestaurantDTOList(
                restaurantService.getAllRestaurants()));
    }

}
