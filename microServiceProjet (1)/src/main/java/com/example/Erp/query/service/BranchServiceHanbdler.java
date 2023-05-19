package com.example.Erp.query.service;

import com.example.Erp.commonApi.event.BranchEvent.BranchCreatedEvent;
import com.example.Erp.commonApi.event.BranchEvent.BranchUpdatedEvent;
import com.example.Erp.commonApi.queries.BranchQueries.GetAllBranchQuery;
import com.example.Erp.commonApi.queries.BranchQueries.GetByProject;
import com.example.Erp.query.entities.Branche;
import com.example.Erp.query.entities.Project;
import com.example.Erp.query.repositories.BranchRepository;
import com.example.Erp.query.repositories.ProjectRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j

public class BranchServiceHanbdler {

    private BranchRepository branchRepository;
    private  ProjectRepository projectRepository;

    @EventHandler
    public void on(BranchCreatedEvent event){
        log.info("******");
        log.info("branchCreatedEventRecieved");
        Branche branche = new Branche();
        branche.setBrancheId(event.getId());
        branche.setBranchTitle(event.getBranchTitle());
        branche.setBranchDescription(event.getBranchDescription());
        branche.setStatus(event.getStatus());
        branche.setUserId(event.getUserId());
        if(event.getProject() != null) {
            Optional<Project> projectOptional = projectRepository.findById(event.getProject());
            if (projectOptional.isPresent()) {
                Project project = projectOptional.get();
                log.info("project: {}", project);
                branche.setProject(project);
            } else {
                log.warn("Project not found for id: {}", event.getProject());
            }
        } else {
            log.warn("Project ID is null");
        }
        branchRepository.save(branche);

    }
    @EventHandler
    public void on(BranchUpdatedEvent event){
        log.info("******");
        log.info("BranchUpdatedEventRecieved");
        Branche branche = branchRepository.findById(event.getId()).orElseThrow(() -> new IllegalArgumentException("Invalid module ID"));
        branche.setBranchTitle(event.getBranchTitle());
        branche.setBranchDescription(event.getBranchDescription());
        branche.setStatus(event.getStatus());
        branche.setUserId(event.getUserId());
        if(event.getProject() != null) {
            Optional<Project> projectOptional = projectRepository.findById(event.getProject());
            if (projectOptional.isPresent()) {
                Project project = projectOptional.get();
                log.info("project: {}", project);
                branche.setProject(project);
            } else {
                log.warn("Project not found for id: {}", event.getProject());
            }
        } else {
            log.warn("Project ID is null");
        }
        branchRepository.save(branche);
    }
    @QueryHandler
    public List<Branche> on (GetAllBranchQuery query){
        return branchRepository.findAll();
    }

    @QueryHandler
    public List<Branche> handle(GetByProject query) {
        return branchRepository.findByProject(query.getProject());
    }
}
