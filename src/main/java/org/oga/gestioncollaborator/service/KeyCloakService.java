package org.oga.gestioncollaborator.service;

import io.phasetwo.client.OrganizationResource;
import io.phasetwo.client.OrganizationRolesResource;
import io.phasetwo.client.OrganizationsResource;
import io.phasetwo.client.PhaseTwo;
import io.phasetwo.client.openapi.model.OrganizationRepresentation;
import io.phasetwo.client.openapi.model.OrganizationRoleRepresentation;

import org.keycloak.admin.client.Keycloak;
import org.oga.gestioncollaborator.Entity.OrgDTO;
import org.oga.gestioncollaborator.config.KeycklockConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;


@Service
public class KeyCloakService  {
    @Autowired
    KeycklockConfig keycklockConfig;
    public String addOrg(OrgDTO orgDTO) {
        Keycloak keycloak = keycklockConfig.getInstance();
        PhaseTwo phaseTwo = new PhaseTwo(keycloak, keycklockConfig.getServerUrl());

        OrganizationRepresentation organizationRepresentation = new OrganizationRepresentation().name(orgDTO.getName());
        OrganizationsResource orgs = phaseTwo.organizations(keycklockConfig.getRealm());



        return  orgs.create(organizationRepresentation);
    }




    public List<OrganizationRepresentation >getOrg() {
        Keycloak keycloak = keycklockConfig.getInstance();
        PhaseTwo phaseTwo = new PhaseTwo(keycloak, keycklockConfig.getServerUrl());
        return   phaseTwo.organizations(keycklockConfig.realm).get();
    }

    public String addRoleToUser(String userId, String roleName) {
        Keycloak keycloak = keycklockConfig.getInstance();
        PhaseTwo phaseTwo = new PhaseTwo(keycloak, keycklockConfig.getServerUrl());
        String organizationId = "35785716-6082-4f21-864c-c9eeb9e1c160";

        OrganizationsResource orgsResource = phaseTwo.organizations(keycklockConfig.getRealm());

        // Attribuer le rôle à l'utilisateur
        OrganizationResource orgResource = orgsResource.organization(organizationId);

        OrganizationRolesResource rolesResource = orgResource.roles();


        rolesResource.grant(roleName,userId);

        return roleName;

    }
    public String addRole(String roleName){
        Keycloak keycloak = keycklockConfig.getInstance();
        PhaseTwo phaseTwo = new PhaseTwo(keycloak, keycklockConfig.getServerUrl());
        String organizationId = "35785716-6082-4f21-864c-c9eeb9e1c160";

        OrganizationsResource orgsResource = phaseTwo.organizations(keycklockConfig.getRealm());

        // Attribuer le rôle à l'utilisateur
        OrganizationResource orgResource = orgsResource.organization(organizationId);

        OrganizationRolesResource rolesResource = orgResource.roles();


        return   rolesResource.create(new OrganizationRoleRepresentation().name(roleName));
    }
    public List<String> getAllRoles(String orgId) {
        Keycloak keycloak = keycklockConfig.getInstance();
        PhaseTwo phaseTwo = new PhaseTwo(keycloak, keycklockConfig.getServerUrl());
        OrganizationsResource orgsResource = phaseTwo.organizations(keycklockConfig.getRealm());
        OrganizationResource orgResource = orgsResource.organization(orgId);
        OrganizationRolesResource rolesResource = orgResource.roles();
        List<String> roles = new ArrayList<>();
        List<OrganizationRoleRepresentation> roleList = rolesResource.get();
        for (OrganizationRoleRepresentation role : roleList) {
            roles.add(role.getName());
        }
        return roles;
    }
}
