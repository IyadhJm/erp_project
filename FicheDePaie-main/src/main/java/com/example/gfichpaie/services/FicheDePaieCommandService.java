package com.example.gfichpaie.services;

import com.example.gfichpaie.dtos.FicheDePaieRequestDTO;


import java.util.concurrent.CompletableFuture;

public interface FicheDePaieCommandService {
    CompletableFuture<String> createFdP(FicheDePaieRequestDTO ficheDePaieRequestDTO);
    CompletableFuture<String> updateFDP(FicheDePaieRequestDTO ficheDePaieRequestDTO);
}
