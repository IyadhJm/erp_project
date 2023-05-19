package org.oga.gestioncras.commands.services;

import org.oga.gestioncras.commands.dtos.CRAsRequestDTO;

import java.util.concurrent.CompletableFuture;

public interface CRAsCommandService {
    CompletableFuture<String> createCRAs(CRAsRequestDTO crAsRequestDTO);
    CompletableFuture<String> updateCRAs(CRAsRequestDTO crAsRequestDTO);
}
