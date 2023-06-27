package com.example.gestionbdg.commonapi;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class CreateCjmCommand extends BaseCommand<String> {
    @Getter
    private List<String> userName;
    @Getter
    private String collaboratorId;
    @Getter  private Double tjm;
    @Getter
    private Double cjm;


    public CreateCjmCommand(String id, List<String> userName, String collaboratorId, Double tjm, Double cjm) {
        super(id);
        this.userName = userName;
        this.collaboratorId = collaboratorId;
        this.tjm = tjm;
        this.cjm = cjm;
    }




}
