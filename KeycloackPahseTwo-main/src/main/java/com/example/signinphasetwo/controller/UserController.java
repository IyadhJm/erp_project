package com.example.signinphasetwo.controller;

import com.example.signinphasetwo.Entity.UserEntity;
import com.example.signinphasetwo.config.KeycklockConfig;
import com.example.signinphasetwo.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;


import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.AccessTokenResponse;
import org.slf4j.Logger;

import org.springframework.web.bind.annotation.*;
import javax.ws.rs.BadRequestException;
import java.util.HashMap;
import java.util.Map;



@CrossOrigin(origins ="*")
@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

@Autowired
 private KeycklockConfig config;
@Autowired
    UserService use ;
    private static final Logger LOG = org.slf4j.LoggerFactory.getLogger(UserController.class);






    @PostMapping("/create")
    public String createOrg(@RequestBody UserEntity userEntity) {
        return use.addUser(userEntity);
    }
    @PostMapping("/login")
    public ResponseEntity<AccessTokenResponse> login(@RequestBody UserEntity userEntity) {
        Keycloak keycloak = config.newKeycloakBuilderWithPasswordCredentials(userEntity).build();
        AccessTokenResponse accessTokenResponse = null;
        try {
            accessTokenResponse = keycloak.tokenManager().getAccessToken();
            return ResponseEntity.status(HttpStatus.OK).body(accessTokenResponse);
        } catch (BadRequestException ex) {
            LOG.warn("invalid account. User probably hasn't verified email.", ex);
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(accessTokenResponse);
        }

    }
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return ResponseEntity.noContent().build();
    }








}
