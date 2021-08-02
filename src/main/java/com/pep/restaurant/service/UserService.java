package com.pep.restaurant.service;

import com.pep.restaurant.domain.User;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * User Service.
 */
@Service
public class UserService {

    private final UsersResource usersResource;

    /**
     * Constructor of Users Resource.
     * @param usersResource users Resource.
     */
    public UserService(final UsersResource usersResource) {
        this.usersResource = usersResource;
    }

    /**
     * Get User by user Id.
     * @param userId user Id.
     * @return User object.
     */
    public User getUser(final String userId){

        final UserRepresentation userRepresentation = usersResource.get(userId).toRepresentation();

        return new User()
                .username(userRepresentation.getUsername())
                .firstName(userRepresentation.getFirstName())
                .lastName(userRepresentation.getLastName())
                .email(userRepresentation.getEmail());
    }

    /**
     * Edit user by id.
     * @param userId user id to edit.
     * @param userToEdit new user data.
     * @return User with user info edited.
     */
    public User editUser(final String userId, final User userToEdit){

        final UserRepresentation userRepresentation = usersResource.get(userId).toRepresentation();

        final CredentialRepresentation password = new CredentialRepresentation();
        password.setTemporary(false);
        password.setType(CredentialRepresentation.PASSWORD);
        password.setValue(userToEdit.getPassword());

        userRepresentation.setEnabled(true);
        userRepresentation.setFirstName(userToEdit.getFirstName());
        userRepresentation.setLastName(userToEdit.getLastName());
        userRepresentation.setEmail(userToEdit.getEmail());
        userRepresentation.setCredentials(Collections.singletonList(password));

        usersResource.get(userId).update(userRepresentation);

        return new User()
                .username(userRepresentation.getUsername())
                .firstName(userRepresentation.getFirstName())
                .lastName(userRepresentation.getLastName())
                .email(userRepresentation.getEmail());

    }

    /**
     * Delete user by id.
     * @param userId user id to delete.
     * @return username of the deleted user.
     */
    public String deleteUser(final String userId){
        final UserRepresentation userRepresentation = usersResource.get(userId).toRepresentation();
        usersResource.delete(userId);
        return userRepresentation.getUsername();
    }

}
