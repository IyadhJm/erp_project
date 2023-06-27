package org.oga.gestioncollaborator.service;


import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import io.phasetwo.client.OrganizationResource;
import io.phasetwo.client.OrganizationRolesResource;
import io.phasetwo.client.OrganizationsResource;
import io.phasetwo.client.PhaseTwo;
import org.jboss.resteasy.spi.WriterException;
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
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;


@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    KeycklockConfig keycklockConfig;
    public String addUser(UserDTO userDTO) throws WriterException, IOException, com.google.zxing.WriterException {
        Keycloak keycloak = keycklockConfig.getInstance();
        PhaseTwo phaseTwo = new PhaseTwo(keycloak, keycklockConfig.getServerUrl());
        UsersResource usersResource = keycloak.realm(keycklockConfig.getRealm()).users();
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
        user.setCredentials(Arrays.asList(pass));
        Map<String, List<String>> attributes = new HashMap<>();
        attributes.put("numTel", Arrays.asList(userDTO.getNumTel()));
        attributes.put("typeContrat",Arrays.asList(userDTO.getTypeContrat()));
        attributes.put("salaire",Arrays.asList(userDTO.getSalaire()));
        attributes.put("dateEmbauche",Arrays.asList(new Date().toString()));
        user.setAttributes(attributes);
        Response response = usersResource.create(user);
        String userId = CreatedResponseUtil.getCreatedId(response);
        OrganizationsResource orgsResource = phaseTwo.organizations(keycklockConfig.getRealm());
        String orgId = userDTO.getOrgId();
        orgsResource.organization(orgId).memberships().add(userId);
        OrganizationResource orgResource = orgsResource.organization(orgId);
        OrganizationRolesResource rolesResource = orgResource.roles();
        rolesResource.grant(userDTO.getRole(), userId);
        //Creation du Code Qr
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        int width = 300;
        int height = 300;
        BitMatrix bitMatrix = qrCodeWriter.encode(userId, BarcodeFormat.QR_CODE, width, height,
                Map.of(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L));
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", outputStream);

        userRepository.save(new UserDTO(userId, outputStream.toByteArray(), userDTO.getOrgId(),
                keycklockConfig.getRealm(), userDTO.getUsername(), userDTO.getPassword(), userDTO.getEmail(),
                userDTO.getFirstName(), userDTO.getLastName(), userDTO.getNumTel(), userDTO.getRole(),
                userDTO.getTypeContrat(), userDTO.getSalaire(), new Date().toString()));
        return userId;
    }
    public void updateUser(UserDTO userDTO) {
        // Check if the user exists in the database
        UserDTO existUser = userRepository.findById(userDTO.getId()).orElse(null);
        if (existUser == null) {
            throw new IllegalArgumentException("User with ID " + userDTO.getId() + " does not exist");
        }
        // Update the user's password if it has been provided
        if (userDTO.getPassword() != null && !userDTO.getPassword().isEmpty()) {
            // Update the password in Keycloak
            Keycloak keycloak = keycklockConfig.getInstance();
            UsersResource usersResource = keycloak.realm(keycklockConfig.getRealm()).users();
            UserResource userResource = usersResource.get(userDTO.getId());
            CredentialRepresentation password = new CredentialRepresentation();
            password.setType(CredentialRepresentation.PASSWORD);
            password.setValue(userDTO.getPassword());
            password.setTemporary(false);
            userResource.resetPassword(password);
            // Update the password in the database
            existUser.setPassword(userDTO.getPassword());
            userRepository.save(existUser);
        }
        // Update the user's other properties in Keycloak
        Keycloak keycloak = keycklockConfig.getInstance();
        UsersResource usersResource = keycloak.realm(keycklockConfig.getRealm()).users();
        UserResource userResource = usersResource.get(userDTO.getId());
        UserRepresentation user = userResource.toRepresentation();
        user.setEmail(userDTO.getEmail());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setUsername(userDTO.getUsername());
        user.setEnabled(true);
        Map<String, List<String>> attributes = new HashMap<>();
        attributes.put("numTel", Arrays.asList(userDTO.getNumTel()));
        attributes.put("typeContrat",Arrays.asList(userDTO.getTypeContrat()));
        attributes.put("salaire",Arrays.asList(userDTO.getSalaire()));
        attributes.put("dateEmbauche",Arrays.asList(userDTO.getDateEmbauche()));
        user.setAttributes(attributes);
        userResource.update(user);
        // Update the user's other properties in the database
        existUser.setUsername(userDTO.getUsername());
        existUser.setFirstName(userDTO.getFirstName());
        existUser.setLastName(userDTO.getLastName());
        existUser.setDateEmbauche(userDTO.getDateEmbauche());
        existUser.setEmail(userDTO.getEmail());
        existUser.setRole(userDTO.getRole());
        existUser.setSalaire(userDTO.getSalaire());
        existUser.setNumTel(userDTO.getNumTel());
        existUser.setTypeContrat(userDTO.getTypeContrat());
        userRepository.save(existUser);
    }

    public List<UserDTO> getAll() {
        return userRepository.findAll();
    }
    public UserDTO getUserById(String userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User not found"));
    }
    public UserDTO getUserByUserName(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new NoSuchElementException("User not found"));
    }
    public UserResource confirmUserOrganization(String orgId , String userId){
        Keycloak keycloak = keycklockConfig.getInstance();
        PhaseTwo phaseTwo = new PhaseTwo(keycloak, keycklockConfig.getServerUrl());
        Response response = phaseTwo.getOrganizationMembershipsApi().checkOrganizationMembership(keycklockConfig.realm, orgId, userId);
        return response.readEntity(UserResource.class);
    }
    public void deleteUser(String userId) throws  WriterException {
        Keycloak keycloak = keycklockConfig.getInstance();
        // Suppression de l'utilisateur de Keycloak
        UsersResource usersResource = keycloak.realm(keycklockConfig.getRealm()).users();
        UserResource userResource = usersResource.get(userId);
        userResource.remove();

        // Suppression de l'utilisateur de la base de données
        userRepository.deleteById(userId);
    }

}

