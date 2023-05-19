package com.example.Erp.commonApi.commands.ModuleCommand;

import com.example.Erp.commonApi.commands.BaseCommand;
import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class UpdateModuleCommand  {
    @TargetAggregateIdentifier
    @Getter
    private final String id;
    @Getter
    private final String moduleTitle;
    @Getter
    private final String moduleDescription;
    @Getter
    private final String userId;
    @Getter
    private final String project;

    public UpdateModuleCommand(String id, String moduleTitle, String moduleDescription, String userId, String project) {
        this.id= id;
        this.moduleTitle = moduleTitle;
        this.moduleDescription = moduleDescription;
        this.userId = userId;
        this.project = project;

    }
}
