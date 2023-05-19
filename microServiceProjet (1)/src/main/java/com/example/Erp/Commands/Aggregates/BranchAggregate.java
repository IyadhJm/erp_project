package com.example.Erp.Commands.Aggregates;

import com.example.Erp.Exception.MissingInputException;
import com.example.Erp.commonApi.commands.BranchCommand.CreateBranchCommand;
import com.example.Erp.commonApi.commands.BranchCommand.UpdateBranchCommand;
import com.example.Erp.commonApi.enums.branchStatus;
import com.example.Erp.commonApi.event.BranchEvent.BranchCreatedEvent;
import com.example.Erp.commonApi.event.BranchEvent.BranchUpdatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
public class BranchAggregate {
    @AggregateIdentifier
    public String branchID;
    public String branchTitle;
    public String branchDescription;

    public String userId;
    public branchStatus status;
    public String project;

    public BranchAggregate() {

    }

    @CommandHandler
    public BranchAggregate(CreateBranchCommand command) {
        AggregateLifecycle.apply(new BranchCreatedEvent(
                command.getId(),
                command.getBranchTitle(),
                command.getBranchDescription(),
                command.getUserId(),
                command.getProject(),

                branchStatus.CREATED));

    }

    @EventSourcingHandler
    public void on(BranchCreatedEvent event) {
        this.branchID = event.getId();
        this.branchTitle = event.getBranchTitle();
        this.branchDescription = event.getBranchDescription();
        this.project = event.getProject();

        this.status=event.getStatus();


    }

    @CommandHandler
    public void handle(UpdateBranchCommand command) {

        if ((command.getBranchTitle() == null) ||
                (command.getBranchDescription()== null) ) {
            throw new MissingInputException("Inputs should not be null");
        }
        AggregateLifecycle.apply(new BranchUpdatedEvent(
                command.getId(),
                command.getBranchTitle(),
                command.getBranchDescription(),
                command.getUserId(),
                command.getProject(),
                branchStatus.UPDATED));
    }

    @EventSourcingHandler
    public void on(BranchUpdatedEvent event) {
        this.branchID= event.getId();
        this.branchTitle= event.getBranchTitle();
        this.branchDescription= event.getBranchDescription();
        this.userId = event.getUserId();
        this.project= event.getProject();
        this.status=event.getStatus();




    }

}
