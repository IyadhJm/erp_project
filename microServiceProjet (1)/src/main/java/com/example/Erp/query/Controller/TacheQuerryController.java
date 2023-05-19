package com.example.Erp.query.Controller;

import com.example.Erp.commonApi.enums.tacheStatus;
import com.example.Erp.commonApi.queries.ProjectQueries.GetById;
import com.example.Erp.commonApi.queries.TacheQueries.GetAllTacheQuery;
import com.example.Erp.commonApi.queries.TacheQueries.GetTacheByProject;
import com.example.Erp.query.entities.Project;
import com.example.Erp.query.entities.Tache;
import com.example.Erp.query.repositories.ProjectRepository;
import com.example.Erp.query.service.TacheServiceHanbdler;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/query/taches")
@AllArgsConstructor
@Slf4j


public class TacheQuerryController {
    private QueryGateway queryGateway;
    private TacheServiceHanbdler tacheServiceHanbdler;
    private ProjectRepository projectRepository;
    @GetMapping()
    public List<Tache> tacheList(){
        List<Tache> response = queryGateway.query((new GetAllTacheQuery()), ResponseTypes.multipleInstancesOf(Tache.class)).join();
        return response;
    };

 /*   @GetMapping("/{id}")
    public Tache tacheById(@PathVariable String id){
        var getbyid = new GetById(id);
        Tache response = queryGateway.query(getbyid, ResponseTypes.instanceOf(Tache.class)).join();
        return response;
    };*/



    @GetMapping("/{projectId}")
    public List<Tache> tacheByProject(@PathVariable String projectId, @RequestParam tacheStatus status) {
        Optional<Project> projectOptional = projectRepository.findById(projectId);
        if (projectOptional.isPresent()) {
            Project project = projectOptional.get();
            GetTacheByProject query = new GetTacheByProject(project, status);
            List<Tache> response = queryGateway.query(query, ResponseTypes.multipleInstancesOf(Tache.class)).join();
            return response;
        } else {
            throw new EntityNotFoundException("Project not found with id: " + projectId);
        }
    }


}
