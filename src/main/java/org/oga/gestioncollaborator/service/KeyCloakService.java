package org.oga.gestioncollaborator.service;

import io.phasetwo.client.OrganizationResource;
import io.phasetwo.client.OrganizationRolesResource;
import io.phasetwo.client.OrganizationsResource;
import io.phasetwo.client.PhaseTwo;
import io.phasetwo.client.openapi.model.OrganizationRepresentation;
import io.phasetwo.client.openapi.model.OrganizationRoleRepresentation;
import io.phasetwo.client.openapi.model.UserRepresentation;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RolesResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.oga.gestioncollaborator.Entity.OrgDTO;
import org.oga.gestioncollaborator.config.KeycklockConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;


@Service
public class KeyCloakService  {
@Autowired
KeycklockConfig keycklockConfig;
    public String addOrg(OrgDTO orgDTO) {
        Keycloak keycloak = keycklockConfig.getInstance();
        PhaseTwo phaseTwo = new PhaseTwo(keycloak, keycklockConfig.getSERVER_URL());

        OrganizationRepresentation organizationRepresentation = new OrganizationRepresentation().name(orgDTO.getName());

        //organizationRepresentation.setName(orgDTO.getName());

        OrganizationsResource orgs = phaseTwo.organizations(keycklockConfig.getREALM());

        String orgId = orgs.create(organizationRepresentation);

        return orgId;
    }




    public List<OrganizationRepresentation >getOrg() {
        Keycloak keycloak = keycklockConfig.getInstance();
        PhaseTwo phaseTwo = new PhaseTwo(keycloak, keycklockConfig.getSERVER_URL());

        List<OrganizationRepresentation> organizations = phaseTwo.organizations(keycklockConfig.REALM).get();

        return  organizations;
    }

    public String addRoleToUser(String userId, String roleName) {
        Keycloak keycloak = keycklockConfig.getInstance();
        PhaseTwo phaseTwo = new PhaseTwo(keycloak, keycklockConfig.getSERVER_URL());
        String organizationId = "3d8dcac9-bd84-4845-b90c-482341b6dffd";

        OrganizationsResource orgsResource = phaseTwo.organizations(keycklockConfig.getREALM());

        // Attribuer le rôle à l'utilisateur
        OrganizationResource orgResource = orgsResource.organization(organizationId);

        OrganizationRolesResource rolesResource = orgResource.roles();

      //  String name = rolesResource.create(new OrganizationRoleRepresentation().name(roleName));
        rolesResource.grant(roleName,userId);

return roleName;

    }
    public String addRole(String roleName){
        Keycloak keycloak = keycklockConfig.getInstance();
        PhaseTwo phaseTwo = new PhaseTwo(keycloak, keycklockConfig.getSERVER_URL());
        String organizationId = "e3f64273-a3ed-4251-a080-fb9343367353";

        OrganizationsResource orgsResource = phaseTwo.organizations(keycklockConfig.getREALM());

        // Attribuer le rôle à l'utilisateur
        OrganizationResource orgResource = orgsResource.organization(organizationId);

        OrganizationRolesResource rolesResource = orgResource.roles();

          String name = rolesResource.create(new OrganizationRoleRepresentation().name(roleName));
        return name;
    }
/*
    public List<String> getAllOrgRoles(String orgId) {
        Keycloak keycloak = keycklockConfig.getInstance();
        PhaseTwo phaseTwo = new PhaseTwo(keycloak, keycklockConfig.getSERVER_URL());

        // Récupérer la ressource de l'organisation
        OrganizationsResource orgsResource = phaseTwo.organizations(keycklockConfig.getREALM());
        OrganizationResource orgResource = orgsResource.organization(orgId);

        // Récupérer la ressource des rôles de l'organisation
        OrganizationRolesResource rolesResource = orgResource.roles();

        // Récupérer tous les rôles de l'organisation
        List<OrganizationRoleRepresentation> roles = rolesResource.list();

        // Créer une liste de noms de rôles
        List<String> roleNames = new ArrayList<>();
        for (OrganizationRoleRepresentation role : roles) {
            roleNames.add(role.getName());
        }

        return roleNames;
    }
    */
/*
    public String AddRole(String roleName) {
        Keycloak keycloak = keycklockConfig.getInstance();
        PhaseTwo phaseTwo = new PhaseTwo(keycloak, keycklockConfig.getSERVER_URL());
        String realm = keycklockConfig.getREALM();
        String organizationId = "3d8dcac9-bd84-4845-b90c-482341b6dffd";


        OrganizationRoleRepresentation role = new OrganizationRoleRepresentation().name(roleName);
        Object createdRoleObj = phaseTwo.getOrganizationRolesApi().createOrganizationRole(realm, organizationId, role);
        OrganizationRoleRepresentation createdRole = (OrganizationRoleRepresentation) createdRoleObj;

    }*/


}
