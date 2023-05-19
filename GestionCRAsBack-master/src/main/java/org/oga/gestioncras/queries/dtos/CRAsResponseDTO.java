package org.oga.gestioncras.queries.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.oga.gestioncras.enums.CRAsStatus;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CRAsResponseDTO {
    private String idCRAs;
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
    private CRAsStatus status;
}
