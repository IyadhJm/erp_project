package com.example.gfichpaie.aggregates;


import com.example.gfichpaie.commonapi.CreateFichedePaieCommand;
import com.example.gfichpaie.commonapi.UpdateFicheDePaieCommand;
import com.example.gfichpaie.dtos.FicheDePaieRequestDTO;
import com.example.gfichpaie.enums.FicheDePaieStatus;
import com.example.gfichpaie.events.FicheDePaieCreatedEvent;
import com.example.gfichpaie.events.FicheDePaieUpdatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;


import java.time.LocalDate;
import java.util.Date;

@Aggregate
@Slf4j
public class FicheDePaie {
    @AggregateIdentifier
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



    private FicheDePaieStatus status;

    public FicheDePaie() {
    }

    @CommandHandler
    public FicheDePaie(CreateFichedePaieCommand createFichedePaieCommand) {
        if((createFichedePaieCommand.getIdCollaborator()==null) || (createFichedePaieCommand.getSalaireNet()==null) || (createFichedePaieCommand.getChargeSociale()==null) || (createFichedePaieCommand.getPrime()==null || (createFichedePaieCommand.getSalaireBrut()==null) || (createFichedePaieCommand.getDate()==null)|| (createFichedePaieCommand.getImpots()==null)|| (createFichedePaieCommand.getUserName()==null))  ){
            throw new RuntimeException("Input should not be null");
        }
        log.info("CreateFDPCommand Reveived");

        AggregateLifecycle.apply(
                new FicheDePaieCreatedEvent(
                                                                        createFichedePaieCommand.getId(),
                                                                        createFichedePaieCommand.getUserName(),
                        createFichedePaieCommand.getDate(),
                                                                        createFichedePaieCommand.getSalaireBrut(),
                                                                        createFichedePaieCommand.getImpots(),
                                                                        createFichedePaieCommand.getIdCollaborator(),
                        createFichedePaieCommand.getSalaireNet(),



                                                                        createFichedePaieCommand.getChargeSociale(),
                                                                        createFichedePaieCommand.getPrime(),
                                                                        createFichedePaieCommand.getTjm(),



                                                                    FicheDePaieStatus.CREATED));
    }
        @EventSourcingHandler
        public void on(FicheDePaieCreatedEvent event){
            log.info("FDPCreatedEvent Occured");
            this.ficheId= event.getId();
            this.idCollaborator=event.getIdCollaborator();
            this.salaireBrut= event.getSalaireBrut();
            this.impots= event.getImpots();
            this.salaireNet= event.getSalaireNet();
            this.date= event.getDate();
            this.userName= event.getUserName();
            this.chargeSociale= event.getChargeSociale();
            this.prime= event.getPrime();
            this.tjm= event.getTjm();
            this.status=event.getStatus();
        }
        @CommandHandler
        public void FicheDePaieAggregate(UpdateFicheDePaieCommand command){
            if((command.getIdCollaborator()==null) || (command.getSalaireNet()==null) || (command.getChargeSociale()==null) || (command.getPrime()==null || (command.getSalaireBrut()==null) || (command.getDate()==null)|| (command.getImpots()==null)|| (command.getUserName()==null))  ){
                throw new RuntimeException("Input should not be null");
            }
        AggregateLifecycle.apply(new FicheDePaieUpdatedEvent(
                command.getFicheId(),
                command.getUserName(),
                command.getDate(),
                command.getSalaireBrut(),
                command.getImpots(),
               command.getIdCollaborator(),

                command.getSalaireNet(),



                command.getChargeSociale(),
                command.getPrime(),
                command.getTjm(),
                FicheDePaieStatus.UPDATED
        ));
        }
    @EventSourcingHandler
    public void on(FicheDePaieUpdatedEvent event){
        this.ficheId= event.getId();
        this.idCollaborator=event.getIdCollaborator();
        this.salaireBrut= event.getSalaireBrut();
        this.salaireNet= event.getSalaireNet();
        this.impots= event.getImpots();
        this.date= event.getDate();
        this.userName= event.getUserName();
        this.chargeSociale= event.getChargeSociale();
        this.prime= event.getPrime();
        this.tjm= event.getTjm();
        this.status=event.getStatus();
    }


}
