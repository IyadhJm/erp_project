package org.oga.gestioncollaborator.config;

import lombok.Data;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data

public class KeycklockConfig {
    static Keycloak keycloak = null;


    @Value("${keycloak.server-url}")
    public  String serverUrl;

    @Value("${keycloak.realm}")
    public  String realm;

    @Value("${keycloak.ressource}")
    public  String clientId;

    @Value("${keycloak.username}")
    public  String username;
    @Value("${keycloak.password}")
    public  String password;
    @Value("${keycloak.secret}")
    public  String secret;

    public  Keycloak getInstance() {
        
        return KeycloakBuilder.builder()
                .serverUrl(serverUrl)
                .realm(realm)
                .grantType(OAuth2Constants.PASSWORD)
                .username(username)
                .password(password)
                .clientSecret(secret)
                .clientId(clientId)
                .build();
    }
    public KeycloakBuilder newKeycloakBuilderWithPasswordCredentials(String userName , String password) {
        return KeycloakBuilder.builder()
                .realm(realm)
                .serverUrl(serverUrl)
                .clientId(clientId)
                .clientSecret(secret)
                .username(userName)
                .password(password);
    }
}

