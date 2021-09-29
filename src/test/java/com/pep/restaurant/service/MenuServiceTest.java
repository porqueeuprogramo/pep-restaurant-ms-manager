package com.pep.restaurant.service;

import com.pep.restaurant.ApplicationDataProvider;
import com.pep.restaurant.domain.Menu;
import com.pep.restaurant.repository.MenuRepository;
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
        Mockito.when(menuRepository.findByLanguage(menuGiven.getLanguage())).thenReturn(Optional.of(menuGiven));
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
        Mockito.when(menuRepository.findById(Mockito.any())).thenReturn(Optional.of(menuToEdit));
        Mockito.when(menuRepository.save(Mockito.any())).thenReturn(menuEdited);
        Menu menuResult = menuService.editMenu(1L,menuToEdit);

        //Then
        Assert.assertEquals(menuEdited,menuResult);

    }

    @Test
    public void receivingAMenuAndAnId_thrownAnException() {
        //Given
        Menu menuToEdit = applicationDataProvider.getMenu();

        //When
        Mockito.when(menuRepository.findById(Mockito.any())).thenReturn(Optional.empty());
        Assert.assertThrows(NullPointerException.class, () -> menuService.editMenu(1L,menuToEdit));

    }

    @Test
    public void receivingAMenuAndAnId_deleteMenu() {
        //Given
        Menu menuToDelete = applicationDataProvider.getMenu();

        //When
        Mockito.when(menuRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(menuToDelete));
        Mockito.doNothing().when(menuRepository).deleteById(Mockito.anyLong());
        menuService.deleteMenu(1L);

        //Then
        Mockito.verify(menuRepository, Mockito.times(1)).findById(Mockito.anyLong());
        Mockito.verify(menuRepository,  Mockito.times(1)).deleteById(Mockito.anyLong());

    }

    @Test
    public void deleteMenuById_thrownAnException() {
        //Given
        //When
        Mockito.when(menuRepository.findById(Mockito.any())).thenReturn(Optional.empty());
        Assert.assertThrows(NullPointerException.class, () -> menuService.deleteMenu(1L));
    }


    @Test
    public void passingAMenuId_thrownAnException() {
        //Given
        //When
        Mockito.when(menuRepository.findById(Mockito.any())).thenReturn(Optional.empty());
        Assert.assertThrows(NullPointerException.class, () -> menuService.getMenu(1L));
    }

    @Test
    public void passingAMenuId_getMenu() {
        //Given
        Menu menuToGet = applicationDataProvider.getMenu();

        //When
        Mockito.when(menuRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(menuToGet));
        Menu menuResult = menuService.getMenu(1L);

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