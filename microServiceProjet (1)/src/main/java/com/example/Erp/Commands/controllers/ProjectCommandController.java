package com.example.Erp.Commands.controllers;

import com.example.Erp.Exception.MissingInputException;
import com.example.Erp.commonApi.ProducerKafka.ProjectProducer;
import com.example.Erp.commonApi.commands.ProjectCommand.CreateProjectCommand;
import com.example.Erp.commonApi.commands.ProjectCommand.DeletProjectCommand;
import com.example.Erp.commonApi.commands.ProjectCommand.UpdateProjectCommand;
import com.example.Erp.commonApi.dtos.ProjectRequestDTO;
import lombok.AllArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;
@CrossOrigin(origins = "*")

@RestController
@RequestMapping(path="/commands/Project")
@AllArgsConstructor
public class ProjectCommandController {
    private CommandGateway commandGateway;
    private EventStore eventStore;


    @PostMapping(path= "/create")
    public CompletableFuture<String> createProject(@RequestBody ProjectRequestDTO requestDTO){

      return commandGateway.send(new CreateProjectCommand(
                    UUID.randomUUID().toString(),
                    requestDTO.getProjectTitle(),
                    requestDTO.getProjectDescription(),
                    requestDTO.getCollaborators(),
                    requestDTO.getStartDate(),
                    requestDTO.getEndDate()
            ));


}


    @ExceptionHandler
    public ResponseEntity<String> exeptionHandler(Exception exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/eventStore/{ProjectId}")
    public Stream eventStore(@PathVariable String ProjectId){
        return (Stream)eventStore.readEvents(ProjectId).asStream() ;

    }


    @PutMapping(path = "/update")
    public CompletableFuture<String> updateProject(@RequestBody ProjectRequestDTO request)
    { CompletableFuture<String> commandResponse = commandGateway.send(new UpdateProjectCommand(
            request.getId(),
            request.getProjectTitle(),
            request.getProjectDescription(),
            request.getCollaborators(),
            request.getStartDate(),
            request.getEndDate()
    ));
        return commandResponse.exceptionally(ex -> {
            throw new MissingInputException("Failed to update PROJECT: " + ex.getMessage());
        });

    }

    @DeleteMapping(path = "/{projectId}")
    public CompletableFuture<String> deleteProject(@PathVariable String projectId){
        return commandGateway.send((new DeletProjectCommand(projectId)));
    }
}
