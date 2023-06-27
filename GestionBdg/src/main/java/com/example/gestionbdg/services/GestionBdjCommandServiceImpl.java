package com.example.gestionbdg.services;

import com.example.gestionbdg.commonapi.CreateCjmCommand;
import com.example.gestionbdg.commonapi.UpdateCjmCommand;
import com.example.gestionbdg.dtos.GestionBdgDTO;
import com.example.gestionbdg.queries.repositories.GestionBdjRepository;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class GestionBdjCommandServiceImpl implements GestionBdjCommandService {
@Autowired
    private CommandGateway commandGateway;
@Autowired
    private GestionBdjRepository gestionBdjRepository;

    @Override
    public CompletableFuture<String> createGBdj(GestionBdgDTO gestionBdgDTO) {
        return commandGateway.send(new CreateCjmCommand(
                        UUID.randomUUID().toString(),
                gestionBdgDTO.getCollaborator(),
                gestionBdgDTO.getTjm(),
                gestionBdgDTO.getCjm(),
                gestionBdgDTO.getTask(),
                gestionBdgDTO.getDayNumber(),
                gestionBdgDTO.getProject()));
    }
@Override
    public CompletableFuture<String> updateGBdj(GestionBdgDTO gestionBdgDTO)
    { CompletableFuture<String> commandResponse = commandGateway.send(new UpdateCjmCommand(
          gestionBdgDTO.getBdgId(),
            gestionBdgDTO.getCollaborator(),
            gestionBdgDTO.getTjm(),
            gestionBdgDTO.getCjm(),
            gestionBdgDTO.getTask(),
            gestionBdgDTO.getDayNumber(),
            gestionBdgDTO.getProject()));
        return commandResponse.exceptionally(ex -> {
            throw new RuntimeException("Failed to update gbj: " + ex.getMessage());
        });
    }

    @Override
    public CompletableFuture<String> deleteGbj(String bdgId) {
        try {
            gestionBdjRepository.deleteById(bdgId);
            return CompletableFuture.completedFuture("La fiche de paie a été supprimée avec succès.");
        } catch (Exception e) {
            CompletableFuture<String> future = new CompletableFuture<>();
            future.completeExceptionally(e);
            return future;
        }
    }


}
