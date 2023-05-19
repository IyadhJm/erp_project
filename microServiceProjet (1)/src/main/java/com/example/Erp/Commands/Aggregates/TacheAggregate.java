package com.example.Erp.Commands.Aggregates;

import com.example.Erp.Exception.MissingInputException;
import com.example.Erp.commonApi.ProducerKafka.TaskProducer;
import com.example.Erp.commonApi.commands.TacheCommand.*;
import com.example.Erp.commonApi.enums.tacheStatus;
import com.example.Erp.commonApi.event.TacheEvent.*;
import lombok.Getter;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

import java.time.LocalDate;

@Aggregate
public class TacheAggregate {
    @AggregateIdentifier
    private String tacheID;
    private String tacheTitle;
    private String tacheDescription;


    private  String collaborator;

    private  String responsible;
    private tacheStatus status;
    private LocalDate startDate;
    private LocalDate endDate;
    private String project;

    public TacheAggregate() {

    }


    @CommandHandler
    public TacheAggregate(CreateTacheCommand command) {
        AggregateLifecycle.apply(new TacheCreatedEvent(
                command.getId(),
                command.getTacheTitle(),
                command.getTacheDescription(),
                command.getCollaborator(),
                command.getResponsible(),
                command.getProject(),
               command.getStartDate(),
                command.getEndDate(),

                tacheStatus.CREATED));

    }

    @EventSourcingHandler
    public void on(TacheCreatedEvent event) {
        this.tacheID = event.getId();
        this.tacheTitle = event.getTacheTitle();
        this.tacheDescription = event.getTacheDescription();
        this.collaborator = event.getCollaborator();
        this.responsible = event.getResponsible();
        this.project = event.getProject();
        this.startDate=event.getStartDate();
        this.endDate=event.getEndDate();

        this.status=event.getStatus();


    }

    @CommandHandler
    public void handle(UpdateTacheCommand command) {

        if ((command.getTacheTitle() == null) ||
                (command.getTacheDescription()== null) ) {
            throw new MissingInputException("Inputs should not be null");
        }
        AggregateLifecycle.apply(new TacheUpdatedEvent(
                command.getId(),
                command.getTacheTitle(),
                command.getTacheDescription(),
                command.getCollaborator(),
                command.getResponsible(),
                command.getProject(),
                command.getStartDate(),
                command.getEndDate(),
                tacheStatus.UPDATED));
    }

    @EventSourcingHandler
    public void on(TacheUpdatedEvent event) {
        this.tacheID= event.getId();
        this.tacheTitle= event.getTacheTitle();
        this.tacheDescription= event.getTacheDescription();
        this.collaborator = event.getCollaborator();
        this.responsible = event.getResponsible();
        this.project= event.getProject();
        this.startDate = event.getStartDate();
        this.endDate = event.getEndDate();
        this.status=event.getStatus();




    }
    @CommandHandler
    public void handle(StartedTacheCommand command) {

        if ((command.getTacheTitle() == null) ||
                (command.getTacheDescription()== null) ) {
            throw new MissingInputException("Inputs should not be null");
        }
        AggregateLifecycle.apply(new TachePrgressedEvent(
                command.getId(),
                command.getTacheTitle(),
                command.getTacheDescription(),
                command.getCollaborator(),
                command.getResponsible(),
                command.getProject(),
                command.getStartDate(),
                command.getEndDate(),
                tacheStatus.Progress));
    }

    @EventSourcingHandler
    public void on(TachePrgressedEvent event) {
        this.tacheID= event.getId();
        this.tacheTitle= event.getTacheTitle();
        this.tacheDescription= event.getTacheDescription();
        this.collaborator = event.getCollaborator();
        this.responsible = event.getResponsible();
        this.project= event.getProject();
        this.startDate = event.getStartDate();
        this.endDate = event.getEndDate();
        this.status=event.getStatus();




    }
    @CommandHandler
    public void handle(TesteTacheCommand command) {

        if ((command.getTacheTitle() == null) ||
                (command.getTacheDescription()== null) ) {
            throw new MissingInputException("Inputs should not be null");
        }
        AggregateLifecycle.apply(new TacheTestedEvent(
                command.getId(),
                command.getTacheTitle(),
                command.getTacheDescription(),
                command.getCollaborator(),
                command.getResponsible(),
                command.getProject(),
                command.getStartDate(),
                command.getEndDate(),
                tacheStatus.Testing));
    }

    @EventSourcingHandler
    public void on(TacheTestedEvent event) {
        this.tacheID= event.getId();
        this.tacheTitle= event.getTacheTitle();
        this.tacheDescription= event.getTacheDescription();
        this.collaborator = event.getCollaborator();
        this.responsible = event.getResponsible();
        this.project= event.getProject();
        this.startDate = event.getStartDate();
        this.endDate = event.getEndDate();
        this.status=event.getStatus();





    }
    @CommandHandler
    public void handle(CompleteTacheCommand command) {

        if ((command.getTacheTitle() == null) ||
                (command.getTacheDescription()== null) ) {
            throw new MissingInputException("Inputs should not be null");
        }
        AggregateLifecycle.apply(new TacheUpdatedEvent(
                command.getId(),
                command.getTacheTitle(),
                command.getTacheDescription(),
                command.getCollaborator(),
                command.getResponsible(),
                command.getProject(),
                command.getStartDate(),
                command.getEndDate(),
                tacheStatus.Completed));
    }

    @EventSourcingHandler
    public void on(TacheComplitedEvent event) {
        this.tacheID= event.getId();
        this.tacheTitle= event.getTacheTitle();
        this.tacheDescription= event.getTacheDescription();
        this.collaborator = event.getCollaborator();
        this.responsible = event.getResponsible();
        this.project= event.getProject();
        this.startDate = event.getStartDate();
        this.endDate = event.getEndDate();
        this.status=event.getStatus();




    }
    @CommandHandler
    public void handle(DeleteTacheCommand command){
        AggregateLifecycle.apply(new TacheDeletedEvent(command.getId()));
    }
    @EventSourcingHandler
    public void on(TacheDeletedEvent event){
        this.status=tacheStatus.DELETED;
    }
}
