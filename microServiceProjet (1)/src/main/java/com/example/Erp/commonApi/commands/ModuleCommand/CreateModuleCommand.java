package com.example.Erp.commonApi.commands.ModuleCommand;

import com.example.Erp.commonApi.commands.BaseCommand;
import lombok.Getter;

public class CreateModuleCommand extends BaseCommand<String> {
    @Getter
    private  final String moduleTitle;
    @Getter
    private final String moduleDescription;
    @Getter
    private final String userId;
    @Getter
    private final String project;

    public CreateModuleCommand(String id, String moduleTitle, String moduleDescription, String userId,String project) {
        super(id);
        this.moduleTitle = moduleTitle;
        this.moduleDescription = moduleDescription;
        this.userId = userId;
        this.project = project;

    }
}
