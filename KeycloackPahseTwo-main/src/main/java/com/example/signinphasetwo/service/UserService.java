package com.example.signinphasetwo.service;
import com.example.signinphasetwo.Entity.UserEntity;
import com.example.signinphasetwo.config.KeycklockConfig;
import io.phasetwo.client.OrganizationsResource;
import io.phasetwo.client.PhaseTwo;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.ws.rs.core.Response;


@Service
public class UserService {
    @Autowired
    KeycklockConfig keycklockConfig;



    public String addUser(UserEntity userEntity) {
        Keycloak keycloak = keycklockConfig.getInstance();
        PhaseTwo phaseTwo = new PhaseTwo(keycloak, keycklockConfig.getServerUrl());
        UsersResource usersResource = keycloak.realm(keycklockConfig.getRealm()).users();
        CredentialRepresentation pass = new CredentialRepresentation();
        pass.setType(CredentialRepresentation.PASSWORD);
        pass.setValue(userEntity.getPassword());
        pass.setTemporary(false);
        UserRepresentation user = new UserRepresentation();
        user.setUsername(userEntity.getUsername());
        user.setEmail(userEntity.getEmail());
        user.setDisableableCredentialTypes(user.getDisableableCredentialTypes());
        user.setEnabled(true);
        Response response = usersResource.create(user);
        String userId = CreatedResponseUtil.getCreatedId(response);
        OrganizationsResource orgsResource = phaseTwo.organizations(keycklockConfig.getRealm());
        String orgId = userEntity.getOrgId();
        orgsResource.organization(orgId).memberships().add(userId);
        return userId;
    }}


