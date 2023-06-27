package com.example.gfichpaie.events;

import com.example.gfichpaie.enums.FicheDePaieStatus;
import lombok.Getter;

import java.util.Date;


public class FicheDePaieCreatedEvent extends BaseEvent <String> {
    @Getter private String userName;
    @Getter private Date date;
    @Getter private Double salaireBrut;
    @Getter  private Double impots;
    @Getter  private String idCollaborator;
    @Getter  private Double salaireNet;
    @Getter  private Double chargeSociale;
    @Getter  private Double prime;
    @Getter  private Double tjm;
    @Getter   private FicheDePaieStatus status;

    public FicheDePaieCreatedEvent(String id, String userName, Date date, Double salaireBrut, Double impots, String idCollaborator, Double salaireNet, Double chargeSociale, Double prime, Double tjm, FicheDePaieStatus status) {
        super(id);
        this.userName = userName;
        this.date = date;
        this.salaireBrut = salaireBrut;
        this.impots = impots;
        this.idCollaborator = idCollaborator;
        this.salaireNet = salaireNet;
        this.chargeSociale = chargeSociale;
        this.prime = prime;
        this.tjm = tjm;
        this.status = status;
    }

}
