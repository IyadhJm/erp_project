package org.oga.gestioncras.commands.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data @AllArgsConstructor @NoArgsConstructor
public class CRAsRequestDTO {
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
}
