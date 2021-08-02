package com.pep.restaurant.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "application")
public class ApplicationProperties {

    private final Keycloak keycloak = new Keycloak();

    public Keycloak getKeycloak(){
        return keycloak;
    }

    public static class Keycloak{

        private String serverUrl;

        private String realm;

        private String username;

        private String password;

        private String clientId;

        private String clientSecret;

        /**
         * Get keycloak Server Url.
         * @return keycloak server url.
         */
        public String getServerUrl() {
            return serverUrl;
        }

        /**
         * Set keycloak Server Url.
         * @param serverUrl Server url.
         */
        public void setServerUrl(final String serverUrl) {
            this.serverUrl = serverUrl;
        }

        /**
         * Get Keycloak realm.
         * @return realm.
         */
        public String getRealm() {
            return realm;
        }

        /**
         * Set keycloak realm.
         * @param realm realm.
         */
        public void setRealm(final String realm) {
            this.realm = realm;
        }

        /**
         * Get Keycloak Username username.
         * @return username.
         */
        public String getUsername() {
            return username;
        }

        /**
         * Set keycloak username.
         * @param username username.
         */
        public void setUsername(final String username) {
            this.username = username;
        }

        /**
         * Get keycloak password.
         * @return password.
         */
        public String getPassword() {
            return password;
        }

        /**
         * Set keycloak password.
         * @param password password.
         */
        public void setPassword(final String password) {
            this.password = password;
        }

        /**
         * Get keycloak client id.
         * @return clientId.
         */
        public String getClientId() {
            return clientId;
        }

        /**
         * Set keycloak client id.
         * @param clientId client id.
         */
        public void setClientId(final String clientId) {
            this.clientId = clientId;
        }

        /**
         * Get keycloak client secret.
         * @return client Secret.
         */
        public String getClientSecret() {
            return clientSecret;
        }

        /**
         * Set keycloak client secret.
         * @param clientSecret client secret.
         */
        public void setClientSecret(final String clientSecret) {
            this.clientSecret = clientSecret;
        }
    }


}
