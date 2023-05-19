package com.oga.leave.queries.controllers;

import com.oga.leave.queries.services.EventSourcingService;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Stream;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping(path = "/eventSourcing")
public class LeaveEventSourcingRestController {
    private final EventSourcingService leaveQueryService;
    public LeaveEventSourcingRestController(EventSourcingService leaveQueryService){
        this.leaveQueryService = leaveQueryService;
    }
    @GetMapping(path = "/leaveEvents/{id}")
    public Stream eventsByLeaveId(@PathVariable String id){
        return leaveQueryService.eventsByLeaveId(id).asStream();
    }
}
