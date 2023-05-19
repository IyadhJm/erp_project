package com.example.signinphasetwo.controller;


import com.example.signinphasetwo.Entity.OrgEntity;
import com.example.signinphasetwo.config.KeycklockConfig;
import com.example.signinphasetwo.service.KeyCloakService;
import com.example.signinphasetwo.service.UserService;
import io.phasetwo.client.openapi.model.OrganizationRepresentation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/organizations")
public class OrganizationController {

@Autowired
    private KeyCloakService keycl;
@Autowired
 private KeycklockConfig config;
@Autowired
UserService use ;



    @PostMapping("/create")
    public String createOrg(@RequestBody OrgEntity orgEntity) {
        return keycl.addOrg(orgEntity);
    }

    @GetMapping("/orgs")
    public ResponseEntity <List<OrganizationRepresentation>> getOrganization() {
       return new ResponseEntity<>(keycl.gettOrg(),HttpStatus.OK);
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
