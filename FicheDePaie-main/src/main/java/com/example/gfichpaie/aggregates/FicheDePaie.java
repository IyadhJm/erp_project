package com.example.gfichpaie.aggregates;


import com.example.gfichpaie.commonapi.CreateFichedePaieCommand;
import com.example.gfichpaie.commonapi.DeleteFDPCommand;
import com.example.gfichpaie.commonapi.UpdateFicheDePaieCommand;
import com.example.gfichpaie.enums.FicheDePaieStatus;

import com.example.gfichpaie.events.FDPDeletedEvent;
import com.example.gfichpaie.events.FicheDePaieCreatedEvent;
import com.example.gfichpaie.events.FicheDePaieUpdatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;


import java.util.Date;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
@Slf4j
public class FicheDePaie {
    @AggregateIdentifier
    private String ficheId;
    public FicheDePaie() {
    }

    @CommandHandler
    public FicheDePaie(CreateFichedePaieCommand createFichedePaieCommand) {
        if((createFichedePaieCommand.getSalaireNet()==null) || (createFichedePaieCommand.getChargeSociale()==null) || (createFichedePaieCommand.getPrime()==null || (createFichedePaieCommand.getSalaireBrut()==null) || (createFichedePaieCommand.getDate()==null)|| (createFichedePaieCommand.getImpots()==null)|| (createFichedePaieCommand.getUserName()==null))  ){
            throw new RuntimeException("Input should not be null");
        }
        log.info("CreateFDPCommand Reveived");

        apply(
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
        }
        @CommandHandler
        public void ficheDePaieAggregate(UpdateFicheDePaieCommand command){
            if((command.getIdCollaborator()==null) || (command.getSalaireNet()==null) || (command.getChargeSociale()==null) || (command.getPrime()==null || (command.getSalaireBrut()==null) || (command.getDate()==null)|| (command.getImpots()==null)|| (command.getUserName()==null))  ){
                throw new RuntimeException("Input should not be null");
            }
        apply(new FicheDePaieUpdatedEvent(
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
    }
    @CommandHandler
    public void ficheDePaieAggregate(DeleteFDPCommand command) {
        apply(new FDPDeletedEvent(command.getFicheId()));
    }

    @EventSourcingHandler
    public void on(FDPDeletedEvent event) {
        this.ficheId = event.getFicheId();
    }


}
