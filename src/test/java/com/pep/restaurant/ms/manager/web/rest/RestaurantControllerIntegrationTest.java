package com.pep.restaurant.ms.manager.web.rest;

import com.pep.restaurant.ms.manager.ApplicationDataProvider;
import com.pep.restaurant.ms.manager.RestaurantMsManagerApplication;
import com.pep.restaurant.ms.manager.domain.*;
import com.pep.restaurant.ms.manager.repository.*;
import com.pep.restaurant.ms.manager.service.EmployeeService;
import com.pep.restaurant.ms.manager.service.RestaurantService;
import com.pep.restaurant.ms.manager.service.mapper.RestaurantMapper;
import com.pep.restaurant.ms.manager.service.model.MenuDTO;
import com.pep.restaurant.ms.manager.service.model.RestaurantDTO;
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
public class RestaurantControllerIntegrationTest {

    @Autowired
    private RestaurantController restaurantController;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private RestaurantMapper restaurantMapper;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    ApplicationDataProvider applicationDataProvider = new ApplicationDataProvider();

    @Before
    public void clearDBRestaurant() {
        addressRepository.deleteAll();
        locationRepository.deleteAll();
        restaurantRepository.deleteAll();
        menuRepository.deleteAll();
        employeeRepository.deleteAll();
    }

    @Test
    public void requestingARestaurantDTO_checkRestaurantSaved(){

        //Given
        Menu menu = applicationDataProvider.getMenu();

        //save menu
        menuRepository.save(menu);

        Restaurant restaurant = applicationDataProvider.getRestaurant();
        restaurant.setMenu(menu);

        RestaurantDTO restaurantDTO = restaurantMapper.mapRestaurantToRestaurantDTO(restaurant);

        //Menu DTO created by menu
        MenuDTO menuDTOSaved = new MenuDTO()
            .language(menu.getLanguage());
        restaurantDTO.setMenu(menuDTOSaved);

        //When
        ResponseEntity<RestaurantDTO> restaurantDTOResponseEntity =
                restaurantController.createRestaurant(restaurantDTO);

        //Then
        Assert.assertEquals(restaurant.getName(),
                Objects.requireNonNull(restaurantDTOResponseEntity.getBody()).getName());
        Assert.assertEquals(restaurant.getHereId(),
                Objects.requireNonNull(restaurantDTOResponseEntity.getBody()).getHereId());
        Assert.assertEquals(restaurant.getCapacity(), restaurantDTOResponseEntity.getBody().getCapacity());
        Assert.assertEquals(restaurant.getLocation().getCoordinate().getLatitude(),
                restaurantDTOResponseEntity.getBody().getLocation().getLocationCoordinate().getLatitude(), 0);
        Assert.assertEquals(restaurant.getLocation().getCoordinate().getLongitude(),
                restaurantDTOResponseEntity.getBody().getLocation().getLocationCoordinate().getLongitude(), 0);
        Assert.assertEquals(restaurant.getLocation().getAddress().getName(),
                restaurantDTOResponseEntity.getBody().getLocation().getAddress().getName());
        Assert.assertEquals(restaurant.getLocation().getAddress().getPostalCode(),
                restaurantDTOResponseEntity.getBody().getLocation().getAddress().getPostalCode());
        Assert.assertEquals(restaurant.getLocation().getAddress().getCity(),
                restaurantDTOResponseEntity.getBody().getLocation().getAddress().getCity());
        Assert.assertEquals(restaurant.getLocation().getAddress().getCountry(),
                restaurantDTOResponseEntity.getBody().getLocation().getAddress().getCountry());
        Assert.assertEquals(restaurant.getCapacity(), restaurantDTOResponseEntity.getBody().getCapacity());
        Assert.assertEquals(restaurant.getMenu().getLanguage(),
                restaurantDTOResponseEntity.getBody().getMenu().getLanguage());
        Assert.assertEquals(restaurant.getScheduleRoutine().getScheduleRoutine().size(),
                restaurantDTOResponseEntity.getBody().getSchedule().getScheduleRoutine().size());
        Assert.assertEquals(restaurant.getEmployeeList().size(),
                restaurantDTOResponseEntity.getBody().getEmployeeList().size());
    }

