package com.pep.restaurant.ms.manager.web.rest;

import com.pep.restaurant.ms.manager.ApplicationDataProvider;
import com.pep.restaurant.ms.manager.RestaurantMsManagerApplication;
import com.pep.restaurant.ms.manager.domain.Employee;
import com.pep.restaurant.ms.manager.domain.Menu;
import com.pep.restaurant.ms.manager.domain.Restaurant;
import com.pep.restaurant.ms.manager.repository.EmployeeRepository;
import com.pep.restaurant.ms.manager.repository.RestaurantRepository;
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
        ResponseEntity<EmployeeDTO> employeeDTOResponseEntity = employeeController.getEmployee(employee.getId());

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
                employeeController.editEmployee(employee.getId(),
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
                employeeController.deleteEmployee(employee.getId());

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
        Menu menu = applicationDataProvider.getMenu();
        //save menu
        menuRepository.save(menu);

        Restaurant restaurant = applicationDataProvider.getRestaurant();
        //save restaurant
        restaurantRepository.save(restaurant);

        Employee employee = applicationDataProvider.getEmployeeWithoutRestaurantListAndWithoutSchedule();
        employee.addRestaurant(restaurant);
        //save employee
        employeeRepository.save(employee);

        //When
        ResponseEntity<EmployeeDTO> employeeDTOResponseEntity =
                employeeController.removeRestaurant(1L, 1L);

        //Then
        Assert.assertEquals(0,
                Objects.requireNonNull(employeeDTOResponseEntity.getBody()).getRestaurantList().size());

    }

    @Test
    public void requestingEmployeeIdAndRestaurantId_addRestaurantToEmployeesList() {

        //Given
        Menu menu = applicationDataProvider.getMenu();
        //save menu
        menuRepository.save(menu);

        Restaurant restaurant = applicationDataProvider.getRestaurant();
        //save restaurant
        restaurantRepository.save(restaurant);

        Employee employee = applicationDataProvider.getEmployeeWithoutRestaurantListAndWithoutSchedule();
        //save employee
        employeeRepository.save(employee);

        //When
        ResponseEntity<EmployeeDTO> employeeDTOResponseEntity =
                employeeController.addRestaurant(1L, 1L);

        //Then
        Assert.assertEquals(1,
                Objects.requireNonNull(employeeDTOResponseEntity.getBody()).getRestaurantList().size());

    }
}
