package com.example.gestionbdg.controllers;


import com.example.gestionbdg.dtos.GestionBdgDTO;
import com.example.gestionbdg.services.GestionBdjCommandService;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path="/commands/Gbdg")
public class GestionBdjCommandContoller {
    private GestionBdjCommandService gestionBdjCommandService;
    private EventStore eventStore;

    public GestionBdjCommandContoller(GestionBdjCommandService gestionBdjCommandService, EventStore eventStore) {
        this.gestionBdjCommandService = gestionBdjCommandService;
        this.eventStore = eventStore;
    }

    @PostMapping(path = "/create")
    public CompletableFuture<String> createCJm(@RequestBody GestionBdgDTO gestionBdgDTO){
        return gestionBdjCommandService.createGBdj(gestionBdgDTO);
    }
    @PutMapping(path = "/update")
    public CompletableFuture<String> updateCjm(@RequestBody GestionBdgDTO gestionBdgDTO){
        return gestionBdjCommandService.updateGBdj(gestionBdgDTO);
    }
    @GetMapping("/eventStore/{CjmId}")
    public Stream eventStore(@PathVariable String cjmId){
        return eventStore.readEvents(cjmId).asStream() ;
    }
    @DeleteMapping(path = "/delete/{bdjId}")
    public ResponseEntity<String> deleteFicheDePaie(@PathVariable String bdjId) {
        try {
            gestionBdjCommandService.deleteGbj(bdjId);
            return ResponseEntity.ok("La fiche de paie a été supprimée avec succès.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Une erreur s'est produite lors de la suppression de la fiche de paie.");
        }
    }

}
