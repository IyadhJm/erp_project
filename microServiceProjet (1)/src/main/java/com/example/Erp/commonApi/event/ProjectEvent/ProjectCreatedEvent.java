package com.example.Erp.commonApi.event.ProjectEvent;

import com.example.Erp.commonApi.enums.projectStatus;
import com.example.Erp.commonApi.event.BaseEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
@NoArgsConstructor

public class ProjectCreatedEvent extends BaseEvent<String> {

    @Getter
    private  String projectTitle;
    @Getter
    private  String projectDescription;
    @Getter
    private  List<String> collaborators;
    @Getter
    private  LocalDate startDate;
    @Getter
    private  LocalDate endDate;
    @Getter
    private  projectStatus status;

    public ProjectCreatedEvent(String id, String projectTitle, String projectDescription, List<String> collaborators, LocalDate startDate, LocalDate endDate, projectStatus status) {
        super(id);
        this.projectTitle = projectTitle;
        this.projectDescription = projectDescription;
        this.collaborators = collaborators;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status=status;
    }

}
