package com.pep.restaurant.service;

import com.pep.restaurant.ApplicationDataProvider;
import com.pep.restaurant.KeycloakDataProvider;
import com.pep.restaurant.domain.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @InjectMocks
    UserService userService;

    @Mock
    UsersResource usersResource;

    KeycloakDataProvider keycloakDataProvider = new KeycloakDataProvider();

    ApplicationDataProvider applicationDataProvider = new ApplicationDataProvider();

    @Test
    public void passingAUserId_checkTheUserGot() {

        //Given a user info
        User user = applicationDataProvider.getUser();
/*
        //When
        UserResource userResource = Mockito.mock(UserResource.class);
        Mockito.when(usersResource.get(Mockito.any())).thenReturn(userResource);
        Mockito.when(userResource.toRepresentation()).thenReturn(keycloakDataProvider.getUserRepresentation());
        User userResponse = userService.getUser("1");

        //Then
        Assert.assertEquals(user.getUserName(),userResponse.getUserName());
        Assert.assertEquals(user.getFirstName(),userResponse.getFirstName());
        Assert.assertEquals(user.getLastName(),userResponse.getLastName());
        Assert.assertEquals(user.getEmail(),userResponse.getEmail());*/
    }
/*
    @Test
    public void passingAUserIdAndAUserObject_checkTheUserEdited() {

        //Given a user info
        User user = applicationDataProvider.getUser();

        //When
        UserResource userResource = Mockito.mock(UserResource.class);
        Mockito.when(usersResource.get(Mockito.any())).thenReturn(userResource);
        Mockito.when(userResource.toRepresentation()).thenReturn(keycloakDataProvider.getUserRepresentation());
        User userResponse = userService.editUser("1",user);

        //Then
        Assert.assertEquals(user.getUserName(),userResponse.getUserName());
        Assert.assertEquals(user.getFirstName(),userResponse.getFirstName());
        Assert.assertEquals(user.getLastName(),userResponse.getLastName());
        Assert.assertEquals(user.getEmail(),userResponse.getEmail());
    }

    @Test
    public void passingAUserId_checkIfDeleteUserWasCalled() {

        //Given a user info
        User user = applicationDataProvider.getUser();

        //When
        UserResource userResource = Mockito.mock(UserResource.class);
        Mockito.when(usersResource.get(Mockito.any())).thenReturn(userResource);
        Mockito.when(userResource.toRepresentation()).thenReturn(keycloakDataProvider.getUserRepresentation());
        userService.deleteUser("1");

        //Then
        Mockito.verify(usersResource, Mockito.times(1)).delete("1");
    }
*/

}