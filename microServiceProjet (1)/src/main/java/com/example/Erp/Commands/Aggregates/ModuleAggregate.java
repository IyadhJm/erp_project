package com.example.Erp.Commands.Aggregates;

import com.example.Erp.Exception.MissingInputException;
import com.example.Erp.commonApi.commands.ModuleCommand.CreateModuleCommand;
import com.example.Erp.commonApi.commands.ModuleCommand.DeleteModuleCommand;
import com.example.Erp.commonApi.commands.ModuleCommand.UpdateModuleCommand;
import com.example.Erp.commonApi.enums.moduleStatus;

import com.example.Erp.commonApi.event.ModuleEvent.ModuleCreatedEvent;
import com.example.Erp.commonApi.event.ModuleEvent.ModuleDeletedEvent;
import com.example.Erp.commonApi.event.ModuleEvent.ModuleUpdatedEvent;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
public class ModuleAggregate {
    @AggregateIdentifier
    private String moduleID;
    private String moduleTitle;
    private String moduleDescription;

    private String userId;
    private moduleStatus status;
    private String project;

    public ModuleAggregate() {

    }

    @CommandHandler
    public ModuleAggregate(CreateModuleCommand command) {
        AggregateLifecycle.apply(new ModuleCreatedEvent(
                command.getId(),
                command.getModuleTitle(),
                command.getModuleDescription(),
                command.getUserId(),
                command.getProject(),

                moduleStatus.CREATED));

    }

    @EventSourcingHandler
    public void on(ModuleCreatedEvent event) {
        this.moduleID = event.getId();
        this.moduleTitle = event.getModuleTitle();
        this.moduleDescription = event.getModuleDescription();
        this.project = event.getProject();

        this.status=event.getStatus();


    }

    @CommandHandler
    public void handle(UpdateModuleCommand command) {

        if ((command.getModuleTitle() == null) ||
                (command.getModuleDescription()== null) ) {
            throw new MissingInputException("Inputs should not be null");
        }
        AggregateLifecycle.apply(new ModuleUpdatedEvent(
                command.getId(),
                command.getModuleTitle(),
                command.getModuleDescription(),
                command.getUserId(),
                command.getProject(),
                moduleStatus.UPDATED));
    }

    @EventSourcingHandler
    public void on(ModuleUpdatedEvent event) {
        this.moduleID= event.getId();
        this.moduleTitle= event.getModuleTitle();
        this.moduleDescription= event.getModuleDescription();
        this.userId = event.getUserId();
        this.project= event.getProject();
        this.status=event.getStatus();




    }
    @CommandHandler
    public void handle(DeleteModuleCommand command){
        AggregateLifecycle.apply(new ModuleDeletedEvent(command.getId()));
    }
    @EventSourcingHandler
    public void on(ModuleDeletedEvent event){
        this.status=moduleStatus.DELETED;
    }
}