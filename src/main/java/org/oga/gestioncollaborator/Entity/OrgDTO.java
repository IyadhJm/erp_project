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
public class OrgDTO {
    @Id
    private String id;
    private String realm;
    private String name;
    private String displayName;
    private String url;
    private String domain;
    private String attributes;
}
