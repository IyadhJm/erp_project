package com.example.gestionbdg.aggregates;

import com.example.gestionbdg.commonapi.CreateCjmCommand;
import com.example.gestionbdg.commonapi.UpdateCjmCommand;
import com.example.gestionbdg.enums.GbdgStatus;
import com.example.gestionbdg.events.CjmCreatedEvent;
import com.example.gestionbdg.events.CjmUpdatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.List;


@Aggregate
@Slf4j
public class GestionBdj {
    @AggregateIdentifier
    private String bdgId;
    private List<String> userName;
    private String collaboratorId;
    private Double tjm;
    private Double cjm;
    private GbdgStatus status;

    public GestionBdj() {
    }

    @CommandHandler
    public GestionBdj(CreateCjmCommand createCjmCommand) {
        if((createCjmCommand.getCjm()==null) || (createCjmCommand.getUserName()==null) || (createCjmCommand.getCollaboratorId()==null) ){
            throw new RuntimeException("Input should not be null");
        }
        log.info("CreateFDPCommand Reveived");

        AggregateLifecycle.apply(
                new CjmCreatedEvent(
                        createCjmCommand.getId(),
                        createCjmCommand.getUserName(),
                        createCjmCommand.getCollaboratorId(),
                        createCjmCommand.getTjm(),
                        createCjmCommand.getCjm(),
                        GbdgStatus.CREATED));

                        }
        @EventSourcingHandler
        public void on(CjmCreatedEvent event){
            log.info("FDPCreatedEvent Occured");
            this.bdgId= event.getId();
            this.userName=event.getUserName();
            this.collaboratorId= event.getCollaboratorId();
            this.cjm= event.getCjm();
            this.status=event.getStatus();
        }
        @CommandHandler
        public void FicheDePaieAggregate(UpdateCjmCommand command){
            if((command.getUserName()==null) || (command.getCollaboratorId()==null) || (command.getCjm()==null)  ){
                throw new RuntimeException("Input should not be null");
            }
        AggregateLifecycle.apply(new CjmUpdatedEvent(
                command.getBdgId(),
                command.getUserName(),
                command.getCollaboratorId(),
                command.getTjm(),
                command.getCjm(),
                GbdgStatus.UPDATED
        ));
        }
    @EventSourcingHandler
    public void on(CjmUpdatedEvent event){
        this.bdgId= event.getId();
        this.userName=event.getUserName();
        this.collaboratorId= event.getCollaboratorId();
        this.cjm= event.getCjm();
        this.status=event.getStatus();
    }


}
