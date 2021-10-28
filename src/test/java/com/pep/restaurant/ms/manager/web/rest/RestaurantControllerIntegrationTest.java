package com.pep.restaurant.ms.manager.web.rest;

import com.pep.restaurant.ms.manager.ApplicationDataProvider;
import com.pep.restaurant.ms.manager.RestaurantMsManagerApplication;
import com.pep.restaurant.ms.manager.domain.Employee;
import com.pep.restaurant.ms.manager.domain.Menu;
import com.pep.restaurant.ms.manager.domain.Restaurant;
import com.pep.restaurant.ms.manager.repository.RestaurantRepository;
import com.pep.restaurant.ms.manager.service.mapper.RestaurantMapper;
import com.pep.restaurant.ms.manager.service.model.MenuDTO;
import com.pep.restaurant.ms.manager.repository.EmployeeRepository;
import com.pep.restaurant.ms.manager.repository.MenuRepository;
import com.pep.restaurant.ms.manager.service.model.RestaurantDTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
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
    private RestaurantMapper restaurantMapper;

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

    @WithMockUser(roles = "ADMIN")
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
            .id(menu.getId())
            .language(menu.getLanguage());
        restaurantDTO.setMenu(menuDTOSaved);

        //When
        ResponseEntity<RestaurantDTO> restaurantDTOResponseEntity =
                restaurantController.createRestaurant(restaurantDTO);

        //Then
        Assert.assertEquals(restaurant.getName(),
                Objects.requireNonNull(restaurantDTOResponseEntity.getBody()).getName());
        Assert.assertEquals(restaurant.getLocation(),
                restaurantDTOResponseEntity.getBody().getLocation());
        Assert.assertEquals(restaurant.getCapacity(), restaurantDTOResponseEntity.getBody().getCapacity());
        Assert.assertEquals(restaurant.getMenu().getLanguage(),
                restaurantDTOResponseEntity.getBody().getMenu().getLanguage());
        Assert.assertEquals(restaurant.getEmployeeList().size(),
                restaurantDTOResponseEntity.getBody().getEmployeeList().size());
    }

    @WithMockUser(roles = "ADMIN")
    @Test
    public void requestingARestaurantId_getRestaurantById(){

        //Given
        Menu menu = applicationDataProvider.getMenu();
        //save menu
        menuRepository.save(menu);

        Employee employee = applicationDataProvider.getEmployeeWithoutRestaurantListAndWithoutSchedule();
        employeeRepository.save(employee);

        Restaurant restaurant = applicationDataProvider.getRestaurant();
        restaurant.setMenu(menu);

        restaurant.addEmployee(employee);

        //save restaurant
        restaurantRepository.save(restaurant);

        //When
        ResponseEntity<RestaurantDTO> restaurantDTOResponseEntity =
                restaurantController.getRestaurant(restaurant.getId());

        //Then
        Assert.assertEquals(restaurant.getName(),
                Objects.requireNonNull(restaurantDTOResponseEntity.getBody()).getName());
        Assert.assertEquals(restaurant.getLocation(), restaurantDTOResponseEntity.getBody().getLocation());
        Assert.assertEquals(restaurant.getCapacity(), restaurantDTOResponseEntity.getBody().getCapacity());
        Assert.assertEquals(restaurant.getMenu().getLanguage(),
                restaurantDTOResponseEntity.getBody().getMenu().getLanguage());
        Assert.assertEquals(restaurant.getEmployeeList().size(),
                 restaurantDTOResponseEntity.getBody().getEmployeeList().size());
    }

    @WithMockUser(roles = "ADMIN")
    @Test
    public void requestingARestaurantToEdit_getRestaurantEdited(){
        //Given
        Menu menu = applicationDataProvider.getMenu();
        //save menu
        menuRepository.save(menu);

        Restaurant restaurant = applicationDataProvider.getRestaurant();
        restaurant.setMenu(menu);

        //save restaurant
        restaurantRepository.save(restaurant);

        //restaurant edited
        Restaurant restaurantEdited = applicationDataProvider
                .getRestaurant()
                .location("Bahamas")
                .capacity(1000)
                .name("livenatwitch");

        //When
        ResponseEntity<RestaurantDTO> restaurantDTOResponseEntity =
                restaurantController.editRestaurant(restaurant.getId(),
                        restaurantMapper.mapRestaurantToRestaurantDTO(restaurantEdited));

        //Then
        Assert.assertEquals(restaurantEdited.getName(),
                Objects.requireNonNull(restaurantDTOResponseEntity.getBody()).getName());
        Assert.assertEquals(restaurantEdited.getLocation(), restaurantDTOResponseEntity.getBody().getLocation());
        Assert.assertEquals(restaurantEdited.getCapacity(), restaurantDTOResponseEntity.getBody().getCapacity());
        Assert.assertEquals(restaurant.getMenu().getLanguage(),
                restaurantDTOResponseEntity.getBody().getMenu().getLanguage());
        Assert.assertEquals(restaurant.getEmployeeList().size(),
                restaurantDTOResponseEntity.getBody().getEmployeeList().size());

    }

    @WithMockUser(roles = "ADMIN")
    @Test
    public void requestingARestaurantIdToDelete_checkRestaurantDeleted(){

        //Given
        Menu menu = applicationDataProvider.getMenu();
        //save menu
        menuRepository.save(menu);

        Restaurant restaurant = applicationDataProvider.getRestaurant();
        restaurant.setMenu(menu);

        //save restaurant
        restaurantRepository.save(restaurant);

        //When
        ResponseEntity<RestaurantDTO> restaurantDTOResponseEntity =
                restaurantController.deleteRestaurant(restaurant.getId());

        //Then
        Assert.assertEquals(restaurant.getName(),
                Objects.requireNonNull(restaurantDTOResponseEntity.getBody()).getName());
        Assert.assertEquals(restaurant.getLocation(), restaurantDTOResponseEntity.getBody().getLocation());
        Assert.assertEquals(restaurant.getCapacity(), restaurantDTOResponseEntity.getBody().getCapacity());
        Assert.assertEquals(restaurant.getMenu().getLanguage(),
                restaurantDTOResponseEntity.getBody().getMenu().getLanguage());
        Assert.assertEquals(restaurant.getEmployeeList().size(),
                 restaurantDTOResponseEntity.getBody().getEmployeeList().size());

    }

    @WithMockUser(roles = "ADMIN")
    @Test
    public void callingGetAllRestaurants_checkRestaurantsList(){

        //Given
        Menu menu = applicationDataProvider.getMenu();
        //save menu
        menuRepository.save(menu);

        Restaurant restaurant = applicationDataProvider.getRestaurant();
        restaurant.setMenu(menu);

        Restaurant restaurant2 = applicationDataProvider
                .getRestaurant().name("youtube restaurant");
        restaurant2.setMenu(menu);

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

    @WithMockUser(roles = "ADMIN")
    @Test
    public void requestingRestaurantIdAndEmployeeId_removeEmployeeFromRestaurantsList() {

        //Given
        Menu menu = applicationDataProvider.getMenu();
        //save menu
        menuRepository.save(menu);

        Employee employee = applicationDataProvider.getEmployeeWithoutRestaurantListAndWithoutSchedule();
        employeeRepository.save(employee);

        Restaurant restaurant = applicationDataProvider.getRestaurant();
        restaurant.addEmployee(employee);
        //save restaurant
        restaurantRepository.save(restaurant);

        //When
        ResponseEntity<RestaurantDTO> restaurantDTOResponseEntity =
                restaurantController.removeEmployee(1L, 1L);

        //Then
        Assert.assertEquals(0,
                Objects.requireNonNull(restaurantDTOResponseEntity.getBody()).getEmployeeList().size());

    }

    @WithMockUser(roles = "ADMIN")
    @Test
    public void requestingRestaurantIdAndEmployeeId_addEmployeeToRestaurantsList() {

        //Given
        Menu menu = applicationDataProvider.getMenu();
        //save menu
        menuRepository.save(menu);

        Restaurant restaurant = applicationDataProvider.getRestaurant();
        //save restaurant
        restaurantRepository.save(restaurant);

        Employee employee = applicationDataProvider.getEmployeeWithoutRestaurantListAndWithoutSchedule();
        employeeRepository.save(employee);

        //When
        ResponseEntity<RestaurantDTO> restaurantDTOResponseEntity =
                restaurantController.addEmployee(1L, 1L);

        //Then
        Assert.assertEquals(1,
                Objects.requireNonNull(restaurantDTOResponseEntity.getBody()).getEmployeeList().size());

    }

}
