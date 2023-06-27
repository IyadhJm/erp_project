package com.example.gfichpaie.services;

import com.example.gfichpaie.commonapi.CreateFichedePaieCommand;
import com.example.gfichpaie.commonapi.UpdateFicheDePaieCommand;
import com.example.gfichpaie.dtos.FicheDePaieRequestDTO;
import com.example.gfichpaie.queries.repositories.FDPRepository;
import org.axonframework.commandhandling.gateway.CommandGateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class FicheDePaieCommandServiceImpl implements FicheDePaieCommandService {
@Autowired
    private CommandGateway commandGateway;
@Autowired
private FDPRepository fdpRepository;

    @Override
    public CompletableFuture<String> createFdP(FicheDePaieRequestDTO ficheDePaieRequestDTO) {
        return commandGateway.send(new CreateFichedePaieCommand(
                        UUID.randomUUID().toString(),

                ficheDePaieRequestDTO.getUserName(),
                ficheDePaieRequestDTO.getDate(),

                ficheDePaieRequestDTO.getSalaireBrut(),
                ficheDePaieRequestDTO.getImpots(),
                ficheDePaieRequestDTO.getCollaboratorId(),

                ficheDePaieRequestDTO.getChargeSociale(),
                ficheDePaieRequestDTO.getPrime()));


       }
@Override
    public CompletableFuture<String> updateFDP(FicheDePaieRequestDTO ficheDePaieRequestDTO)
    { CompletableFuture<String> commandResponse = commandGateway.send(new UpdateFicheDePaieCommand(
            ficheDePaieRequestDTO.getFicheId(),
            ficheDePaieRequestDTO.getUserName(),
            ficheDePaieRequestDTO.getDate(),
            ficheDePaieRequestDTO.getSalaireBrut(),
            ficheDePaieRequestDTO.getImpots(),
            ficheDePaieRequestDTO.getCollaboratorId(),
            ficheDePaieRequestDTO.getSalaireNet(),
            ficheDePaieRequestDTO.getChargeSociale(),
            ficheDePaieRequestDTO.getPrime(),
            ficheDePaieRequestDTO.getTjm()));

        return commandResponse.exceptionally(ex -> {
            throw new RuntimeException("Failed to update FdP: " + ex.getMessage());
        });

    }

    public CompletableFuture<String> deleteFDP(String ficheId) {
        try {
            fdpRepository.deleteById(ficheId);
            return CompletableFuture.completedFuture("La fiche de paie a été supprimée avec succès.");
        } catch (Exception e) {
            CompletableFuture<String> future = new CompletableFuture<>();
            future.completeExceptionally(e);
            return future;
        }
    }




}