    @Test
    public void requestingARestaurantId_getRestaurantById(){

        Employee employee = applicationDataProvider.getEmployeeToCreate();
        employeeService.createEmployee(employee);

        Restaurant restaurant = applicationDataProvider.getRestaurantToCreate();
        restaurantService.createRestaurant(restaurant);

        restaurant.addEmployee(employee);

        //When
        ResponseEntity<RestaurantDTO> restaurantDTOResponseEntity =
                restaurantController.getRestaurant(restaurant.getUid());

        //Then
        Assert.assertEquals(restaurant.getName(),
                Objects.requireNonNull(restaurantDTOResponseEntity.getBody()).getName());
        Assert.assertEquals(restaurant.getHereId(),
                Objects.requireNonNull(restaurantDTOResponseEntity.getBody()).getHereId());
        Assert.assertEquals(restaurant.getCapacity(), restaurantDTOResponseEntity.getBody().getCapacity());
        Assert.assertEquals(restaurant.getLocation().getCoordinate().getLatitude(),
                restaurantDTOResponseEntity.getBody().getLocation().getLocationCoordinate().getLatitude(), 0);
        Assert.assertEquals(restaurant.getLocation().getCoordinate().getLongitude(),
                restaurantDTOResponseEntity.getBody().getLocation().getLocationCoordinate().getLongitude(), 0);
        Assert.assertEquals(restaurant.getLocation().getAddress().getName(),
                restaurantDTOResponseEntity.getBody().getLocation().getAddress().getName());
        Assert.assertEquals(restaurant.getLocation().getAddress().getPostalCode(),
                restaurantDTOResponseEntity.getBody().getLocation().getAddress().getPostalCode());
        Assert.assertEquals(restaurant.getLocation().getAddress().getCity(),
                restaurantDTOResponseEntity.getBody().getLocation().getAddress().getCity());
        Assert.assertEquals(restaurant.getLocation().getAddress().getCountry(),
                restaurantDTOResponseEntity.getBody().getLocation().getAddress().getCountry());
        Assert.assertEquals(restaurant.getCapacity(), restaurantDTOResponseEntity.getBody().getCapacity());
        Assert.assertEquals(restaurant.getMenu().getLanguage(),
                restaurantDTOResponseEntity.getBody().getMenu().getLanguage());
        Assert.assertEquals(restaurant.getScheduleRoutine().getScheduleRoutine().size(),
                restaurantDTOResponseEntity.getBody().getSchedule().getScheduleRoutine().size());
        Assert.assertEquals(restaurant.getEmployeeList().size(),
                restaurantDTOResponseEntity.getBody().getEmployeeList().size());

    }

