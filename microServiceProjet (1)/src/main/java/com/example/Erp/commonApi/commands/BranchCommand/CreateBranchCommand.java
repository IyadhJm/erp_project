package com.example.Erp.commonApi.commands.BranchCommand;

import com.example.Erp.commonApi.commands.BaseCommand;
import lombok.Getter;

public class CreateBranchCommand extends BaseCommand<String> {
    @Getter
    private  final String branchTitle;
    @Getter
    private final String branchDescription;
    @Getter
    private final String userId;
    @Getter
    private final String project;

    public CreateBranchCommand(String id, String branchTitle, String branchDescription, String userId, String project) {
        super(id);
        this.branchTitle = branchTitle;
        this.branchDescription = branchDescription;
        this.userId = userId;
        this.project = project;

    }
}
