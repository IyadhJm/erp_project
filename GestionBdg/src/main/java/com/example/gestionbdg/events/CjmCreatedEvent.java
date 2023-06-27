package com.example.gestionbdg.events;


import com.example.gestionbdg.enums.GbdgStatus;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;


public class CjmCreatedEvent extends BaseEvent <String> {
    @Getter
    private List<String> userName;
    @Getter
    private String collaboratorId;
    @Getter  private Double tjm;
    @Getter
    private Double cjm;
    @Getter   private GbdgStatus status;

    public CjmCreatedEvent(String id, List<String> userName, String collaboratorId, Double tjm, Double cjm, GbdgStatus status) {
        super(id);
        this.userName =  userName;
        this.collaboratorId = collaboratorId;
        this.tjm = tjm;
        this.cjm = cjm;
        this.status = status;
    }






}
