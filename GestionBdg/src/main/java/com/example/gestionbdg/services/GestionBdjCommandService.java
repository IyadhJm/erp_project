package com.example.gestionbdg.services;



import com.example.gestionbdg.dtos.GestionBdgDTO;

import java.util.concurrent.CompletableFuture;

public interface GestionBdjCommandService {
    CompletableFuture<String> createGBdj(GestionBdgDTO gestionBdgDTO);
    CompletableFuture<String> updateGBdj(GestionBdgDTO gestionBdgDTO);
}
