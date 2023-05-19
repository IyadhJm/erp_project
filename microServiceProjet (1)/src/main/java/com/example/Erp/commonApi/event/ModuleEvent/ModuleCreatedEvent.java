package com.example.Erp.commonApi.event.ModuleEvent;

import com.example.Erp.commonApi.enums.moduleStatus;
import com.example.Erp.commonApi.event.BaseEvent;
import lombok.Getter;



public class ModuleCreatedEvent extends BaseEvent<String> {

    @Getter
    private final String moduleTitle;
    @Getter
    private final String moduleDescription;
    @Getter
    private final String userId;

    @Getter
    private final moduleStatus status;
    @Getter
    private final String project;

    public ModuleCreatedEvent( String id, String moduleTitle, String moduleDescription, String userId,String project, moduleStatus status) {
        super(id);
        this.moduleTitle = moduleTitle;
        this.moduleDescription = moduleDescription;
        this.userId = userId;
        this.project=project;

        this.status=status;
    }
}
