package com.example.Erp.Commands.controllers;
import com.example.Erp.Exception.MissingInputException;
import com.example.Erp.commonApi.ProducerKafka.TaskProducer;
import com.example.Erp.commonApi.commands.TacheCommand.*;
import com.example.Erp.commonApi.dtos.TacheRequestDTO;
import com.example.Erp.commonApi.event.TacheEvent.TacheTestedEvent;
import lombok.AllArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path="/commands/tache")
@AllArgsConstructor
public class TacheCommandController {


    private CommandGateway commandGateway;
    private EventStore eventStore;
    @PostMapping(path= "/create")
    public CompletableFuture<String> createTache(@RequestBody TacheRequestDTO requestDTO){

        return commandGateway.send(new CreateTacheCommand(
                UUID.randomUUID().toString(),
                requestDTO.getTacheTitle(),
                requestDTO.getTacheDescription(),
                requestDTO.getCollaborator(),
                requestDTO.getResponsible(),
                requestDTO.getProject(),
                requestDTO.getStartDate(),
                requestDTO.getEndDate()

        ));


    }


    @ExceptionHandler
    public ResponseEntity<String> exeptionHandler(Exception exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/eventStore/{TacheId}")
    public Stream eventStore(@PathVariable String TacheId){
        return (Stream)eventStore.readEvents(TacheId).asStream() ;

    }


    @PutMapping(path = "/update/{tachId}")
    public CompletableFuture<String> updateTache(@PathVariable(name = "tacheId", required = false) String id, @RequestBody TacheRequestDTO requestDTO)
    { CompletableFuture<String> commandResponse = commandGateway.send(new UpdateTacheCommand(
            id,
            requestDTO.getTacheTitle(),
            requestDTO.getTacheDescription(),
            requestDTO.getCollaborator(),
            requestDTO.getResponsible(),
            requestDTO.getProject(),
            requestDTO.getStartDate(),
            requestDTO.getEndDate()

    ));
        return commandResponse.exceptionally(ex -> {
            throw new MissingInputException("Failed to update tache: " + ex.getMessage());
        });

    }
    @PutMapping(path = "/Progress")
    public CompletableFuture<String> Started( @RequestBody TacheRequestDTO requestDTO)
    { CompletableFuture<String> commandResponse = commandGateway.send(new StartedTacheCommand(
            requestDTO.getTacheId(),
            requestDTO.getTacheTitle(),
            requestDTO.getTacheDescription(),
            requestDTO.getCollaborator(),
            requestDTO.getResponsible(),
            requestDTO.getProject(),
            requestDTO.getStartDate(),
            requestDTO.getEndDate()

    ));
        return commandResponse.exceptionally(ex -> {
            throw new MissingInputException("Failed to Started tache: " + ex.getMessage());
        });

    }
    @PutMapping(path = "/test")
    public CompletableFuture<String> Tested( @RequestBody TacheRequestDTO requestDTO)
    { CompletableFuture<String> commandResponse = commandGateway.send(new TesteTacheCommand(
            requestDTO.getTacheId(),

            requestDTO.getTacheTitle(),
            requestDTO.getTacheDescription(),
            requestDTO.getCollaborator(),
            requestDTO.getResponsible(),
            requestDTO.getProject(),
            requestDTO.getStartDate(),
            requestDTO.getEndDate()

    ));

        return commandResponse.exceptionally(ex -> {
            throw new MissingInputException("Failed to Started  test tache: " + ex.getMessage());
        });

    }
    @PutMapping(path = "/Complete")
    public CompletableFuture<String> Complete( @RequestBody TacheRequestDTO requestDTO)
    { CompletableFuture<String> commandResponse = commandGateway.send(new CompleteTacheCommand(
            requestDTO.getTacheId(),
            requestDTO.getTacheTitle(),
            requestDTO.getTacheDescription(),
            requestDTO.getCollaborator(),
            requestDTO.getResponsible(),
            requestDTO.getProject(),
            requestDTO.getStartDate(),
            requestDTO.getEndDate()

    ));
        return commandResponse.exceptionally(ex -> {
            throw new MissingInputException("Failed to Started  test tache: " + ex.getMessage());
        });

    }

    @DeleteMapping(path = "/{tacheId}")
    public CompletableFuture<String> deleteTache(@PathVariable String tacheId){
        return commandGateway.send((new DeleteTacheCommand(tacheId)));
    }
}
