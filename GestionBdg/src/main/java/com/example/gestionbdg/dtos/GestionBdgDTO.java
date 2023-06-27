package com.example.gestionbdg.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data @AllArgsConstructor @NoArgsConstructor
public class GestionBdgDTO {
    private String bdgId;
    private String collaborator;
    private Double tjm;
    private Double cjm;
    private String task;
    private double dayNumber;
    private String project ;


}
