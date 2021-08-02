package com.pep.restaurant.config;

import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.UsersResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KeycloakConfig {

    @Autowired
    private ApplicationProperties applicationProperties;

    /**
     * Keycloak builder
     * @return keycloak.
     */
    public Keycloak keycloakClientBuilder(){
        return KeycloakBuilder.builder()
            .serverUrl(applicationProperties.getKeycloak().getServerUrl())
            .realm(applicationProperties.getKeycloak().getRealm())
            .username(applicationProperties.getKeycloak().getUsername())
            .password(applicationProperties.getKeycloak().getPassword())
            .clientId(applicationProperties.getKeycloak().getClientId())
            .clientSecret(applicationProperties.getKeycloak().getClientSecret())
            .grantType(OAuth2Constants.PASSWORD)
            .build();
    }

    @Bean
    public UsersResource getUsersResource() {
        return keycloakClientBuilder()
                .realm(applicationProperties
                        .getKeycloak()
                        .getRealm()).users();
    }

}
