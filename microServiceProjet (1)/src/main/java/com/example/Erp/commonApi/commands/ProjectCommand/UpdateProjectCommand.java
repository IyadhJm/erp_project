package com.example.Erp.commonApi.commands.ProjectCommand;

import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.time.LocalDate;
import java.util.List;

public class UpdateProjectCommand  {
    @TargetAggregateIdentifier
    @Getter
    private final String id;
    @Getter
    private final String projectTitle;
    @Getter
    private final String projectDescription;
    @Getter
    private final List<String> collaborators;
    @Getter
    private final LocalDate startDate;
    @Getter
    private final LocalDate endDate;
    public UpdateProjectCommand(String id, String projectTitle, String projectDescription, List<String> collaborators, LocalDate startDate, LocalDate endDate) {
        this.id=id;
        this.projectTitle = projectTitle;
        this.projectDescription = projectDescription;
        this.collaborators = collaborators;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
