package org.oga.gestioncras.events;

import lombok.Getter;
import org.oga.gestioncras.enums.CRAsStatus;

import java.time.LocalDate;

public class CRAsStartedEvent extends BaseEvent <String> {
   @Getter private final CRAsStatus status;
    public CRAsStartedEvent(String id, String timeSpent, String description, LocalDate startDate, LocalDate endDate,String idProject, String idCollaborator, String idResponsible, String comment, double productivity, Boolean approve,CRAsStatus status) {
        super(id);
        this.status=status;

    }
}
