package com.example.gfichpaie.queries.controllers;

import com.example.gfichpaie.queries.services.EventSourcingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Stream;
@RestController
@RequestMapping(path="/eventSourcing")
public class FDPEventSourcingRestController {
    private final EventSourcingService fdpQueryService;

    public FDPEventSourcingRestController(EventSourcingService fdpQueryService) {
        this.fdpQueryService = fdpQueryService;
    }


    @GetMapping(path = "/FDPEvents/{id}")
    public Stream eventsByFDPId(@PathVariable String id){
        return fdpQueryService.eventsByFDPId(id).asStream();
    }
}
