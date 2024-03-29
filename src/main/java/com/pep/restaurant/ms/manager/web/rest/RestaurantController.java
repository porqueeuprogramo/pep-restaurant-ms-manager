package com.pep.restaurant.ms.manager.web.rest;

import com.pep.restaurant.ms.manager.service.RestaurantService;
import com.pep.restaurant.ms.manager.service.mapper.RestaurantMapper;
import com.pep.restaurant.ms.manager.service.model.RestaurantDTO;
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

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@RestController
public class RestaurantController implements ApiController {

    public static final int OK = 200;
    public static final int INTERNAL_SERVER_ERROR = 500;
    public static final String RESTAURANT_RESTAURANT_ID = "/restaurant/{restaurantId}";
    public static final String RESTAURANT_ADD_EMPLOYEE_RESTAURANT_ID_EMPLOYEE_ID =
            "/restaurant/add/employee/{restaurantId}/{employeeId}";
    public static final String RESTAURANT_REMOVE_EMPLOYEE_RESTAURANT_ID_EMPLOYEE_ID =
            "/restaurant/remove/employee/{restaurantId}/{employeeId}";
    public static final String RESTAURANT = "/restaurant";
    private final RestaurantService restaurantService;
    private final RestaurantMapper restaurantMapper;

    /**
     * Constructor for Restaurant Controller.
     * @param restaurantService Restaurant Service.
     * @param restaurantMapper  Restaurant mapper.
     */
    public RestaurantController(final RestaurantService restaurantService,
                                final RestaurantMapper restaurantMapper) {
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
            @ApiResponse(code = OK, message = "Return restaurant got",
                    response = RestaurantDTO.class, responseContainer = "Restaurant"),
            @ApiResponse(code = INTERNAL_SERVER_ERROR, message = "Restaurant not exists",
                    response = RestaurantDTO.class, responseContainer = "Restaurant")
    })
    @GetMapping(value = RESTAURANT_RESTAURANT_ID,
            produces = {"application/json"},
            consumes = {"application/json"})
    public ResponseEntity<RestaurantDTO> getRestaurant(@PathVariable final String restaurantId) {
        return ResponseEntity.ok(restaurantMapper.mapRestaurantToRestaurantDTO(
                restaurantService.getRestaurant(restaurantId)));
    }

    /**
     * Controller to create a restaurant.
     *
     * @param restaurantDTO restaurantDTO to create.
     * @return RestaurantDTO created.
     */
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
    @PutMapping(value = RESTAURANT_RESTAURANT_ID,
            produces = {"application/json"},
            consumes = {"application/json"})
    public ResponseEntity<RestaurantDTO> editRestaurant(@PathVariable final String restaurantId,
                                                        @RequestBody final RestaurantDTO restaurantToEdit) {
        return ResponseEntity.ok(restaurantMapper.mapRestaurantToRestaurantDTO(
                restaurantService.editRestaurant(restaurantId,
                        restaurantMapper.mapRestaurantDTOToRestaurant(restaurantToEdit))));
    }

    /**
     * Controller to add employee to restaurant.
     * @param restaurantId restaurant id.
     * @param employeeId employee id.
     * @return Restaurant with employee added.
     */
    @PutMapping(value = RESTAURANT_ADD_EMPLOYEE_RESTAURANT_ID_EMPLOYEE_ID,
            produces = {"application/json"},
            consumes = {"application/json"})
    public ResponseEntity<RestaurantDTO> addEmployee(@PathVariable final String restaurantId,
                                                     @PathVariable final String employeeId) {
        return ResponseEntity.ok(restaurantMapper.mapRestaurantToRestaurantDTO(
                restaurantService.addEmployee(restaurantId, employeeId)));
    }

    /**
     * Controller to remove employee from restaurant.
     * @param restaurantId restaurant id.
     * @param employeeId employee id.
     * @return Restaurant with employee removed.
     */
    @PutMapping(value = RESTAURANT_REMOVE_EMPLOYEE_RESTAURANT_ID_EMPLOYEE_ID,
            produces = {"application/json"},
            consumes = {"application/json"})
    public ResponseEntity<RestaurantDTO> removeEmployee(@PathVariable final String restaurantId,
                                                     @PathVariable final String employeeId) {
        return ResponseEntity.ok(restaurantMapper.mapRestaurantToRestaurantDTO(
                restaurantService.removeEmployee(restaurantId, employeeId)));
    }

    /**
     * Controller to delete a restaurant by id.
     *
     * @param restaurantId restaurant id to be deleted.
     * @return RestaurantDTO deleted.
     */
    @DeleteMapping(value = RESTAURANT_RESTAURANT_ID,
            produces = {"application/json"},
            consumes = {"application/json"})
    public ResponseEntity<RestaurantDTO> deleteRestaurant(@PathVariable final String restaurantId) {
        return ResponseEntity.ok(restaurantMapper.mapRestaurantToRestaurantDTO(
                restaurantService.deleteRestaurant(restaurantId)));
    }

    /**
     * Controller to get a list with all restaurants.
     *
     * @return RestaurantsDTO list.
     */
    @GetMapping(value = RESTAURANT,
            produces = {"application/json"},
            consumes = {"application/json"})
    public ResponseEntity<List<RestaurantDTO>> getAllRestaurants() {
        return ResponseEntity.ok(restaurantMapper.mapRestaurantListToRestaurantDTOList(
                restaurantService.getAllRestaurants()));
    }

}