    @Test
    public void requestingARestaurantToEdit_getRestaurantEdited(){
        //Given
        Restaurant restaurant = applicationDataProvider.getRestaurantToCreate();
        Restaurant restaurantSaved = restaurantService.createRestaurant(restaurant);

        //restaurant edited
        Restaurant restaurantEdited = applicationDataProvider.getRestaurantToCreate();

        //When
        ResponseEntity<RestaurantDTO> restaurantDTOResponseEntity =
                restaurantController.editRestaurant(restaurantSaved.getUid(),
                        restaurantMapper.mapRestaurantToRestaurantDTO(restaurantEdited));

        //Then
        Assert.assertEquals(restaurantEdited.getName(),
                Objects.requireNonNull(restaurantDTOResponseEntity.getBody()).getName());
        Assert.assertEquals(restaurant.getHereId(),
                Objects.requireNonNull(restaurantDTOResponseEntity.getBody()).getHereId());
        Assert.assertEquals(restaurantEdited.getCapacity(), restaurantDTOResponseEntity.getBody().getCapacity());
        Assert.assertEquals(restaurant.getLocation().getCoordinate().getLatitude(),
                restaurantDTOResponseEntity.getBody().getLocation().getLocationCoordinate().getLatitude(), 0);
        Assert.assertEquals(restaurant.getLocation().getCoordinate().getLongitude(),
                restaurantDTOResponseEntity.getBody().getLocation().getLocationCoordinate().getLongitude(), 0);
        Assert.assertEquals(restaurant.getLocation().getAddress().getName(),
                restaurantDTOResponseEntity.getBody().getLocation().getAddress().getName());
        Assert.assertEquals(restaurant.getLocation().getAddress().getPostalCode(),
                restaurantDTOResponseEntity.getBody().getLocation().getAddress().getPostalCode());
        Assert.assertEquals(restaurant.getLocation().getAddress().getCity(),
                restaurantDTOResponseEntity.getBody().getLocation().getAddress().getCity());
        Assert.assertEquals(restaurant.getLocation().getAddress().getCountry(),
                restaurantDTOResponseEntity.getBody().getLocation().getAddress().getCountry());
        Assert.assertEquals(restaurant.getCapacity(), restaurantDTOResponseEntity.getBody().getCapacity());
        Assert.assertEquals(restaurant.getMenu().getLanguage(),
                restaurantDTOResponseEntity.getBody().getMenu().getLanguage());
        Assert.assertEquals(restaurant.getScheduleRoutine().getScheduleRoutine().size(),
                restaurantDTOResponseEntity.getBody().getSchedule().getScheduleRoutine().size());
        Assert.assertEquals(restaurant.getEmployeeList().size(),
                restaurantDTOResponseEntity.getBody().getEmployeeList().size());

    }

    @Test
    public void requestingARestaurantIdToDelete_checkRestaurantDeleted(){

        //Given
        Menu menu = applicationDataProvider.getMenu();
        //save menu
        menuRepository.save(menu);

        Restaurant restaurant = applicationDataProvider.getRestaurant();
        restaurant.setMenu(menu);

        Address address = applicationDataProvider.getAddress();
        addressRepository.save(address);
        Location location = applicationDataProvider.getLocation();
        locationRepository.save(location);
        location.setAddress(address);
        restaurant.setLocation(location);

        //save restaurant
        restaurantRepository.save(restaurant);

        //When
        ResponseEntity<RestaurantDTO> restaurantDTOResponseEntity =
                restaurantController.deleteRestaurant(restaurant.getUid());

        //Then
        Assert.assertEquals(restaurant.getName(),
                Objects.requireNonNull(restaurantDTOResponseEntity.getBody()).getName());
        Assert.assertEquals(restaurant.getHereId(),
                Objects.requireNonNull(restaurantDTOResponseEntity.getBody()).getHereId());
        Assert.assertEquals(restaurant.getCapacity(), restaurantDTOResponseEntity.getBody().getCapacity());
        Assert.assertEquals(restaurant.getLocation().getCoordinate().getLatitude(),
                restaurantDTOResponseEntity.getBody().getLocation().getLocationCoordinate().getLatitude(), 0);
        Assert.assertEquals(restaurant.getLocation().getCoordinate().getLongitude(),
                restaurantDTOResponseEntity.getBody().getLocation().getLocationCoordinate().getLongitude(), 0);
        Assert.assertEquals(restaurant.getLocation().getAddress().getName(),
                restaurantDTOResponseEntity.getBody().getLocation().getAddress().getName());
        Assert.assertEquals(restaurant.getLocation().getAddress().getPostalCode(),
                restaurantDTOResponseEntity.getBody().getLocation().getAddress().getPostalCode());
        Assert.assertEquals(restaurant.getLocation().getAddress().getCity(),
                restaurantDTOResponseEntity.getBody().getLocation().getAddress().getCity());
        Assert.assertEquals(restaurant.getLocation().getAddress().getCountry(),
                restaurantDTOResponseEntity.getBody().getLocation().getAddress().getCountry());
        Assert.assertEquals(restaurant.getCapacity(), restaurantDTOResponseEntity.getBody().getCapacity());
        Assert.assertEquals(restaurant.getMenu().getLanguage(),
                restaurantDTOResponseEntity.getBody().getMenu().getLanguage());
        Assert.assertEquals(restaurant.getScheduleRoutine().getScheduleRoutine().size(),
                restaurantDTOResponseEntity.getBody().getSchedule().getScheduleRoutine().size());
        Assert.assertEquals(restaurant.getEmployeeList().size(),
                restaurantDTOResponseEntity.getBody().getEmployeeList().size());

    }

