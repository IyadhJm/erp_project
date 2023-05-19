package org.oga.gestioncras.queries.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.axonframework.queryhandling.QueryUpdateEmitter;
import org.oga.gestioncras.crasProducer.CrasProducer;
import org.oga.gestioncras.events.CRAsCreatedEvent;
import org.oga.gestioncras.events.CRAsUpdatedEvent;
import org.oga.gestioncras.mappers.CRAsMapper;
import org.oga.gestioncras.queries.dtos.GetAllCRAsQueryDTO;
import org.oga.gestioncras.queries.dtos.GetCRAsQueryDTO;
import org.oga.gestioncras.queries.entities.CRAs;
import org.oga.gestioncras.queries.repositories.CRAsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class CRAsQueryService {
    @Autowired
    private CrasProducer crasProducer;
    private final CRAsRepository crAsRepository;
    private final CRAsMapper crAsMapper;
    private final QueryUpdateEmitter queryUpdateEmitter;


    @EventHandler
    public void on(CRAsCreatedEvent crAsCreatedEvent){
        log.info("***");
        log.info("CRAsCreatedEventRecieved");
        CRAs crAs=new CRAs();
        crAs.setCrasId(crAsCreatedEvent.getId());
        crAs.setDescription(crAsCreatedEvent.getDescription());
        crAs.setStartDate(crAsCreatedEvent.getStartDate());
        crAs.setEndDate(crAsCreatedEvent.getEndDate());
        crAs.setIdProject(crAsCreatedEvent.getIdProject());
        crAs.setTimeSpent(crAsCreatedEvent.getTimeSpent());
        crAs.setIdCollaborator(crAsCreatedEvent.getIdCollaborator());
        crAs.setIdResponsible(crAsCreatedEvent.getIdResponsible());
        crAs.setComment(crAsCreatedEvent.getComment());
        crAs.setProductivity(crAsCreatedEvent.getProductivity());
        crAs.setApprove(crAsCreatedEvent.getApprove());
        crAs.setStatus(crAsCreatedEvent.getStatus());
        crAsRepository.save(crAs);
        crasProducer.sendMessage(crAsCreatedEvent);
    }
    @EventHandler
    public void on(CRAsUpdatedEvent event){
        log.info("***");
        log.info("CRAsUpdatedEventRecieved");
          CRAs crAs = crAsRepository.findById(event.getId()).get();
          crAs.setTimeSpent(event.getTimeSpent());
          crAs.setDescription(event.getDescription());
          crAs.setStartDate(event.getStartDate());
          crAs.setEndDate(event.getEndDate());
          crAs.setIdProject(event.getIdProject());
          crAs.setIdResponsible(event.getIdResponsible());
          crAs.setIdCollaborator(event.getIdCollaborator());
          crAs.setComment(event.getComment());
          crAs.setProductivity(event.getProductivity());
          crAs.setApprove(event.getApprove());
          crAs.setStatus(event.getStatus());
          crAsRepository.save(crAs);
    }
    @QueryHandler
    public CRAs on (GetCRAsQueryDTO query) {
        return crAsRepository.findById(query.getId()).get();
    }

    @QueryHandler
    public List<CRAs> on(GetAllCRAsQueryDTO getAllCRAsRequestDTO) {
        return crAsRepository.findAll();
    }
}
