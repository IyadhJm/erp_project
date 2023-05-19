package com.example.Erp.commonApi.event.ProjectEvent;

import com.example.Erp.commonApi.enums.projectStatus;
import com.example.Erp.commonApi.event.BaseEvent;
import lombok.Getter;

public class ProjectStartedEvent extends BaseEvent<String> {
    @Getter private final projectStatus status;

    public ProjectStartedEvent(String id, projectStatus status) {
        super(id);
        this.status=status;
    }
}

