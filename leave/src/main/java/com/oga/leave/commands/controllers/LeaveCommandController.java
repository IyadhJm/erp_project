package com.oga.leave.commands.controllers;

import com.oga.leave.commands.dtos.LeaveRequestDTO;
import com.oga.leave.commands.services.LeaveCommandService;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping(path= "/commands")
public class LeaveCommandController {
    private final LeaveCommandService leaveCommandService;
    private EventStore eventStore;

    public LeaveCommandController(LeaveCommandService leaveCommandService, EventStore eventStore) {
        this.leaveCommandService = leaveCommandService;
        this.eventStore = eventStore;
    }
    @PostMapping(path="/leaves/create")
    public CompletableFuture<String> createLeave(@RequestBody LeaveRequestDTO leaveRequestDTO){
        return leaveCommandService.createLeave(leaveRequestDTO);
    }
    @PutMapping(path = "/update")
    public CompletableFuture<String> updateLeave(@RequestBody LeaveRequestDTO leaveRequestDTO){
        return leaveCommandService.updateLeave(leaveRequestDTO);
    }
    @PutMapping(path = "/approve")
    public CompletableFuture<String> approveLeave(@RequestBody LeaveRequestDTO leaveRequestDTO){
        return leaveCommandService.approveLeave(leaveRequestDTO);
    }
    @PutMapping(path = "/reject")
    public CompletableFuture<String> rejectLeave(@RequestBody LeaveRequestDTO leaveRequestDTO){
        return leaveCommandService.rejectLeave(leaveRequestDTO);
    }
    @GetMapping("/eventStore/{leaveId}")
    public Stream eventStore(@PathVariable String leaveId) {
        return (Stream) eventStore.readEvents(leaveId).asStream();
    }



}
