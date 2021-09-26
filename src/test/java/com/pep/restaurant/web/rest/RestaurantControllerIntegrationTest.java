package com.pep.restaurant.web.rest;

import com.pep.restaurant.ApplicationDataProvider;
import com.pep.restaurant.RestaurantApplication;
import com.pep.restaurant.domain.Menu;
import com.pep.restaurant.domain.Restaurant;
import com.pep.restaurant.repository.MenuRepository;
import com.pep.restaurant.repository.RestaurantRepository;
import com.pep.restaurant.service.mapper.RestaurantMapper;
import com.pep.restaurant.service.model.MenuDTO;
import com.pep.restaurant.service.model.RestaurantDTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RestaurantApplication.class)
public class RestaurantControllerIntegrationTest {

  /*  @Autowired
    private RestaurantController restaurantController;

    @Autowired
    private RestaurantMapper restaurantMapper;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private MenuRepository menuRepository;*/

    ApplicationDataProvider applicationDataProvider = new ApplicationDataProvider();

 /*   @Before
    public void clearDBRestaurant(){
        restaurantRepository.deleteAll();
        menuRepository.deleteAll();
    }*/


    @WithMockUser(roles = "ADMIN")
    @Test
    public void requestingARestaurantDTO_checkRestaurantSaved(){

        //Given
        Menu menu = applicationDataProvider.getMenu();
       /*
       //save menu
        menuRepository.save(menu);

        Restaurant restaurant = applicationDataProvider.getRestaurant();
        restaurant.setMenu(menu);

        RestaurantDTO restaurantDTO = restaurantMapper.mapRestaurantToRestaurantDTO(restaurant);

        //Menu DTO created by menu
        MenuDTO menuDTOSaved = new MenuDTO();
        menuDTOSaved.setId(menu.getId());
        menuDTOSaved.setLanguage(menu.getLanguage());
        restaurantDTO.setMenu(menuDTOSaved);

        //When
        ResponseEntity<RestaurantDTO> restaurantDTOResponseEntity = restaurantController.createRestaurant(restaurantDTO);

        //Then
        Assert.assertEquals(restaurant.getName(), restaurantDTOResponseEntity.getBody().getName());
        Assert.assertEquals(restaurant.getLocation(), restaurantDTOResponseEntity.getBody().getLocation());
        Assert.assertEquals(restaurant.getCapacity(), restaurantDTOResponseEntity.getBody().getCapacity());
        Assert.assertEquals(restaurant.getMenu().getLanguage(), restaurantDTOResponseEntity.getBody().getMenu().getLanguage());*/
    }
/*
    @WithMockUser(roles = "ADMIN")
    @Test
    public void requestingARestaurantId_getRestaurantById(){

        //Given
        Menu menu = applicationDataProvider.getMenu();
        //save menu
        menuRepository.save(menu);

        Restaurant restaurant = applicationDataProvider.getRestaurant();
        restaurant.setMenu(menu);

        //save restaurant
        restaurantRepository.save(restaurant);

        //When
        ResponseEntity<RestaurantDTO> restaurantDTOResponseEntity = restaurantController.getRestaurant(restaurant.getId());

        //Then
        Assert.assertEquals(restaurant.getName(), restaurantDTOResponseEntity.getBody().getName());
        Assert.assertEquals(restaurant.getLocation(), restaurantDTOResponseEntity.getBody().getLocation());
        Assert.assertEquals(restaurant.getCapacity(), restaurantDTOResponseEntity.getBody().getCapacity());
        Assert.assertEquals(restaurant.getMenu().getLanguage(), restaurantDTOResponseEntity.getBody().getMenu().getLanguage());
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
        Assert.assertEquals(restaurantEdited.getName(), restaurantDTOResponseEntity.getBody().getName());
        Assert.assertEquals(restaurantEdited.getLocation(), restaurantDTOResponseEntity.getBody().getLocation());
        Assert.assertEquals(restaurantEdited.getCapacity(), restaurantDTOResponseEntity.getBody().getCapacity());
        Assert.assertEquals(restaurant.getMenu().getLanguage(), restaurantDTOResponseEntity.getBody().getMenu().getLanguage());

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
        Assert.assertEquals(restaurant.getName(), restaurantDTOResponseEntity.getBody().getName());
        Assert.assertEquals(restaurant.getLocation(), restaurantDTOResponseEntity.getBody().getLocation());
        Assert.assertEquals(restaurant.getCapacity(), restaurantDTOResponseEntity.getBody().getCapacity());

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
        Assert.assertEquals(2, restaurantDTOResponseEntity.getBody().size());

    }*/
}
