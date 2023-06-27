package com.example.gestionbdg.queries.dtos;

import com.example.gestionbdg.enums.GbdgStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class GestionBdjResponseDTO {
 private String bdgId;
 private List<String> userName = new ArrayList<>();
 private String collaboratorId;
 private Double tjm;
 private Double cjm;
 private GbdgStatus status;
}
