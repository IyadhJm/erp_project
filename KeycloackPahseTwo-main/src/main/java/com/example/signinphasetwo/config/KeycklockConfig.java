package com.example.signinPhasetwo.config;

import com.example.signinPhasetwo.Entity.UserEntity;
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
    public  String SERVER_URL;

    @Value("${keycloak.realm}")
    public  String REALM;

    @Value("${keycloak.ressource}")
    public  String CLIENT_ID;

    @Value("${keycloak.username}")
    public  String USERNAME;
    @Value("${keycloak.password}")
    public  String PASSWORD;
    @Value("${keycloak.secret}")
    public  String SECRET;

    public  Keycloak getInstance() {
        
        return KeycloakBuilder.builder()
                .serverUrl(SERVER_URL)
                .realm(REALM)
                .grantType(OAuth2Constants.PASSWORD)
                .username(USERNAME)
                .password(PASSWORD)
                .clientSecret(SECRET)
                .clientId(CLIENT_ID)





                .build();
    }

    public KeycloakBuilder newKeycloakBuilderWithPasswordCredentials(UserEntity userEntity) {
        return KeycloakBuilder.builder()
                .realm(REALM)
                .serverUrl(SERVER_URL)
                .clientId(CLIENT_ID)
                .clientSecret(SECRET)
                .username(userEntity.getUsername())
                .password(userEntity.getPassword());
    }


}
