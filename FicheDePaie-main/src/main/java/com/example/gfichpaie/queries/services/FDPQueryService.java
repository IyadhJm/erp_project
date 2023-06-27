package com.example.gfichpaie.queries.services;

import com.example.gfichpaie.events.FicheDePaieCreatedEvent;
import com.example.gfichpaie.events.FicheDePaieUpdatedEvent;
import com.example.gfichpaie.mappers.FicheDePaieMapper;
import com.example.gfichpaie.queries.entities.FDP;
import com.example.gfichpaie.queries.repositories.FDPRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.axonframework.queryhandling.QueryUpdateEmitter;
import org.springframework.stereotype.Service;
import com.example.gfichpaie.queries.dtos.GetFdpUser;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class FDPQueryService {
    private final FDPRepository fdpRepository;
    private final FicheDePaieMapper ficheDePaieMapper;
    private final QueryUpdateEmitter queryUpdateEmitter;

    @EventHandler
    public void on(FicheDePaieCreatedEvent ficheDePaieCreatedEvent){

        log.info("FDPCreatedEventRecieved");
        FDP fdp=new FDP();
        fdp.setFicheId(ficheDePaieCreatedEvent.getId());
        fdp.setUserName(ficheDePaieCreatedEvent.getUserName());
        fdp.setDate(ficheDePaieCreatedEvent.getDate());
        fdp.setSalaireBrut(ficheDePaieCreatedEvent.getSalaireBrut());
        fdp.setImpots(ficheDePaieCreatedEvent.getImpots());
        fdp.setIdCollaborator(ficheDePaieCreatedEvent.getIdCollaborator());
        fdp.setSalaireNet(ficheDePaieCreatedEvent.getSalaireNet());

        fdp.setChargeSociale(ficheDePaieCreatedEvent.getChargeSociale());
        fdp.setPrime(ficheDePaieCreatedEvent.getPrime());
        fdp.setTjm(ficheDePaieCreatedEvent.getTjm());
        fdp.setStatus(ficheDePaieCreatedEvent.getStatus());

        fdpRepository.save(fdp);
    }
    @EventHandler
    public void on(FicheDePaieUpdatedEvent event){

        log.info("FDPUpdatedEventRecieved");
        FDP fdp=fdpRepository.findById(event.getId()).get();

        fdp.setUserName(event.getUserName());
        fdp.setDate(event.getDate());
        fdp.setSalaireBrut(event.getSalaireBrut());
        fdp.setImpots(event.getImpots());

        fdp.setSalaireNet(event.getSalaireNet());
        fdp.setIdCollaborator(event.getIdCollaborator());
        fdp.setChargeSociale(event.getChargeSociale());
        fdp.setPrime(event.getPrime());
        fdp.setTjm(event.getTjm());

        fdp.setStatus(event.getStatus());

        fdpRepository.save(fdp);
    }

    @QueryHandler
    public List<FDP> on (GetFdpUser query) {
        return fdpRepository.findByUserName(query.getUserName());
    }

    @QueryHandler
    public List<FDP> on(GetAllFDPQueryDTO getAllRequestDTO) {
        return fdpRepository.findAll();
    }
}
