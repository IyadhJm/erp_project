package org.oga.gestioncollaborator.controller;


import io.phasetwo.client.openapi.model.OrganizationRepresentation;
import io.phasetwo.client.openapi.model.UserRepresentation;
import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.resource.UserResource;
import org.oga.gestioncollaborator.Entity.OrgDTO;
import org.oga.gestioncollaborator.config.KeycklockConfig;
import org.oga.gestioncollaborator.service.KeyCloakService;
import org.oga.gestioncollaborator.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/organizations")
public class OrganizationController {

@Autowired
    private KeyCloakService keycl;
@Autowired
 private KeycklockConfig config;
@Autowired
UserService use ;



    @PostMapping("/create")
    public String createOrg(@RequestBody OrgDTO orgDTO) {
        return keycl.addOrg(orgDTO);
    }



   /* @PostMapping
    public ResponseEntity<String> createOrganization(@RequestBody OrganizationRepresentation organization) {
        return new ResponseEntity<>(HttpStatus.CREATED);
    }*/

    @GetMapping("/orgs")
    public ResponseEntity <List<OrganizationRepresentation>> getOrganization() {
       return new ResponseEntity<>(keycl.getOrg(),HttpStatus.OK);
    }

@PostMapping("/assignRole")
    public String addRoleToUser() {
        return keycl.addRoleToUser("4a83cea7-04eb-42c7-ac5f-b45d075485cf","Manager");
}
    @PostMapping("/addRole/{roleName}")
    public String addRole(@PathVariable ("roleName") String roleName) {
        return keycl.addRole(roleName);
    }
    /*

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateOrganization(@PathVariable("id") String id, @RequestBody OrganizationRepresentation organization) {
        phaseTwo.organizations(keycloak.getRealm()).organization(id).update(organization);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrganization(@PathVariable("id") String id) {
        phaseTwo.organizations(keycloak.getRealm()).organization(id).delete();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<OrganizationRepresentation>> getAllOrganizations() {
        List<OrganizationRepresentation> organizations = phaseTwo.organizations(keycloak.getRealm()).get();
        return new ResponseEntity<>(organizations, HttpStatus.OK);
    }*/
}
