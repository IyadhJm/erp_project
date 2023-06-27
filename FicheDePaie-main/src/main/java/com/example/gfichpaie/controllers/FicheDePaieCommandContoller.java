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
    public CompletableFuture<String> UpdateFDP(@RequestBody FicheDePaieRequestDTO ficheDePaieRequestDTO){
        return ficheDePaieCommandService.updateFDP(ficheDePaieRequestDTO);
    }
    @GetMapping("/eventStore/{FDPId}")
    public Stream eventStore(@PathVariable String FDPId){
        return (Stream)eventStore.readEvents(FDPId).asStream() ;
    }

}
