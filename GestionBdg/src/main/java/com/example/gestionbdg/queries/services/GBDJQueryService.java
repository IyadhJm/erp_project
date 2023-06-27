package com.example.gestionbdg.queries.services;

import com.example.gestionbdg.events.CjmCreatedEvent;
import com.example.gestionbdg.events.CjmUpdatedEvent;
import com.example.gestionbdg.mappers.GestionBdjMapper;
import com.example.gestionbdg.queries.dtos.GetAllCJMQueryDTO;
import com.example.gestionbdg.queries.dtos.GetCJMQueryDTO;
import com.example.gestionbdg.queries.entities.GestionBdjEntity;
import com.example.gestionbdg.queries.repositories.GestionBdjRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.axonframework.queryhandling.QueryUpdateEmitter;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class GBDJQueryService {
    private final GestionBdjRepository gestionBdjRepository;
    private final GestionBdjMapper gestionBdjMapper;
    private final QueryUpdateEmitter queryUpdateEmitter;
    @EventHandler
    public void on(CjmCreatedEvent cjmCreatedEvent){
        log.info("FDPCreatedEventRecieved");
        GestionBdjEntity gestionBdjEntity=new GestionBdjEntity();
        gestionBdjEntity.setBdgId(cjmCreatedEvent.getId());
        gestionBdjEntity.setUserName(cjmCreatedEvent.getUserName());
        gestionBdjEntity.setCollaboratorId(cjmCreatedEvent.getCollaboratorId());
        gestionBdjEntity.setTjm(cjmCreatedEvent.getTjm());
        gestionBdjEntity.setCjm(cjmCreatedEvent.getCjm());
        gestionBdjEntity.setStatus(cjmCreatedEvent.getStatus());
        gestionBdjRepository.save(gestionBdjEntity);
    }
    @EventHandler
    public void on(CjmUpdatedEvent event){
        log.info("FDPUpdatedEventRecieved");
        GestionBdjEntity gestionBdjEntity=gestionBdjRepository.findById(event.getId()).get();
        gestionBdjEntity.setUserName(event.getUserName());
        gestionBdjEntity.setCollaboratorId(event.getCollaboratorId());
        gestionBdjEntity.setTjm(event.getTjm());
        gestionBdjEntity.setCjm(event.getCjm());
        gestionBdjEntity.setStatus(event.getStatus());
        gestionBdjRepository.save(gestionBdjEntity);
    }

    @QueryHandler
    public GestionBdjEntity on (GetCJMQueryDTO query) {
        return gestionBdjRepository.findById(query.getId()).get();
    }
    @QueryHandler
    public List<GestionBdjEntity> on(GetAllCJMQueryDTO getAllRequestDTO) {
        return gestionBdjRepository.findAll();
    }
}
