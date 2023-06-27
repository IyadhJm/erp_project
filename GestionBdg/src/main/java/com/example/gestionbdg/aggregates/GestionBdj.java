package com.example.gestionbdg.aggregates;

import com.example.gestionbdg.commonapi.CreateCjmCommand;
import com.example.gestionbdg.commonapi.DeleteCjmCommand;
import com.example.gestionbdg.commonapi.UpdateCjmCommand;
import com.example.gestionbdg.enums.GbdgStatus;
import com.example.gestionbdg.events.CjmCreatedEvent;
import com.example.gestionbdg.events.CjmDeletedEvent;
import com.example.gestionbdg.events.CjmUpdatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.List;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;


@Aggregate
@Slf4j
public class GestionBdj {
    @AggregateIdentifier
    private String bdgId;
    private String collaborator;
    private Double tjm;
    private Double cjm;
    private String task;
    private String project;
    private GbdgStatus status;
    private double dayNumber;

    public GestionBdj() {
    }

    @CommandHandler
    public GestionBdj(CreateCjmCommand createCjmCommand) {
        if((createCjmCommand.getCjm()==null) || (createCjmCommand.getCollaborator()==null) ){
            throw new RuntimeException("Input should not be null");
        }
        log.info("CreateFDPCommand Reveived");

        AggregateLifecycle.apply(
                new CjmCreatedEvent(
                        createCjmCommand.getId(),
                        createCjmCommand.getCollaborator(),
                        createCjmCommand.getTjm(),
                        createCjmCommand.getCjm(),
                        createCjmCommand.getTask(),
                        createCjmCommand.getDayNumber(),
                        createCjmCommand.getProject(),
                        GbdgStatus.CREATED));

                        }
        @EventSourcingHandler
        public void on(CjmCreatedEvent event){
            log.info("FDPCreatedEvent Occured");
            this.bdgId= event.getId();
            this.collaborator=event.getCollaborator();
            this.tjm= event.getTjm();
            this.cjm= event.getCjm();
            this.task= event.getTask();
            this.dayNumber=event.getDayNumber();
            this.project=event.getProject();
            this.status=event.getStatus();
        }
        @CommandHandler
        public void ficheDePaieAggregate(UpdateCjmCommand command){
            if((command.getCollaborator()==null)  ){
                throw new RuntimeException("Input should not be null");
            }
        AggregateLifecycle.apply(new CjmUpdatedEvent(
                command.getBdgId(),
                command.getCollaborator(),
                command.getTjm(),
                command.getCjm(),
                command.getTask(),
                command.getDayNumber(),
                command.getProject(),
                GbdgStatus.UPDATED
        ));
        }
    @EventSourcingHandler
    public void on(CjmUpdatedEvent event){
        this.bdgId= event.getId();
        this.collaborator=event.getCollaborator();
        this.tjm= event.getTjm();
        this.cjm= event.getCjm();
        this.task= event.getTask();
        this.dayNumber= event.getDayNumber();
        this.project=event.getProject();
        this.status=event.getStatus();
    }

    @CommandHandler
    public void gestionBdjAggregate(DeleteCjmCommand command) {
        apply(new CjmDeletedEvent(command.getBdgId()));
    }

    @EventSourcingHandler
    public void on(CjmDeletedEvent event) {
        this.bdgId = event.getBdgId();
    }

}
