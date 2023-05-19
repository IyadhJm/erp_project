package org.oga.gestioncras.commands.controllers;

import org.axonframework.eventsourcing.eventstore.EventStore;
import org.oga.gestioncras.commands.dtos.CRAsRequestDTO;
import org.oga.gestioncras.commands.exceptions.FalseDateException;
import org.oga.gestioncras.commands.services.CRAsCommandService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping(path="/commands/CRAs")
public class CRAsCommandContoller {
    private CRAsCommandService crAsCommandService;
    private EventStore eventStore;

    public CRAsCommandContoller(CRAsCommandService crAsCommandService, EventStore eventStore) {
        this.crAsCommandService = crAsCommandService;
        this.eventStore = eventStore;
    }

    @PostMapping(path = "/create")
    public CompletableFuture<String> createCRAs(@RequestBody CRAsRequestDTO crAsRequestDTO){
        return crAsCommandService.createCRAs(crAsRequestDTO);
    }
    @PutMapping(path = "/update")
    public CompletableFuture<String> UpdateCRAs(@RequestBody CRAsRequestDTO request){
        return crAsCommandService.updateCRAs(request);
    }
    @GetMapping("/eventStore/{crasId}")
    public Stream eventStore(@PathVariable String crasId){
        return (Stream)eventStore.readEvents(crasId).asStream() ;
    }

    @ExceptionHandler(FalseDateException.class)
    public ResponseEntity<String> exceptionHandler(FalseDateException exception){
        return new ResponseEntity<String>(exception.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
