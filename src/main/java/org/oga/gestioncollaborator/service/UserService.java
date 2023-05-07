package org.oga.gestioncollaborator.service;


import io.phasetwo.client.OrganizationResource;
import io.phasetwo.client.OrganizationRolesResource;
import io.phasetwo.client.OrganizationsResource;
import io.phasetwo.client.PhaseTwo;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.oga.gestioncollaborator.Entity.UserDTO;
import org.oga.gestioncollaborator.config.KeycklockConfig;
import org.oga.gestioncollaborator.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.NoSuchElementException;


@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    KeycklockConfig keycklockConfig;


    public String addUser(UserDTO userDTO) {
        Keycloak keycloak = keycklockConfig.getInstance();
        PhaseTwo phaseTwo = new PhaseTwo(keycloak, keycklockConfig.getSERVER_URL());
        UsersResource usersResource = keycloak.realm(keycklockConfig.getREALM()).users();
        CredentialRepresentation pass = new CredentialRepresentation();
        pass.setType(CredentialRepresentation.PASSWORD);
        pass.setValue(userDTO.getPassword());
        pass.setTemporary(false);
        UserRepresentation user = new UserRepresentation();
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEnabled(true);
        Response response = usersResource.create(user);
        String userId = CreatedResponseUtil.getCreatedId(response);
        OrganizationsResource orgsResource = phaseTwo.organizations(keycklockConfig.getREALM());
        String orgId = userDTO.getOrgId();
        orgsResource.organization(orgId).memberships().add(userId);
        OrganizationResource orgResource = orgsResource.organization(orgId);
        OrganizationRolesResource rolesResource = orgResource.roles();

        rolesResource.grant(userDTO.getRole(), userId);
        userRepository.save(new UserDTO(userId, userDTO.getOrgId(), keycklockConfig.getREALM(), userDTO.getUsername(),
                userDTO.getPassword(), userDTO.getEmail(), userDTO.getFirstName(),
                userDTO.getLastName(), userDTO.getRole(), userDTO.getTypeContrat(), userDTO.getSalaire(), userDTO.getDateEmbauche()));

        return userId;
    }
    /*
    public List<io.phasetwo.client.openapi.model.UserRepresentation> getUsers(String orgId){
        Keycloak keycloak = keycklockConfig.getInstance();
        PhaseTwo phaseTwo = new PhaseTwo(keycloak, keycklockConfig.getSERVER_URL());
        List<io.phasetwo.client.openapi.model.UserRepresentation> userRepresentations = phaseTwo.getOrganizationMembershipsApi().getOrganizationMemberships(keycklockConfig.REALM, orgId,1,10000).stream().toList();
        return userRepresentations;
    }*/
    public List<UserDTO> getAll() {
        return userRepository.findAll();
    }

    /*
    public UserRepresentation getUserById(String userId) {
        Keycloak keycloak = keycklockConfig.getInstance();
        UsersResource usersResource = keycloak.realm(keycklockConfig.getREALM()).users();
        UserResource userResource = usersResource.get(userId);
        UserRepresentation user = userResource.toRepresentation();
        return user;
    }*/
    public UserDTO getUserById(String userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User not found"));
    }

    public UserResource confirmUserOrganization(String orgId , String userId){
        Keycloak keycloak = keycklockConfig.getInstance();
        PhaseTwo phaseTwo = new PhaseTwo(keycloak, keycklockConfig.getSERVER_URL());
        Response response = phaseTwo.getOrganizationMembershipsApi().checkOrganizationMembership(keycklockConfig.REALM, orgId, userId);
        UserResource userResource = response.readEntity(UserResource.class);
        return userResource;
    }
}


