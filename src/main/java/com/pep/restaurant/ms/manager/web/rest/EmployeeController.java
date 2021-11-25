package com.pep.restaurant.ms.manager.web.rest;

import com.pep.restaurant.ms.manager.service.EmployeeService;
import com.pep.restaurant.ms.manager.service.mapper.EmployeeMapper;
import com.pep.restaurant.ms.manager.service.mapper.RestaurantMapper;
import com.pep.restaurant.ms.manager.service.model.EmployeeDTO;
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
public class EmployeeController implements ApiController {

    public static final int OK = 200;
    public static final int INTERNAL_SERVER_ERROR = 500;
    public static final String EMPLOYEE_EMPLOYEE_ID = "/employee/{employeeId}";
    public static final String EMPLOYEE_ADD_RESTAURANT_EMPLOYEE_ID_RESTAURANT_ID =
            "/employee/add/restaurant/{employeeId}/{restaurantId}";
    public static final String EMPLOYEE_REMOVE_RESTAURANT_EMPLOYEE_ID_RESTAURANT_ID =
            "/employee/remove/restaurant/{employeeId}/{restaurantId}";
    public static final String EMPLOYEE = "/employee";
    private final EmployeeService employeeService;
    private final EmployeeMapper employeeMapper;
    private final RestaurantMapper restaurantMapper;

    /**
     * Constructor for Employee Controller.
     * @param employeeService Employee Service.
     * @param employeeMapper  Employee mapper.
     * @param restaurantMapper
     */
    public EmployeeController(final EmployeeService employeeService, final EmployeeMapper employeeMapper,
                              final RestaurantMapper restaurantMapper) {
        this.employeeService = employeeService;
        this.employeeMapper = employeeMapper;
        this.restaurantMapper = restaurantMapper;
    }

    /**
     * Controller to get a employee by id.
     *
     * @param employeeId id of employee to get.
     * @return EmployeeDTO with the provided id.
     */
    @ApiOperation(
            value = "Get Employee by id",
            notes = "This method allows us to get employee by id")
    @ApiResponses(value = {
            @ApiResponse(code = OK, message = "Return employee got",
                    response = EmployeeDTO.class, responseContainer = "Employee"),
            @ApiResponse(code = INTERNAL_SERVER_ERROR, message = "Employee not exists",
                    response = EmployeeDTO.class, responseContainer = "Employee")
    })
    @GetMapping(value = EMPLOYEE_EMPLOYEE_ID,
            produces = {"application/json"},
            consumes = {"application/json"})
    public ResponseEntity<EmployeeDTO> getEmployee(@PathVariable final long employeeId) {
        return ResponseEntity.ok(employeeMapper.mapEmployeeToEmployeeDTO(
                employeeService.getEmployee(employeeId)));
    }

    /**
     * Controller to create a employee.
     *
     * @param employeeDTO employeeDTO to create.
     * @return EmployeeDTO created.
     */
    @PostMapping(value = EMPLOYEE,
            produces = {"application/json"},
            consumes = {"application/json"})
    public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody final EmployeeDTO employeeDTO) {
        return ResponseEntity.ok(employeeMapper.mapEmployeeToEmployeeDTO(
                employeeService.createEmployee(employeeMapper.mapEmployeeDTOToEmployee(employeeDTO))));
    }

    /**
     * Controller to edit a employee by id.
     *
     * @param employeeId     employee id to edit.
     * @param employeeToEdit employee update.
     * @return EmployeeDTO edited.
     */
    @PutMapping(value = EMPLOYEE_EMPLOYEE_ID,
            produces = {"application/json"},
            consumes = {"application/json"})
    public ResponseEntity<EmployeeDTO> editEmployee(@PathVariable final long employeeId,
                                                        @RequestBody final EmployeeDTO employeeToEdit) {
        return ResponseEntity.ok(employeeMapper.mapEmployeeToEmployeeDTO(
                employeeService.editEmployee(employeeId, employeeMapper.mapEmployeeDTOToEmployee(employeeToEdit))));
    }

    /**
     * Controller to delete a employee by id.
     *
     * @param employeeId employee id to be deleted.
     * @return EmployeeDTO deleted.
     */
    @DeleteMapping(value = EMPLOYEE_EMPLOYEE_ID,
            produces = {"application/json"},
            consumes = {"application/json"})
    public ResponseEntity<EmployeeDTO> deleteEmployee(@PathVariable final long employeeId) {
        return ResponseEntity.ok(employeeMapper.mapEmployeeToEmployeeDTO(
                employeeService.deleteEmployee(employeeId)));
    }

    /**
     * Controller to get a list with all employees.
     *
     * @return EmployeesDTO list.
     */
    @GetMapping(value = EMPLOYEE,
            produces = {"application/json"},
            consumes = {"application/json"})
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
        return ResponseEntity.ok(employeeMapper.mapEmployeeListToEmployeeDTOList(
                employeeService.getAllEmployees()));
    }

    /**
     * Controller to add restaurant to employee.
     * @param employeeId employee id.
     * @param restaurantId restaurant id.
     * @return Employee with restuarant added.
     */
    @PutMapping(value = EMPLOYEE_ADD_RESTAURANT_EMPLOYEE_ID_RESTAURANT_ID,
            produces = {"application/json"},
            consumes = {"application/json"})
    public ResponseEntity<EmployeeDTO> addRestaurant(@PathVariable final long employeeId,
                                                     @PathVariable final long restaurantId) {
        return ResponseEntity.ok(employeeMapper.mapEmployeeToEmployeeDTO(
                employeeService.addRestaurant(employeeId, restaurantId)));
    }

    /**
     * Controller to remove restaurant from employee.
     * @param employeeId employee id.
     * @param restaurantId restaurant id.
     * @return Employee with restaurant removed.
     */
    @PutMapping(value = EMPLOYEE_REMOVE_RESTAURANT_EMPLOYEE_ID_RESTAURANT_ID,
            produces = {"application/json"},
            consumes = {"application/json"})
    public ResponseEntity<EmployeeDTO> removeRestaurant(@PathVariable final long employeeId,
                                                        @PathVariable final long restaurantId) {
        return ResponseEntity.ok(employeeMapper.mapEmployeeToEmployeeDTO(
                employeeService.removeRestaurant(employeeId, restaurantId)));
    }

}
