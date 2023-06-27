package com.example.gestionbdg.commonapi;

import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.List;

public class UpdateCjmCommand {
    @TargetAggregateIdentifier
    @Getter private String bdgId;
    @Getter
    private List<String> userName;
    @Getter
    private String collaboratorId;
    @Getter  private Double tjm;
    @Getter
    private Double cjm;

    public UpdateCjmCommand(String bdgId, List<String> userName, String collaboratorId, Double tjm, Double cjm) {
        this.bdgId = bdgId;
        this.userName = userName;
        this.collaboratorId = collaboratorId;
        this.tjm = tjm;
        this.cjm = cjm;
    }













}
