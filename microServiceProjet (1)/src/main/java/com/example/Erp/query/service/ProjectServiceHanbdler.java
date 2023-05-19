package com.example.Erp.query.service;

import com.example.Erp.commonApi.ProducerKafka.ProjectProducer;
import com.example.Erp.commonApi.event.ProjectEvent.ProjectCreatedEvent;
import com.example.Erp.commonApi.event.ProjectEvent.ProjectUpdatedEvent;
import com.example.Erp.commonApi.queries.ProjectQueries.GetAllProjectQuery;
import com.example.Erp.commonApi.queries.ProjectQueries.GetById;
import com.example.Erp.query.entities.Project;
import com.example.Erp.query.repositories.ProjectRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j

public class ProjectServiceHanbdler {
    @Autowired
    ProjectProducer projectProducer;
    private NewTopic topic;
private KafkaTemplate<String,ProjectCreatedEvent> kafkaTemplate;
    private ProjectRepository projectRepository;

    @EventHandler
    public void on(ProjectCreatedEvent event){
        log.info("******");
        log.info("ProjectCreatedEventRecieved");
        Project project = new Project();
        project.setId(event.getId());
        project.setProjectTitle(event.getProjectTitle());
        project.setProjectDescription(event.getProjectDescription());
        project.setStartDate(event.getStartDate().toString());
        project.setEndDate(event.getEndDate().toString());
        project.setStatus(event.getStatus());
        project.setCollaborators(event.getCollaborators());
        projectRepository.save(project);
        projectProducer.sendMessage(event);

    }
    @EventHandler
    public void on(ProjectUpdatedEvent event){
        log.info("******");
        log.info("ProjectUpdatedEventRecieved");
        Project project = projectRepository.findById(event.getId()).get();
        project.setProjectTitle(event.getProjectTitle());
        project.setProjectDescription(event.getProjectDescription());
        project.setStartDate(event.getStartDate().toString());
        project.setEndDate(event.getEndDate().toString());
        project.setStatus(event.getStatus());
        project.setCollaborators(event.getCollaborators());
        projectRepository.save(project);
    }
    @QueryHandler
    public List<Project> on (GetAllProjectQuery query){
        return projectRepository.findAll();
    }
    @QueryHandler
    public List<Project> on(GetById query) {
        return projectRepository.findByCollaboratorsContaining(query.getCollaborators());
    }



}
