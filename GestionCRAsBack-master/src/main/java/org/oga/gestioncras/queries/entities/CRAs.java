package org.oga.gestioncras.queries.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.oga.gestioncras.enums.CRAsStatus;


import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CRAs {
    @Id
    private String crasId;
    private String timeSpent;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private String idProject;
    private String idResponsible;
    private String idCollaborator;
    private String comment;
    private double productivity;
    private Boolean approve;
    @Enumerated(EnumType.STRING)
    private CRAsStatus status;
}
