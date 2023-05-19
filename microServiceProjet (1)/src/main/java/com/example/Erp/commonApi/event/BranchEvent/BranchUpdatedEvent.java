package com.example.Erp.commonApi.event.BranchEvent;

import com.example.Erp.commonApi.enums.branchStatus;
import com.example.Erp.commonApi.enums.moduleStatus;
import com.example.Erp.commonApi.event.BaseEvent;
import lombok.Getter;


public class BranchUpdatedEvent extends BaseEvent<String> {

    @Getter
    private final String branchTitle;
    @Getter
    private final String branchDescription;
    @Getter
    private final String userId;

    @Getter
    private final branchStatus status;
    @Getter
    private final String project;

    public BranchUpdatedEvent(String id, String branchTitle, String branchDescription, String userId, String project, branchStatus status) {
        super(id);
        this.branchTitle = branchTitle;
        this.branchDescription = branchDescription;
        this.userId = userId;
        this.project= project;

        this.status=status;

    }
}
