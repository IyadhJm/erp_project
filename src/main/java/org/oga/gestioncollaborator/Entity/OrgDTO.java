package org.oga.gestioncollaborator.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

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
