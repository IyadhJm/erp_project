package org.oga.gestioncras.commands.services;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.oga.gestioncras.commands.commonapi.CreateCRAsCommand;
import org.oga.gestioncras.commands.commonapi.UpdateCRAsCommand;
import org.oga.gestioncras.commands.dtos.CRAsRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.oga.gestioncras.commands.commonapi.CreateCRAsCommand;

import java.time.Duration;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class CRAsCommandServiceImpl implements CRAsCommandService {
@Autowired
    private CommandGateway commandGateway;
    private CreateCRAsCommand command;
    @Override
    public CompletableFuture<String> createCRAs(CRAsRequestDTO crAsRequestDTO) {
        if (crAsRequestDTO.getStartDate() != null && crAsRequestDTO.getEndDate() != null) {
            long timeDiffInDays = ChronoUnit.DAYS.between(crAsRequestDTO.getStartDate(), crAsRequestDTO.getEndDate())+1;
            crAsRequestDTO.setTimeSpent(String.valueOf(timeDiffInDays)+" Jours");
        }
            return commandGateway.send(new CreateCRAsCommand(
                    UUID.randomUUID().toString(),
                    crAsRequestDTO.getTimeSpent(),
                    crAsRequestDTO.getDescription(),
                    crAsRequestDTO.getStartDate(),
                    crAsRequestDTO.getEndDate(),
                    crAsRequestDTO.getIdProject(),
                    crAsRequestDTO.getIdResponsible(),
                    crAsRequestDTO.getIdCollaborator(),
                    crAsRequestDTO.getComment(),
                    crAsRequestDTO.getProductivity(),
                    crAsRequestDTO.getApprove()));
        }

@Override
    public CompletableFuture<String> updateCRAs(CRAsRequestDTO crAsRequestDTO)
    {
        if (crAsRequestDTO.getStartDate() != null && crAsRequestDTO.getEndDate() != null) {
        long timeDiffInDays = ChronoUnit.DAYS.between(crAsRequestDTO.getStartDate(), crAsRequestDTO.getEndDate())+1;
        crAsRequestDTO.setTimeSpent(String.valueOf(timeDiffInDays)+" Jours");
    }
        CompletableFuture<String> commandResponse = commandGateway.send(new UpdateCRAsCommand(
            crAsRequestDTO.getCrasId(),
            crAsRequestDTO.getTimeSpent(),
            crAsRequestDTO.getDescription(),
            crAsRequestDTO.getStartDate(),
            crAsRequestDTO.getEndDate(),
            crAsRequestDTO.getIdProject(),
            crAsRequestDTO.getIdCollaborator(),
            crAsRequestDTO.getIdResponsible(),
            crAsRequestDTO.getComment(),
            crAsRequestDTO.getProductivity(),
            crAsRequestDTO.getApprove()
    ));
        return commandResponse.exceptionally(ex -> {
            throw new RuntimeException("Failed to update CRAs: " + ex.getMessage());
        });

    }
}
