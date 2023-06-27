package com.example.gfichpaie.queries.entities;

import com.example.gfichpaie.enums.FicheDePaieStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.NoArgsConstructor;


import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FDP {
    @Id
    private String ficheId;
    private String userName;
    private Date date;
    private Double salaireBrut;
    private Double impots;
    private String idCollaborator;
    private Double salaireNet;
    private Double chargeSociale;
    private Double prime;
    private Double tjm;
    @Enumerated(EnumType.STRING)
    private FicheDePaieStatus status;
}
