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
        Menu menuResult = menuService.editMenu(menuToEdit.getId(),menuToEdit);

        //Then
        Assert.assertEquals(menuEdited,menuResult);

    }

    @Test
    public void receivingAMenuAndAnId_thrownAnException() {
        //Given
        Menu menuToEdit = applicationDataProvider.getMenu();

        //When
        Mockito.when(menuRepository.findById(Mockito.any())).thenReturn(Optional.empty());
        Assert.assertThrows(NullPointerException.class, () -> menuService.editMenu(menuToEdit.getId(),menuToEdit));

    }

}
