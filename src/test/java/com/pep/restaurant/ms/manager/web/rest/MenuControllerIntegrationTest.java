package com.pep.restaurant.ms.manager.web.rest;

import com.pep.restaurant.ms.manager.ApplicationDataProvider;
import com.pep.restaurant.ms.manager.RestaurantMsManagerApplication;
import com.pep.restaurant.ms.manager.domain.Menu;
import com.pep.restaurant.ms.manager.repository.MenuRepository;
import com.pep.restaurant.ms.manager.service.mapper.MenuMapper;
import com.pep.restaurant.ms.manager.service.model.MenuDTO;
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
public class MenuControllerIntegrationTest {

    @Autowired
    private MenuController menuController;

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private MenuRepository menuRepository;

    ApplicationDataProvider applicationDataProvider = new ApplicationDataProvider();

    @Before
    public void clearDBRestaurant() {
        menuRepository.deleteAll();
    }

    @Test
    public void requestingAMenuDTO_checkMenuSaved(){

        //Given
        Menu menu = applicationDataProvider.getMenu();

        MenuDTO menuDTO = menuMapper.mapMenuToMenuDTO(menu);

        //When
        ResponseEntity<MenuDTO> menuDTOResponseEntity =
                menuController.createMenu(menuDTO);

        //Then
        Assert.assertEquals(menu.getLanguage(),
                Objects.requireNonNull(menuDTOResponseEntity.getBody()).getLanguage());
    }

    @Test
    public void requestingAMenuId_getMenuById(){

        //Given
        Menu menu = applicationDataProvider.getMenu();

        //save menu
        menuRepository.save(menu);

        //When
        ResponseEntity<MenuDTO> menuDTOResponseEntity =
                menuController.getMenu(menu.getUid());

        //Then
        Assert.assertEquals(menu.getLanguage(),
                Objects.requireNonNull(menuDTOResponseEntity.getBody()).getLanguage());
    }

    @Test
    public void requestingAMenuToEdit_getMenuEdited(){
        //Given
        Menu menu = applicationDataProvider.getMenu();
        //save menu
        menuRepository.save(menu);

        //restaurant edited
        Menu menuEdited = applicationDataProvider
                .getMenu()
                .language("ENGLISH");

        //When
        ResponseEntity<MenuDTO> menuDTOResponseEntity =
                menuController.editMenu(menu.getUid(),
                        menuMapper.mapMenuToMenuDTO(menuEdited));

        //Then
        Assert.assertEquals(menu.getLanguage(),
                Objects.requireNonNull(menuDTOResponseEntity.getBody()).getLanguage());

    }

    @Test
    public void requestingAMenuIdToDelete_checkMenuDeleted(){

        //Given
        Menu menu = applicationDataProvider.getMenu();
        //save menu
        menuRepository.save(menu);

        //When
        ResponseEntity<MenuDTO> menuDTOResponseEntity =
                menuController.deleteMenu(menu.getUid());

        //Then
        Assert.assertEquals(menu.getLanguage(),
                Objects.requireNonNull(menuDTOResponseEntity.getBody()).getLanguage());

    }

    @Test
    public void callingGetAllMenu_checkMenuList(){

        //Given
        Menu menu = applicationDataProvider.getMenu();

        Menu menu2 = applicationDataProvider
                .getMenu()
                .language("ENGLISH");

        //save menu
        menuRepository.save(menu);

        //save menu 2
        menuRepository.save(menu2);

        //When
        ResponseEntity<List<MenuDTO>> menuDTOResponseEntity =
                menuController.getAllMenus();

        //Then
        Assert.assertEquals(2, Objects.requireNonNull(menuDTOResponseEntity.getBody()).size());

    }

}
