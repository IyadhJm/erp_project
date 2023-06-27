package com.example.gestionbdg.queries.dtos;

import com.example.gestionbdg.enums.GbdgStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class GestionBdjResponseDTO {
 private String bdgId;
 private String collaborator;
 private Double tjm;
 private Double cjm;
 private String task;
 private double dayNumber;
 private String project ;
 private GbdgStatus status;
}
