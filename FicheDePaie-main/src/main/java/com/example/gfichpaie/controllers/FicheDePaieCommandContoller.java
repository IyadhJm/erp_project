package com.example.gfichpaie.controllers;

import com.example.gfichpaie.dtos.FicheDePaieRequestDTO;
import com.example.gfichpaie.services.FicheDePaieCommandService;
import org.axonframework.eventsourcing.eventstore.EventStore;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path="/commands/fdp")
public class FicheDePaieCommandContoller {
    private FicheDePaieCommandService ficheDePaieCommandService;
    private EventStore eventStore;

    public FicheDePaieCommandContoller(FicheDePaieCommandService ficheDePaieCommandService, EventStore eventStore) {
        this.ficheDePaieCommandService = ficheDePaieCommandService;
        this.eventStore = eventStore;
    }

    @PostMapping(path = "/create")
    public CompletableFuture<String> createFDP(@RequestBody FicheDePaieRequestDTO ficheDePaieRequestDTO){
        return ficheDePaieCommandService.createFdP(ficheDePaieRequestDTO);
    }
    @PutMapping(path = "/update")
    public CompletableFuture<String> updateFDP(@RequestBody FicheDePaieRequestDTO ficheDePaieRequestDTO){
        return ficheDePaieCommandService.updateFDP(ficheDePaieRequestDTO);
    }
    @DeleteMapping(path = "/delete/{ficheId}")
    public ResponseEntity<String> deleteFicheDePaie(@PathVariable String ficheId) {
        try {
            ficheDePaieCommandService.deleteFDP(ficheId);
            return ResponseEntity.ok("La fiche de paie a été supprimée avec succès.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Une erreur s'est produite lors de la suppression de la fiche de paie.");
        }
    }



    @GetMapping("/eventStore/{FDPId}")
    public Stream eventStore(@PathVariable String fdpId){
        return eventStore.readEvents(fdpId).asStream() ;
    }

}
