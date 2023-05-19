package com.example.Erp.Commands.controllers;

import com.example.Erp.Exception.MissingInputException;
import com.example.Erp.commonApi.commands.BranchCommand.CreateBranchCommand;
import com.example.Erp.commonApi.commands.BranchCommand.UpdateBranchCommand;
import com.example.Erp.commonApi.commands.ModuleCommand.CreateModuleCommand;
import com.example.Erp.commonApi.commands.ModuleCommand.UpdateModuleCommand;
import com.example.Erp.commonApi.commands.ProjectCommand.DeletProjectCommand;
import com.example.Erp.commonApi.dtos.BrancheRequestDTO;
import com.example.Erp.commonApi.dtos.ModuleRequestDTO;
import lombok.AllArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

@CrossOrigin(origins = "*")

@RestController
@RequestMapping(path="/commands/branch")
@AllArgsConstructor
public class BranchCommandController {
    private CommandGateway commandGateway;
    private EventStore eventStore;
    @PostMapping(path= "/create")
    public CompletableFuture<String> createBranch(@RequestBody BrancheRequestDTO requestDTO){
        return commandGateway.send(new CreateBranchCommand(
                UUID.randomUUID().toString(),
                requestDTO.getBranchTitle(),
                requestDTO.getBranchDescription(),
                requestDTO.getUserId(),
                requestDTO.getProject()
        ));
    }




    @ExceptionHandler
    public ResponseEntity<String> exeptionHandler(Exception exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/eventStore/{branchId}")
    public Stream eventStore(@PathVariable String branchId){
        return (Stream)eventStore.readEvents(branchId).asStream() ;

    }

    @CrossOrigin(origins = "*")
    @PutMapping(path = "/update")
    public CompletableFuture<String> updateBranch( @RequestBody BrancheRequestDTO requestDTO)
    { CompletableFuture<String> commandResponse = commandGateway.send(new UpdateBranchCommand(
            requestDTO.getBrancheId(),
            requestDTO.getBranchTitle(),
            requestDTO.getBranchDescription(),
            requestDTO.getUserId(),
            requestDTO.getProject()
    ));
        return commandResponse.exceptionally(ex -> {
            throw new MissingInputException("Failed to update PROJECT: " + ex.getMessage());
        });

    }

    @DeleteMapping(path = "/{branchId}")
    public CompletableFuture<String> deleteProject(@PathVariable String branchId){
        return commandGateway.send((new DeletProjectCommand(branchId)));
    }
}
