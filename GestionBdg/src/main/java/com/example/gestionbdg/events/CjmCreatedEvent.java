package com.example.gestionbdg.events;


import com.example.gestionbdg.enums.GbdgStatus;
import lombok.Getter;

public class CjmCreatedEvent extends BaseEvent <String> {
    @Getter
    private String collaborator;
    @Getter  private Double tjm;
    @Getter
    private Double cjm;
    @Getter
    private String task;
    @Getter
    private double dayNumber;
    @Getter
    private String project ;

    @Getter   private GbdgStatus status;

    public CjmCreatedEvent(String id, String collaborator, Double tjm, Double cjm, String task, double dayNumber, String project, GbdgStatus status) {
        super(id);
        this.collaborator = collaborator;
        this.tjm = tjm;
        this.cjm = cjm;
        this.task = task;
        this.dayNumber = dayNumber;
        this.project = project;
        this.status = status;
    }
}
