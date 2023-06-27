package com.example.gfichpaie.queries.controllers;

import com.example.gfichpaie.queries.dtos.GetFDPQueryDTO;
import com.example.gfichpaie.queries.dtos.GetFdpUser;
import com.example.gfichpaie.queries.entities.FDP;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;

import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path="/query")
public class FDPQueryRestController {
    private QueryGateway queryGateway;

    public FDPQueryRestController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }
    @GetMapping(path="/FDP/{id}")
    public FDP getFDP(@PathVariable String id){
        return queryGateway.query(new GetFDPQueryDTO(id),ResponseTypes.instanceOf(FDP.class)).join();
    }

    @GetMapping(path="/{userName}")
    public List<FDP> getFDPByUserName(@PathVariable String userName){
        return queryGateway.query(new GetFdpUser(userName),ResponseTypes.multipleInstancesOf(FDP.class)).join();
    }

    @GetMapping(path="/AllFDP")
    public List<FDP> getAll(){
        return queryGateway.query(new GetAllFDPQueryDTO(), ResponseTypes.multipleInstancesOf(FDP.class)).join();
    }

}
