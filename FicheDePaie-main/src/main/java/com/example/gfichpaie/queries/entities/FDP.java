package com.example.gfichpaie.queries.entities;

import com.example.gfichpaie.enums.FicheDePaieStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
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
