package org.oga.gestioncollaborator.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    @Id
    private String id;
    private byte[] qrCode;
    private String orgId;
    private String realm;
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String numTel;
    private String role;
    private String typeContrat;
    private String salaire;
    private String dateEmbauche;

}
