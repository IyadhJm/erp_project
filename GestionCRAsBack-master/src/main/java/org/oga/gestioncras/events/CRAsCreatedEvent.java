package org.oga.gestioncras.events;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.oga.gestioncras.enums.CRAsStatus;

import java.time.LocalDate;
@NoArgsConstructor
public class CRAsCreatedEvent extends BaseEvent <String> {
    @Getter   private String timeSpent;
    @Getter   private String description;
    @Getter   private LocalDate startDate;
    @Getter   private LocalDate endDate;
    @Getter   private String idProject;
    @Getter   private String idResponsible;
    @Getter   private String idCollaborator;
    @Getter   private String comment;
    @Getter   private double productivity;
    @Getter   private Boolean approve;
    @Getter   private CRAsStatus status;

    public CRAsCreatedEvent(String id, String timeSpent, String description, LocalDate startDate, LocalDate endDate, String idProject, String idResponsible, String idCollaborator, String comment, double productivity, Boolean approve, CRAsStatus status) {
        super(id);
        this.timeSpent = timeSpent;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.idProject = idProject;
        this.idResponsible = idResponsible;
        this.idCollaborator = idCollaborator;
        this.comment = comment;
        this.productivity = productivity;
        this.approve = approve;
        this.status = status;
    }
}
