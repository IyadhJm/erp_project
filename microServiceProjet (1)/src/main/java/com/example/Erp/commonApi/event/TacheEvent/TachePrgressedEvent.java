package com.example.Erp.commonApi.event.TacheEvent;

import com.example.Erp.commonApi.enums.tacheStatus;
import com.example.Erp.commonApi.event.BaseEvent;
import lombok.Getter;

import java.time.LocalDate;


public class TachePrgressedEvent extends BaseEvent<String> {

    @Getter
    private final String tacheTitle;
    @Getter
    private final String tacheDescription;
    @Getter
    private final String collaborator;
    @Getter
    private final String responsible;

    @Getter
    private final tacheStatus status;
    @Getter
    private final  String project;
    @Getter
    private final LocalDate startDate;
    @Getter
    private final LocalDate endDate;


    public TachePrgressedEvent(String id, String tacheTitle, String tacheDescription, String collaborator,String responsible, String project, LocalDate startDate, LocalDate endDate, tacheStatus status) {
        super(id);
        this.tacheTitle = tacheTitle;
        this.tacheDescription = tacheDescription;
        this.collaborator = collaborator;
        this.responsible = responsible;
        this.project=project;
        this.startDate=startDate;
        this.endDate=endDate;

        this.status=status;
    }
}
