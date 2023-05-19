package com.example.Erp.commonApi.commands.TacheCommand;

import com.example.Erp.commonApi.commands.BaseCommand;
import lombok.Getter;

import java.time.LocalDate;

public class CreateTacheCommand extends BaseCommand<String> {
    @Getter
    private final String tacheTitle;
    @Getter
    private final String tacheDescription;
    @Getter
    private final String collaborator;
    @Getter
    private final String responsible;
    @Getter
    private final String project;
    @Getter
    private final LocalDate startDate;
    @Getter
    private final LocalDate endDate;

    public CreateTacheCommand(String id, String tacheTitle, String tacheDescription, String collaborator,String responsible, String project, LocalDate startDate, LocalDate endDate) {
        super(id);
        this.tacheTitle = tacheTitle;
        this.tacheDescription = tacheDescription;
        this.collaborator = collaborator;
        this.responsible= responsible;
        this.project = project;
        this.startDate=startDate;
        this.endDate=endDate;

    }
}
