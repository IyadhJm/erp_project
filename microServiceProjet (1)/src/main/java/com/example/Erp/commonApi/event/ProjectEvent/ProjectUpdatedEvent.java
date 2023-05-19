package com.example.Erp.commonApi.event.ProjectEvent;

import com.example.Erp.commonApi.enums.projectStatus;
import com.example.Erp.commonApi.event.BaseEvent;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

public class ProjectUpdatedEvent extends BaseEvent<String> {

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
    @Getter
    private final projectStatus status;


    public ProjectUpdatedEvent(String id, String projectTitle, String projectDescription, List<String> collaborators, LocalDate startDate, LocalDate endDate, projectStatus status) {
        super(id);
        this.projectTitle = projectTitle;
        this.projectDescription = projectDescription;
        this.collaborators = collaborators;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status=status;
    }
}
