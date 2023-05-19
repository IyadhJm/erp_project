package com.example.Erp.commonApi.event.TacheEvent;

import com.example.Erp.commonApi.enums.tacheStatus;
import com.example.Erp.commonApi.event.BaseEvent;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
public class TacheTestedEvent extends BaseEvent<String> {

    @Getter
    private  String tacheTitle;
    @Getter
    private  String tacheDescription;
    @Getter
    private  String collaborator;
    @Getter
    private  String responsible;

    @Getter
    private  tacheStatus status;
    @Getter
    private   String project;
    @Getter
    private  LocalDate startDate;
    @Getter
    private  LocalDate endDate;


    public TacheTestedEvent(String id, String tacheTitle, String tacheDescription, String collaborator, String responsible,String project, LocalDate startDate, LocalDate endDate, tacheStatus status) {
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
