package org.oga.gestioncras.queries.controllers;

import org.oga.gestioncras.queries.services.EventSourcingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.stream.Stream;
@RestController
@RequestMapping(path="/eventSourcing")
public class CRAsEventSourcingRestController {
    private final EventSourcingService crasQueryService;

    public CRAsEventSourcingRestController(EventSourcingService crasQueryService) {
        this.crasQueryService = crasQueryService;
    }


    @GetMapping(path = "/crasEvents/{id}")
    public Stream eventsByCRAsId(@PathVariable String id){
        return crasQueryService.eventsByCRAsId(id).asStream();
    }
}
