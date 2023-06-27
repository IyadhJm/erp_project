package com.example.gestionbdg.commonapi;
import lombok.Getter;


public class CreateCjmCommand extends BaseCommand<String> {
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


    public CreateCjmCommand(String id, String collaborator, Double tjm, Double cjm, String task, double dayNumber, String project) {
        super(id);
        this.collaborator = collaborator;

        this.tjm = tjm;
        this.cjm = cjm;
        this.task = task;
        this.dayNumber = dayNumber;
        this.project = project;
    }


}
