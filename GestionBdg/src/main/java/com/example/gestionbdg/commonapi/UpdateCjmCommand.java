package com.example.gestionbdg.commonapi;

import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;


public class UpdateCjmCommand {
    @TargetAggregateIdentifier
    @Getter private String bdgId;
    @Getter private String collaborator;
    @Getter  private Double tjm;
    @Getter private Double cjm;
    @Getter private String task;
    @Getter private double dayNumber;
    @Getter private String project ;
    public UpdateCjmCommand(String bdgId, String collaborator, Double tjm, Double cjm, String task, double dayNumber, String project) {
        this.bdgId = bdgId;
        this.collaborator = collaborator;
        this.tjm = tjm;
        this.cjm = cjm;
        this.task = task;
        this.dayNumber = dayNumber;
        this.project = project;
    }















}