    @Test
    public void callingGetAllRestaurants_checkRestaurantsList(){

        //Given
        //Restaurant 1
        Menu menu = applicationDataProvider.getMenu();
        menuRepository.save(menu);
        Restaurant restaurant = applicationDataProvider.getRestaurant();
        restaurant.setMenu(menu);
        Address address = applicationDataProvider.getAddress();
        addressRepository.save(address);
        Location location = applicationDataProvider.getLocation();
        locationRepository.save(location);
        location.setAddress(address);
        restaurant.setLocation(location);

        //Restaurant 2
        Restaurant restaurant2 = applicationDataProvider
                .getRestaurant2();
        Menu menu2 = applicationDataProvider.getMenu2();
        menuRepository.save(menu2);
        restaurant2.setMenu(menu2);
        Address address2 = applicationDataProvider.getAddress2();
        addressRepository.save(address2);
        Location location2 = applicationDataProvider.getLocation2();
        locationRepository.save(location2);
        location2.setAddress(address2);
        restaurant2.setLocation(location2);

        //save restaurant
        restaurantRepository.save(restaurant);

        //save restaurant2
        restaurantRepository.save(restaurant2);

        //When
        ResponseEntity<List<RestaurantDTO>> restaurantDTOResponseEntity =
                restaurantController.getAllRestaurants();

        //Then
        Assert.assertEquals(2, Objects.requireNonNull(restaurantDTOResponseEntity.getBody()).size());

    }

    @Test
    public void requestingRestaurantIdAndEmployeeId_removeEmployeeFromRestaurantsList() {

        //Given
        Restaurant restaurant = applicationDataProvider.getRestaurantToCreate();
        //save restaurant
        Restaurant restaurantSaved = restaurantService.createRestaurant(restaurant);

        //save employee
        Employee employee = applicationDataProvider.getEmployeeToCreate();
        Employee employeeSaved = employeeService.createEmployee(employee);

        restaurantService.addEmployee(restaurant.getUid(), employeeSaved.getUid());

        //When
        ResponseEntity<RestaurantDTO> restaurantDTOResponseEntity =
                restaurantController.removeEmployee(restaurantSaved.getUid(), employeeSaved.getUid());

        //Then
        Assert.assertEquals(0,
                Objects.requireNonNull(restaurantDTOResponseEntity.getBody()).getEmployeeList().size());

    }

    @Test
    public void requestingRestaurantIdAndEmployeeId_addEmployeeToRestaurantsList() {

        //Given
        Restaurant restaurant = applicationDataProvider.getRestaurantToCreate();
        //save restaurant
        Restaurant restaurantSaved = restaurantService.createRestaurant(restaurant);

        Employee employee = applicationDataProvider.getEmployeeToCreate();
        Employee employeeSaved = employeeService.createEmployee(employee);


        //When
        ResponseEntity<RestaurantDTO> restaurantDTOResponseEntity =
                restaurantController.addEmployee(restaurantSaved.getUid(), employeeSaved.getUid());

        //Then
        Assert.assertEquals(1,
                Objects.requireNonNull(restaurantDTOResponseEntity.getBody()).getEmployeeList().size());

    }

}
