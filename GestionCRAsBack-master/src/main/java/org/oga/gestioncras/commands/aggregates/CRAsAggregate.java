package org.oga.gestioncras.commands.aggregates;


import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.oga.gestioncras.commands.commonapi.CreateCRAsCommand;
import org.oga.gestioncras.commands.commonapi.UpdateCRAsCommand;
import org.oga.gestioncras.enums.CRAsStatus;
import org.oga.gestioncras.events.CRAsCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.oga.gestioncras.events.CRAsUpdatedEvent;
import org.oga.gestioncras.queries.entities.CRAs;
import org.oga.gestioncras.queries.repositories.CRAsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalDate;
import java.util.List;

@Aggregate
@Slf4j
public class CRAsAggregate {


    @AggregateIdentifier
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
    private CRAsStatus status;

public CRAsAggregate() {

}

    @CommandHandler
    public CRAsAggregate(CreateCRAsCommand createCRAsCommand) {
        if((createCRAsCommand.getDescription()==null) || (createCRAsCommand.getStartDate()==null) || (createCRAsCommand.getEndDate()==null) || (createCRAsCommand.getIdProject()==null)){
            throw new RuntimeException("Input should not be null");
        }
        if (createCRAsCommand.getEndDate().isBefore(createCRAsCommand.getStartDate())) {
            throw new IllegalArgumentException("The end date cannot be earlier than the start date");
        }
        log.info("CreateCRAsCommand Reveived");



        AggregateLifecycle.apply(
                new CRAsCreatedEvent(
                       createCRAsCommand.getId(),
                        createCRAsCommand.getTimeSpent(),
                        createCRAsCommand.getDescription(),
                        createCRAsCommand.getStartDate(),
                        createCRAsCommand.getEndDate(),
                        createCRAsCommand.getIdProject(),
                        createCRAsCommand.getIdResponsible(),
                        createCRAsCommand.getIdCollaborator(),
                        createCRAsCommand.getComment(),
                        createCRAsCommand.getProductivity(),
                        createCRAsCommand.getApprove(),
                        CRAsStatus.CREATED));

    }
        @EventSourcingHandler
        public void on(CRAsCreatedEvent event){
            log.info("CRAsCreatedEvent Occured");
            this.crasId= event.getId();
            this.startDate=event.getStartDate();
            this.endDate=event.getEndDate();
            this.description= event.getDescription();
            this.timeSpent =event.getTimeSpent();
            this.idProject= event.getIdProject();
            this.idCollaborator= event.getIdCollaborator();
            this.idResponsible= event.getIdResponsible();
            this.comment= event.getComment();
            this.productivity=event.getProductivity();
            this.approve=event.getApprove();
            this.status=event.getStatus();
        }
        @CommandHandler
        public void CRAsAggregate(UpdateCRAsCommand command){
            if((command.getDescription()==null) || (command.getStartDate()==null) || (command.getEndDate()==null)  || (command.getIdProject()==null)){
                throw new RuntimeException("Input should not be null");
            }
            if (command.getEndDate().isBefore(command.getStartDate())) {
                throw new IllegalArgumentException("The end date cannot be earlier than the start date");
            }
        AggregateLifecycle.apply(new CRAsUpdatedEvent(
               command.getCrasId(),
                command.getTimeSpent(),
                command.getDescription(),
                command.getStartDate(),
                command.getEndDate(),
                command.getIdProject(),
                command.getIdResponsible(),
                command.getIdCollaborator(),
                command.getComment(),
                command.getProductivity(),
                command.getApprove(),
                CRAsStatus.UPDATED
        ));
        }
    @EventSourcingHandler
    public void on(CRAsUpdatedEvent event){
        this.crasId= event.getId();
        this.timeSpent= event.getTimeSpent();
        this.description= event.getDescription();
        this.startDate=event.getStartDate();
        this.endDate=event.getEndDate();
        this.idProject= event.getIdProject();
        this.idResponsible=event.getIdResponsible();
        this.idCollaborator= event.getIdCollaborator();
        this.comment= event.getComment();
        this.productivity=event.getProductivity();
        this.approve=event.getApprove();
        this.status=event.getStatus();
    }


}