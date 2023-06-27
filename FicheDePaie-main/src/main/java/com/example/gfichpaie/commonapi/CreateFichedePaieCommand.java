package com.example.gfichpaie.commonapi;
import lombok.Getter;

import java.util.Date;
public class CreateFichedePaieCommand extends BaseCommand<String> {
    @Getter private String userName;
    @Getter private Date date;
    @Getter private Double salaireBrut;
    @Getter  private Double impots;
    @Getter  private String idCollaborator;
    @Getter  private Double salaireNet;
    @Getter  private Double chargeSociale;
    @Getter  private Double prime;
    @Getter  private Double tjm;

    public CreateFichedePaieCommand(String id, String userName, Date date, Double salaireBrut, Double impots, String idCollaborator, Double chargeSociale, Double prime) {
        super(id);
        this.userName = userName;
        this.date = date;
        this.salaireBrut = salaireBrut;
        this.impots = impots;
        this.idCollaborator = idCollaborator;
        this.salaireNet = salaireBrut-impots;
        this.chargeSociale = chargeSociale;
        this.prime = prime;
        this.tjm = (prime+salaireNet+chargeSociale)/20;
    }

}
