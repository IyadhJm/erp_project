package com.example.gestionbdg.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data @AllArgsConstructor @NoArgsConstructor
public class GestionBdgDTO {
    private String bdgId;
    private List<String> userName;
    private String collaboratorId;
    private Double tjm;
    private Double cjm;
    
}
