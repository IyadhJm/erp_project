package com.example.gfichpaie.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data @AllArgsConstructor @NoArgsConstructor
public class FicheDePaieRequestDTO {
    private String ficheId;
    private String userName;
    private Date date;
    private Double salaireBrut;
    private Double impots;
    private String collaboratorId;
    private Double salaireNet;
    private Double chargeSociale;
    private Double prime;
    private Double tjm;
    
}
