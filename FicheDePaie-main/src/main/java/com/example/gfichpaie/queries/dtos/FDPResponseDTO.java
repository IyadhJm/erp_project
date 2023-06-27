package com.example.gfichpaie.queries.dtos;

import com.example.gfichpaie.enums.FicheDePaieStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class FDPResponseDTO {
 @Getter private  String ficheId;
 @Getter private String userName;
 @Getter private Date date;
 @Getter private Double salaireBrut;
 @Getter  private Double impots;
 @Getter  private String idCollaborator;
 @Getter  private Double salaireNet;
 @Getter  private Double chargeSociale;
 @Getter  private Double prime;
 @Getter  private Double tjm;
 private FicheDePaieStatus status;
}
