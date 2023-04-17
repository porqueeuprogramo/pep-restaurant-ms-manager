package com.pep.restaurant.ms.manager.service;

import com.pep.restaurant.ms.manager.ApplicationDataProvider;
import com.pep.restaurant.ms.manager.domain.Menu;
import com.pep.restaurant.ms.manager.repository.MenuRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class MenuServiceTest {

    @InjectMocks
    MenuService menuService;

    @Mock
    MenuRepository menuRepository;

    ApplicationDataProvider applicationDataProvider = new ApplicationDataProvider();


    @Test
    public void passingAMenuThatAlreadyExists_throwAnException() {
        //Given
        Menu menuGiven = applicationDataProvider.getMenu();

        //When
        Mockito.when(menuRepository.findByUid(menuGiven.getUid())).thenReturn(Optional.of(menuGiven));
        Assert.assertThrows(NullPointerException.class, () -> menuService.createMenu(menuGiven));

    }

    @Test
    public void passingAMenuThatNotExists_ReturnMenuSaved() {
        //Given
        Menu menuGiven = applicationDataProvider.getMenu();

        //When
        Mockito.when(menuRepository.save(menuGiven)).thenReturn(menuGiven);
        Menu menuResult = menuService.createMenu(menuGiven);

        //Then
        Assert.assertEquals(menuGiven, menuResult);

    }

    @Test
    public void receivingAMenuAndAnId_returnEditedMenu() {
        //Given
        Menu menuToEdit = applicationDataProvider.getMenu();

        Menu menuEdited = applicationDataProvider.getMenu()
                .language("ENGLISH");

        //When
        Mockito.when(menuRepository.findByUid(Mockito.any())).thenReturn(Optional.of(menuToEdit));
        Mockito.when(menuRepository.save(Mockito.any())).thenReturn(menuEdited);
        Menu menuResult = menuService.editMenu("uid",menuToEdit);

        //Then
        Assert.assertEquals(menuEdited,menuResult);

    }

    @Test
    public void receivingAMenuAndAnId_thrownAnException() {
        //Given
        Menu menuToEdit = applicationDataProvider.getMenu();

        //When
        Mockito.when(menuRepository.findByUid(Mockito.any())).thenReturn(Optional.empty());
        Assert.assertThrows(NullPointerException.class, () -> menuService.editMenu("uid",menuToEdit));

    }

    @Test
    public void receivingAMenuAndAnId_deleteMenu() {
        //Given
        Menu menuToDelete = applicationDataProvider.getMenu();

        //When
        Mockito.when(menuRepository.findByUid(Mockito.anyString())).thenReturn(Optional.of(menuToDelete));
        Mockito.doNothing().when(menuRepository).deleteByUid(Mockito.anyString());
        menuService.deleteMenu("uid");

        //Then
        Mockito.verify(menuRepository, Mockito.times(1)).findByUid(Mockito.anyString());
        Mockito.verify(menuRepository,  Mockito.times(1)).deleteByUid(Mockito.anyString());

    }

    @Test
    public void deleteMenuById_thrownAnException() {
        //Given
        //When
        Mockito.when(menuRepository.findByUid(Mockito.any())).thenReturn(Optional.empty());
        Assert.assertThrows(NullPointerException.class, () -> menuService.deleteMenu("uid"));
    }


    @Test
    public void passingAMenuId_thrownAnException() {
        //Given
        //When
        Mockito.when(menuRepository.findByUid(Mockito.any())).thenReturn(Optional.empty());
        Assert.assertThrows(NullPointerException.class, () -> menuService.getMenu("uid"));
    }

    @Test
    public void passingAMenuId_getMenu() {
        //Given
        Menu menuToGet = applicationDataProvider.getMenu();

        //When
        Mockito.when(menuRepository.findByUid(Mockito.anyString())).thenReturn(Optional.of(menuToGet));
        Menu menuResult = menuService.getMenu("uid");

        //Then
        Assert.assertEquals(menuToGet,menuResult);
    }

    @Test
    public void getAllMenus_CheckIfMenuListHasTheResultExpected() {
        //Given
        Menu menuToGet = applicationDataProvider.getMenu();

        //When
        Mockito.when(menuRepository.findAll()).thenReturn(Collections.singletonList(menuToGet));
        List<Menu> menuListResult = menuService.getAllMenus();

        //Then
        Assert.assertEquals(menuToGet.getId(),menuListResult.get(0).getId());
        Assert.assertEquals(menuToGet.getLanguage(),menuListResult.get(0).getLanguage());

    }

    @Test
    public void getAllMenus_throwAnException() {

        //When
        Mockito.when(menuRepository.findAll()).thenReturn(new ArrayList<>());

        //Then
        Assert.assertThrows(NullPointerException.class, () -> menuService.getAllMenus());

    }


}
