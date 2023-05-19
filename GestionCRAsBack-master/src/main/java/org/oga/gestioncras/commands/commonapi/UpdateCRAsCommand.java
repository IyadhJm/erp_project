package org.oga.gestioncras.commands.commonapi;

import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.oga.gestioncras.queries.entities.CRAs;

import java.time.LocalDate;

public class UpdateCRAsCommand {
    @TargetAggregateIdentifier
    @Getter private String crasId;
   @Getter private String timeSpent;
    @Getter private String description;
    @Getter private LocalDate startDate;
    @Getter private LocalDate endDate;
    @Getter  private String idProject;
    @Getter private String idResponsible;
    @Getter  private String idCollaborator;
    @Getter private String comment;
    @Getter private double productivity;
    @Getter private Boolean approve;

    public UpdateCRAsCommand(String crasId, String timeSpent, String description, LocalDate startDate, LocalDate endDate, String idProject, String idResponsible, String idCollaborator, String comment, double productivity, Boolean approve) {
        this.crasId = crasId;
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
    }
}
