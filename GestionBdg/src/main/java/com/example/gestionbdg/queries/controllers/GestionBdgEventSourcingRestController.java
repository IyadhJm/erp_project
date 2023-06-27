package com.example.gestionbdg.queries.controllers;



import com.example.gestionbdg.queries.services.EventSourcingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Stream;
@RestController
@RequestMapping(path="/eventSourcing")
public class GestionBdgEventSourcingRestController {
    private final EventSourcingService gbdgQueryService;

    public GestionBdgEventSourcingRestController(EventSourcingService gbdgQueryService) {
        this.gbdgQueryService = gbdgQueryService;
    }


    @GetMapping(path = "/CjmEvents/{id}")
    public Stream eventsByFDPId(@PathVariable String id){
        return gbdgQueryService.eventsByGbdgId(id).asStream();
    }
}
