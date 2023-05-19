package com.example.Erp.Commands.Aggregates;

import com.example.Erp.Exception.MissingInputException;
import com.example.Erp.commonApi.commands.ProjectCommand.CreateProjectCommand;
import com.example.Erp.commonApi.commands.ProjectCommand.DeletProjectCommand;
import com.example.Erp.commonApi.commands.ProjectCommand.UpdateProjectCommand;
import com.example.Erp.commonApi.enums.projectStatus;
import com.example.Erp.commonApi.event.ProjectEvent.ProjectCreatedEvent;
import com.example.Erp.commonApi.event.ProjectEvent.ProjectDeletedEvent;
import com.example.Erp.commonApi.event.ProjectEvent.ProjectUpdatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.List;

@Aggregate
public class ProjectAggregate {
    @AggregateIdentifier
    private String projectId;
    private String projectTitle;
    private String projectDescription;

    private List<String> collaborators;
    private String startDate;
    private String endDate;
    private projectStatus status;

    public ProjectAggregate() {

    }

    @CommandHandler
    public ProjectAggregate(CreateProjectCommand command) {
        AggregateLifecycle.apply(new ProjectCreatedEvent(
                command.getId(),
                command.getProjectTitle(),
                command.getProjectDescription(),
                command.getCollaborators(),
                command.getStartDate(),
                command.getEndDate(),
                projectStatus.CREATED));

    }

    @EventSourcingHandler
    public void on(ProjectCreatedEvent event) {
        this.projectId = event.getId();
        this.projectTitle = event.getProjectTitle();
        this.projectDescription = event.getProjectDescription();
        this.startDate = event.getStartDate().toString();
        this.endDate = event.getEndDate().toString();
        this.collaborators= event.getCollaborators();
        this.status=event.getStatus();


    }

    @CommandHandler
    public void handle(UpdateProjectCommand command) {

        if ((command.getProjectTitle() == null) ||
                (command.getProjectDescription() == null) ||
                (command.getStartDate() == null ||
                        (command.getEndDate() == null))) {
            throw new MissingInputException("Inputs should not be null");
        }
        AggregateLifecycle.apply(new ProjectUpdatedEvent(
                command.getId(),
                command.getProjectTitle(),
                command.getProjectDescription(),
                command.getCollaborators(),
                command.getStartDate(),
                command.getEndDate(),
                projectStatus.UPDATED));
    }

    @EventSourcingHandler
    public void on(ProjectUpdatedEvent event) {
        this.projectId = event.getId();
        this.projectTitle = event.getProjectTitle();
        this.projectDescription = event.getProjectDescription();
        this.startDate = event.getStartDate().toString();
        this.endDate = event.getEndDate().toString();
        this.collaborators = event.getCollaborators();
        this.status=event.getStatus();




    }
    @CommandHandler
    public void handle(DeletProjectCommand command){
        AggregateLifecycle.apply(new ProjectDeletedEvent(command.getId()));
    }
    @EventSourcingHandler
    public void on(ProjectDeletedEvent event){
        this.status=projectStatus.DELETED;
    }
}
