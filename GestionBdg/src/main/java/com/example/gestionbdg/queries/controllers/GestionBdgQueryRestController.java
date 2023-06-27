package com.example.gestionbdg.queries.controllers;


import com.example.gestionbdg.queries.dtos.GetAllCJMQueryDTO;
import com.example.gestionbdg.queries.dtos.GetCJMQueryDTO;
import com.example.gestionbdg.queries.dtos.GetCjmTache;
import com.example.gestionbdg.queries.entities.GestionBdjEntity;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path="/query")
public class GestionBdgQueryRestController {
    private QueryGateway queryGateway;

    public GestionBdgQueryRestController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }
    @GetMapping(path="/CJM/{id}")
    public GestionBdjEntity getCjm(@PathVariable String id){
        return queryGateway.query(new GetCJMQueryDTO(id),ResponseTypes.instanceOf(GestionBdjEntity.class)).join();
    }

    @GetMapping(path="/{userName}")
    public GestionBdjEntity getByUserName(@PathVariable String userName){
        return queryGateway.query(new GetCjmTache(userName),ResponseTypes.instanceOf(GestionBdjEntity.class)).join();
    }

    @GetMapping(path="/AllCjm")
    public List<GestionBdjEntity> getAll(){
        List<GestionBdjEntity> reponse=queryGateway.query(new GetAllCJMQueryDTO(), ResponseTypes.multipleInstancesOf(GestionBdjEntity.class)).join();
        return reponse;
    }

}
