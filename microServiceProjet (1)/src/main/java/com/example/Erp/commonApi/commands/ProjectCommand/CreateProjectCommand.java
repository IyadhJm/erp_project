package com.example.Erp.commonApi.commands.ProjectCommand;

import com.example.Erp.commonApi.commands.BaseCommand;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

public class CreateProjectCommand  extends BaseCommand<String> {
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
    public CreateProjectCommand(String id, String projectTitle, String projectDescription, List<String> collaborators,LocalDate startDate, LocalDate endDate) {
        super(id);
        this.projectTitle = projectTitle;
        this.projectDescription = projectDescription;
        this.collaborators = collaborators;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
