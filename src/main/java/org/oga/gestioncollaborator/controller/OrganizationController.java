package org.oga.gestioncollaborator.controller;


import io.phasetwo.client.openapi.model.OrganizationRepresentation;
import lombok.RequiredArgsConstructor;
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

    @GetMapping("/orgs")
    public ResponseEntity <List<OrganizationRepresentation>> getOrganization() {
       return new ResponseEntity<>(keycl.getOrg(),HttpStatus.OK);
    }
    @GetMapping("/roles/{orgId}")
    public List<String> getAllRoles(@PathVariable("orgId") String orgId) {
        return keycl.getAllRoles(orgId);
    }

    @PostMapping("/addRole/{roleName}")
    public String addRole(@PathVariable ("roleName") String roleName) {
        return keycl.addRole(roleName);
    }

}
