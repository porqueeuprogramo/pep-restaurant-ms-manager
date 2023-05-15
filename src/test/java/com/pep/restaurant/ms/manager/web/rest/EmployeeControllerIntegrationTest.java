package com.pep.restaurant.ms.manager.web.rest;

import com.pep.restaurant.ms.manager.ApplicationDataProvider;
import com.pep.restaurant.ms.manager.RestaurantMsManagerApplication;
import com.pep.restaurant.ms.manager.domain.Employee;
import com.pep.restaurant.ms.manager.domain.Restaurant;
import com.pep.restaurant.ms.manager.repository.EmployeeRepository;
import com.pep.restaurant.ms.manager.repository.RestaurantRepository;
import com.pep.restaurant.ms.manager.service.EmployeeService;
import com.pep.restaurant.ms.manager.service.RestaurantService;
import com.pep.restaurant.ms.manager.service.mapper.EmployeeMapper;
import com.pep.restaurant.ms.manager.service.model.EmployeeDTO;
import com.pep.restaurant.ms.manager.repository.MenuRepository;
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
public class EmployeeControllerIntegrationTest {

    @Autowired
    private EmployeeController employeeController;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    ApplicationDataProvider applicationDataProvider = new ApplicationDataProvider();

    @Before
    public void clearDBRestaurant() {
        restaurantRepository.deleteAll();
        menuRepository.deleteAll();
        employeeRepository.deleteAll();
    }

    @Test
    public void requestingARestaurantDTO_checkRestaurantSaved(){

        //Given
        Employee employee = applicationDataProvider.getEmployeeWithId();

        EmployeeDTO employeeDTO = employeeMapper.mapEmployeeToEmployeeDTO(employee);

        //When
        ResponseEntity<EmployeeDTO> employeeDTOResponseEntity = employeeController.createEmployee(employeeDTO);

        //Then
        Assert.assertEquals(employee.getRole(),
                Objects.requireNonNull(employeeDTOResponseEntity.getBody()).getRole());
        Assert.assertEquals(employee.getRestaurantList().size(),
                employeeDTOResponseEntity.getBody().getRestaurantList().size());

    }

    @Test
    public void requestingAEmployeeId_getEmployeeById(){

        //Given
        Employee employee = applicationDataProvider.getEmployeeWithoutRestaurantListAndWithoutSchedule();

        //save employee
        employeeRepository.save(employee);

        //When
        ResponseEntity<EmployeeDTO> employeeDTOResponseEntity = employeeController.getEmployee(employee.getUid());

        //Then
        Assert.assertEquals(employee.getRole(), Objects.requireNonNull(employeeDTOResponseEntity.getBody()).getRole());
    }

    @Test
    public void requestingAEmployeeToEdit_getEmployeeEdited(){
        //Given
        Employee employee = applicationDataProvider.getEmployeeWithoutRestaurantListAndWithoutSchedule();

        //save employee
        employeeRepository.save(employee);

        //restaurant edited
        Employee employeeEdited = applicationDataProvider
                .getEmployeeWithoutRestaurantListAndWithoutSchedule()
                .role("BOSS");

        //When
        ResponseEntity<EmployeeDTO> employeeDTOResponseEntity =
                employeeController.editEmployee(employee.getUid(),
                        employeeMapper.mapEmployeeToEmployeeDTO(employeeEdited));

        //Then
        Assert.assertEquals(employeeEdited.getRole(),
                Objects.requireNonNull(employeeDTOResponseEntity.getBody()).getRole());

    }

    @Test
    public void requestingAnEmployeeIdToDelete_checkEmployeeDeleted(){

        //Given
        Employee employee = applicationDataProvider.getEmployeeWithoutRestaurantListAndWithoutSchedule();

        //save employee
        employeeRepository.save(employee);

        //When
        ResponseEntity<EmployeeDTO> employeeDTOResponseEntity =
                employeeController.deleteEmployee(employee.getUid());

        //Then
        Assert.assertEquals(employee.getRole(), Objects.requireNonNull(employeeDTOResponseEntity.getBody()).getRole());

    }

    @Test
    public void callingGetAllEmployees_checkEmployeesList(){

        //Given
        Employee employee = applicationDataProvider.getEmployeeWithoutRestaurantListAndWithoutSchedule();

        Employee employee2 = applicationDataProvider.getEmployeeWithoutRestaurantListAndWithoutSchedule();
        employee2.role("BOSS");

        //save restaurant
        employeeRepository.save(employee);

        //save restaurant2
        employeeRepository.save(employee2);

        //When
        ResponseEntity<List<EmployeeDTO>> employeeDTOResponseEntity =
                employeeController.getAllEmployees();

        //Then
        Assert.assertEquals(2, Objects.requireNonNull(employeeDTOResponseEntity.getBody()).size());

    }

    @Test
    public void requestingEmployeeIdAndRestaurantId_removeRestaurantFromEmployeesList() {

        //Given
        Restaurant restaurant = applicationDataProvider.getRestaurantToCreate();
        //save restaurant
        Restaurant restaurantSaved = restaurantService.createRestaurant(restaurant);

        //save employee
        Employee employee = applicationDataProvider.getEmployeeToCreate();
        Employee employeeSaved = employeeService.createEmployee(employee);

        employeeService.addRestaurant(employeeSaved.getUid(), restaurantSaved.getUid());

        //When
        ResponseEntity<EmployeeDTO> employeeDTOResponseEntity =
                employeeController.removeRestaurant(employeeSaved.getUid(), restaurantSaved.getUid());

        //Then
        Assert.assertEquals(0,
                Objects.requireNonNull(employeeDTOResponseEntity.getBody()).getRestaurantList().size());

    }

    @Test
    public void requestingEmployeeIdAndRestaurantId_addRestaurantToEmployeesList() {

        //Given
        Restaurant restaurant = applicationDataProvider.getRestaurantToCreate();
        //save restaurant
        Restaurant restaurantSaved = restaurantService.createRestaurant(restaurant);

        //save employee
        Employee employee = applicationDataProvider.getEmployeeToCreate();
        Employee employeeSaved = employeeService.createEmployee(employee);

        //When
        ResponseEntity<EmployeeDTO> employeeDTOResponseEntity =
                employeeController.addRestaurant(employeeSaved.getUid(), restaurantSaved.getUid());

        //Then
        Assert.assertEquals(1,
                Objects.requireNonNull(employeeDTOResponseEntity.getBody()).getRestaurantList().size());

    }
}
