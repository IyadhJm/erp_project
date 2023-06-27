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
}
