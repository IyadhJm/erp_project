package org.oga.gestioncollaborator.controller;

import io.phasetwo.client.openapi.model.UserRepresentation;
import io.smallrye.common.constraint.NotNull;
import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.AccessTokenResponse;
import org.oga.gestioncollaborator.Entity.UserDTO;
import org.oga.gestioncollaborator.config.KeycklockConfig;
import org.oga.gestioncollaborator.service.UserService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.BadRequestException;
import java.util.List;


@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/users")
public class UserController {

@Autowired
 private KeycklockConfig config;
@Autowired
    private UserService use ;
    private static final Logger LOG = org.slf4j.LoggerFactory.getLogger(UserController.class);

    @PostMapping("/create")
    public String createOrg(@RequestBody UserDTO userDTO) {
        return use.addUser(userDTO);
    }
    /*@GetMapping("/AllUsers/{orgId}")
    public ResponseEntity <List<UserRepresentation>> getUsers(@PathVariable("orgId") String orgId) {
        return new ResponseEntity<>(use.getUsers(orgId),HttpStatus.OK);
    }*/
    @GetMapping("/AllUsers/{orgId}")
    public List<UserDTO> getAllUser(){
        return use.getAll();
    }
    @GetMapping("/{orgId}/{userId}")
    public ResponseEntity<UserResource> confirmUserOrganization(@PathVariable ("orgId")String orgId ,@PathVariable ("userId") String userId ) {
        return  new ResponseEntity<>(use.confirmUserOrganization(orgId,userId),HttpStatus.OK);
    }
   /* @GetMapping("/{userId}")
    public org.keycloak.representations.idm.UserRepresentation getUserById(@PathVariable("userId") String userId) {
        return use.getUserById(userId);
    }*/
   @GetMapping("/{userId}")
   public UserDTO getUserById(@PathVariable("userId") String userId){
       return  use.getUserById(userId);
   }
    @PostMapping("/login")

    public ResponseEntity<AccessTokenResponse> login(@NotNull @RequestBody UserDTO userDTO) {
        Keycloak keycloak = config.newKeycloakBuilderWithPasswordCredentials(userDTO.getUsername(), userDTO.getPassword()).build();

        AccessTokenResponse accessTokenResponse = null;
        try {
            accessTokenResponse = keycloak.tokenManager().getAccessToken();
            return ResponseEntity.status(HttpStatus.OK).body(accessTokenResponse);
        } catch (BadRequestException ex) {
            LOG.warn("invalid account. User probably hasn't verified email.", ex);
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(accessTokenResponse);
        }

    }




}
