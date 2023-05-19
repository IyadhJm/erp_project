package com.example.Erp.query.service;

import com.example.Erp.commonApi.event.ModuleEvent.ModuleCreatedEvent;
import com.example.Erp.commonApi.event.ModuleEvent.ModuleUpdatedEvent;
import com.example.Erp.commonApi.queries.ModuleQueries.GetAllModuleQuery;
import com.example.Erp.commonApi.queries.ModuleQueries.GetByProject;
import com.example.Erp.query.entities.Module;
import com.example.Erp.query.entities.Project;
import com.example.Erp.query.entities.Tache;
import com.example.Erp.query.repositories.ModuleRepository;
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

public class ModuleServiceHanbdler {

    private ModuleRepository moduleRepository;
    private  ProjectRepository projectRepository;

    @EventHandler
    public void on(ModuleCreatedEvent event){
        log.info("******");
        log.info("moduleCreatedEventRecieved");
        Module module = new Module();
        module.setModuleId(event.getId());
        module.setModuleTitle(event.getModuleTitle());
        module.setModuleDescription(event.getModuleDescription());
        module.setStatus(event.getStatus());
        module.setUserId(event.getUserId());
        Optional<Project> projectOptional = projectRepository.findById(event.getProject());
        if (projectOptional.isPresent()) {
            Project project = projectOptional.get();
            log.info("project: {}", project);
            module.setProject(project);
        } else {
            log.warn("Project not found for id: {}", event.getProject());
        }
        moduleRepository.save(module);

    }
    @EventHandler
    public void on(ModuleUpdatedEvent event){
        log.info("******");
        log.info("ModuleUpdatedEventRecieved");
        Module module = moduleRepository.findById(event.getId()).orElseThrow(() -> new IllegalArgumentException("Invalid module ID"));
        module.setModuleTitle(event.getModuleTitle());
        module.setModuleDescription(event.getModuleDescription());
        module.setStatus(event.getStatus());
        module.setUserId(event.getUserId());
        if(event.getProject() != null) {
            Optional<Project> projectOptional = projectRepository.findById(event.getProject());
            if (projectOptional.isPresent()) {
                Project project = projectOptional.get();
                log.info("project: {}", project);
                module.setProject(project);
            } else {
                log.warn("Project not found for id: {}", event.getProject());
            }
        } else {
            log.warn("Project ID is null");
        }
        moduleRepository.save(module);
    }
    @QueryHandler
    public List<Module> on (GetAllModuleQuery query){
        return moduleRepository.findAll();
    }

    @QueryHandler
    public List<Module> handle(GetByProject query) {
        return moduleRepository.findByProject(query.getProject());
    }
}
