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
import java.io.IOException;
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
    public String createUser(@RequestBody UserDTO userDTO) throws Exception, IOException {
        return use.addUser(userDTO);
    }

    @GetMapping("/AllUsers")
    public List<UserDTO> getAllUser(){
        return use.getAll();
    }

    @GetMapping("/{userId}")
    public UserDTO getUserById(@PathVariable("userId") String userId){
        return  use.getUserById(userId);
    }
    @PostMapping("/login")

    public ResponseEntity<AccessTokenResponse> login(@RequestBody UserDTO userDTO) {
        Keycloak keycloak = config.newKeycloakBuilderWithPasswordCredentials(userDTO).build();
        AccessTokenResponse accessTokenResponse = null;
        try {
            accessTokenResponse = keycloak.tokenManager().getAccessToken();
            return ResponseEntity.status(HttpStatus.OK).body(accessTokenResponse);
        } catch (BadRequestException ex) {
            LOG.warn("invalid account. User probably hasn't verified email.", ex);
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(accessTokenResponse);
        }

    }


    @PutMapping("/update")
    public ResponseEntity<Void> updateUser( @RequestBody UserDTO userDTO) {
        use.updateUser( userDTO);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable String userId) {
        try {
            use.deleteUser(userId);
            return ResponseEntity.ok("User deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting user");
        }
    }
     /*@GetMapping("/AllUsers/{orgId}")
    public ResponseEntity <List<UserRepresentation>> getUsers(@PathVariable("orgId") String orgId) {
        return new ResponseEntity<>(use.getUsers(orgId),HttpStatus.OK);
    }*/
 /* @GetMapping("/{orgId}/{userId}")
     public ResponseEntity<UserResource> confirmUserOrganization(@PathVariable ("orgId")String orgId ,@PathVariable ("userId") String userId ) {
         return  new ResponseEntity<>(use.confirmUserOrganization(orgId,userId),HttpStatus.OK);
     }*/
  /*  @GetMapping("/{userId}")
    public org.keycloak.representations.idm.UserRepresentation getUserById(@PathVariable("userId") String userId) {
        return use.getUserById(userId);
    }*/
  /* @PostMapping("/{userId}/qrcode")
    public ResponseEntity<String> generateAndSaveQRCode(@PathVariable String userId) {
        try {
            use.generateAndSaveQRCode(userId);
            return ResponseEntity.ok("QR code generated for user " + userId);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to generate QR code for user " + userId + ": " + e.getMessage());
        }
    }

*/
}
