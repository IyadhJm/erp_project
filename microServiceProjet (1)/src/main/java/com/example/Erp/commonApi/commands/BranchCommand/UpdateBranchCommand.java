package com.example.Erp.commonApi.commands.BranchCommand;

import com.example.Erp.commonApi.commands.BaseCommand;
import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class UpdateBranchCommand  {
    @TargetAggregateIdentifier
    @Getter private final String id;
    @Getter
    private  final String branchTitle;
    @Getter
    private final String branchDescription;
    @Getter
    private final String userId;
    @Getter
    private final String project;

    public UpdateBranchCommand(String id, String branchTitle, String branchDescription, String userId, String project) {
        this.id = id;
        this.branchTitle = branchTitle;
        this.branchDescription = branchDescription;
        this.userId = userId;
        this.project = project;

    }
}
