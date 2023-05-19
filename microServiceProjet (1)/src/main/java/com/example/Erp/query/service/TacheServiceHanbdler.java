package com.example.Erp.query.service;
import com.example.Erp.commonApi.ProducerKafka.TaskProducer;
import com.example.Erp.commonApi.enums.tacheStatus;
import com.example.Erp.commonApi.event.ProjectEvent.ProjectCreatedEvent;
import com.example.Erp.commonApi.event.TacheEvent.*;
import com.example.Erp.commonApi.queries.TacheQueries.GetAllTacheQuery;
import com.example.Erp.commonApi.queries.TacheQueries.GetTacheByProject;
import com.example.Erp.query.entities.Project;
import com.example.Erp.query.entities.Tache;
import com.example.Erp.query.repositories.ProjectRepository;
import com.example.Erp.query.repositories.TacheRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;

import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.springframework.web.servlet.mvc.method.annotation.SseEmitter.event;


@Service
@AllArgsConstructor
@Slf4j

public class TacheServiceHanbdler {

@Autowired
private TaskProducer taskProducer;
    private KafkaTemplate<String, TacheTestedEvent> kafkaTemplate;
    private TacheRepository tacheRepository;
    private  ProjectRepository projectRepository;

    @EventHandler
    public void on(TacheCreatedEvent event) throws JsonProcessingException {
        log.info("******");
        log.info("tacheCreatedEventRecieved");
        Tache tache = new Tache();
        tache.setTacheId(event.getId());
        tache.setTacheTitle(event.getTacheTitle());
        tache.setTacheDescription(event.getTacheDescription());
        tache.setStatus(event.getStatus());
        tache.setCollaborator(event.getCollaborator());
        tache.setResponsible(event.getResponsible());
        tache.setStartDate(event.getStartDate());
        tache.setEndDate(event.getEndDate());
        if(event.getProject() != null) {
            Optional<Project> projectOptional = projectRepository.findById(event.getProject());
            if (projectOptional.isPresent()) {
                Project project = projectOptional.get();
                //log.info("project: {}", project);
                tache.setProject(project);
            } else {
                log.warn("Project not found for id: {}", event.getProject());
            }
        } else {
            log.warn("Project ID is null");
        }
        tacheRepository.save(tache);

       // kafkaTemplate.send("notificationTopic","New tacheCreated for collaborateur:"+ event.getUserId());




    }


   @EventHandler
    public void on(TacheUpdatedEvent event){
        log.info("******");
        log.info("TacheUpdatedEventRecieved");
       Tache tache = tacheRepository.findById(event.getId()).orElseThrow(() -> new IllegalArgumentException("Invalid Tache ID"));
        tache.setTacheTitle(event.getTacheTitle());
        tache.setTacheDescription(event.getTacheDescription());
        tache.setStatus(event.getStatus());
       tache.setCollaborator(event.getCollaborator());
       tache.setResponsible(event.getResponsible());
       tache.setStartDate(event.getStartDate());
        tache.setEndDate(event.getEndDate());
       if(event.getProject() != null) {
           Optional<Project> projectOptional = projectRepository.findById(event.getProject());
           if (projectOptional.isPresent()) {
               Project project = projectOptional.get();
               log.info("project: {}", project);
               tache.setProject(project);
           } else {
               log.warn("Project not found for id: {}", event.getProject());
           }
       } else {
           log.warn("Project ID is null");
       }

       tacheRepository.save(tache);

   }
    @EventHandler
    public void on(TachePrgressedEvent event){
        log.info("******");
        log.info("TacheProgressed event received");
        if (event.getId() == null) {
            log.warn("Invalid Tache ID: null");
            return;
        }
        Tache tache = tacheRepository.findById(event.getId()).orElseThrow(() -> new IllegalArgumentException("Invalid Tache ID"));
        tache.setTacheTitle(event.getTacheTitle());
        tache.setTacheDescription(event.getTacheDescription());
        tache.setStatus(event.getStatus());
        tache.setCollaborator(event.getCollaborator());
        tache.setResponsible(event.getResponsible());
        tache.setStartDate(event.getStartDate());
        tache.setEndDate(event.getEndDate());
        if(event.getProject() != null) {
            Optional<Project> projectOptional = projectRepository.findById(event.getProject());
            if (projectOptional.isPresent()) {
                Project project = projectOptional.get();
                //log.info("project: {}", project);
                tache.setProject(project);
            } else {
                log.warn("Project not found for id: {}", event.getProject());
            }
        } else {
            log.warn("Project ID is null");
        }
        tacheRepository.save(tache);

    }


    @EventHandler
    public void on(TacheTestedEvent event){
        log.info("******");
        log.info("TacheTestedEventRecieved");
        Tache tache = tacheRepository.findById(event.getId()).orElseThrow(() -> new IllegalArgumentException("Invalid Tache ID"));
        tache.setTacheTitle(event.getTacheTitle());
        tache.setTacheDescription(event.getTacheDescription());
        tache.setStatus(event.getStatus());
        tache.setCollaborator(event.getCollaborator());
        tache.setResponsible(event.getResponsible());
        tache.setStartDate(event.getStartDate());
        tache.setEndDate(event.getEndDate());
        if(event.getProject() != null) {
            Optional<Project> projectOptional = projectRepository.findById(event.getProject());
            if (projectOptional.isPresent()) {
                Project project = projectOptional.get();
                log.info("project: {}", project);
                tache.setProject(project);
            } else {
                log.warn("Project not found for id: {}", event.getProject());
            }
        } else {
            log.warn("Project ID is null");
        }
        tacheRepository.save(tache);
        taskProducer.sendMessage(event);

    }

    @EventHandler
    public void on(TacheComplitedEvent event){
        log.info("******");
        log.info("TacheCompletedEventRecieved");
        Tache tache = tacheRepository.findById(event.getId()).orElseThrow(() -> new IllegalArgumentException("Invalid Tache ID"));
        tache.setTacheTitle(event.getTacheTitle());
        tache.setTacheDescription(event.getTacheDescription());
        tache.setStatus(event.getStatus());
        tache.setCollaborator(event.getCollaborator());
        tache.setResponsible(event.getResponsible());
        tache.setStartDate(event.getStartDate());
        tache.setEndDate(event.getEndDate());
        if(event.getProject() != null) {
            Optional<Project> projectOptional = projectRepository.findById(event.getProject());
            if (projectOptional.isPresent()) {
                Project project = projectOptional.get();
                //log.info("project: {}", project);
                tache.setProject(project);
            } else {
                log.warn("Project not found for id: {}", event.getProject());
            }
        } else {
            log.warn("Project ID is null");
        }
        tacheRepository.save(tache);

    }

    @QueryHandler
   public List<Tache> handle(GetTacheByProject query) {
       tacheStatus status = query.getStatus();
       return tacheRepository.findTacheByProjectAndStatus(query.getProject(), status);
   }

    @QueryHandler
    public List<Tache> on (GetAllTacheQuery query){
        return tacheRepository.findAll();
    }




}

