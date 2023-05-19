package com.example.Erp.commonApi.event.ProjectEvent;

import com.example.Erp.commonApi.enums.projectStatus;
import com.example.Erp.commonApi.event.BaseEvent;

public class ProjectDeletedEvent extends BaseEvent<String> {
    private final projectStatus status;
    public ProjectDeletedEvent(String id) {
        super(id);
        this.status = projectStatus.DELETED;

    }

    @Override
    public String getId() {
        return super.getId();
    }

    public projectStatus getStatus() {
        return status;
    }
}
