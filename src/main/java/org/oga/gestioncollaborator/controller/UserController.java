package org.oga.gestioncollaborator.controller;

import io.smallrye.common.constraint.NotNull;
import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.Keycloak;
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
    public String createUser(@RequestBody UserDTO userDTO) throws Exception {
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
    @GetMapping("user/{username}")
    public UserDTO getUserByUsername(@PathVariable("username") String username){
        return  use.getUserByUserName(username);
    }
    @PostMapping("/login")



    public ResponseEntity<AccessTokenResponse> login(@NotNull @RequestBody UserDTO userDTO) {
        Keycloak keycloak = config.newKeycloakBuilderWithPasswordCredentials(userDTO.getUsername(),userDTO.getPassword()).build();

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

}
