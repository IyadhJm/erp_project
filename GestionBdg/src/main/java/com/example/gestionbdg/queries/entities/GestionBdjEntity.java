package com.example.gestionbdg.queries.entities;


import com.example.gestionbdg.enums.GbdgStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GestionBdjEntity {
    @Id
    private String bdgId;
    private String collaborator;
    private Double tjm;
    private Double cjm;
    private String task;
    private double dayNumber;
    private String project ;
    @Enumerated(EnumType.STRING)
    private GbdgStatus status;
}


