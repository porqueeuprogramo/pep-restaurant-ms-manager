package com.pep.restaurant;

import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;

import java.util.Collections;

public class KeycloakDataProvider {

    public CredentialRepresentation getCredentialRepresentation(){
        final CredentialRepresentation password = new CredentialRepresentation();
        password.setTemporary(false);
        password.setType(CredentialRepresentation.PASSWORD);
        password.setValue("1234");

        return password;
    }

    public UserRepresentation getUserRepresentation(){
        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setEnabled(true);
        userRepresentation.setUsername("joaobarbosa");
        userRepresentation.setFirstName("Joao");
        userRepresentation.setLastName("Barbosa");
        userRepresentation.setEmail("joaobarbosa@porqueeuprogramo.com");
        userRepresentation.setCredentials(Collections.singletonList(getCredentialRepresentation()));

        return userRepresentation;
    }

}
