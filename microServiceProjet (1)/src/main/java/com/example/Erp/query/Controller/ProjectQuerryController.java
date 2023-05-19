package com.example.Erp.query.Controller;

import com.example.Erp.commonApi.enums.tacheStatus;
import com.example.Erp.commonApi.queries.ProjectQueries.GetAllProjectQuery;
import com.example.Erp.commonApi.queries.ProjectQueries.GetById;
import com.example.Erp.commonApi.queries.TacheQueries.GetTacheByProject;
import com.example.Erp.query.entities.Project;
import com.example.Erp.query.entities.Tache;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/query/projects")
@AllArgsConstructor
@Slf4j


public class ProjectQuerryController {
    private QueryGateway queryGateway;



    @GetMapping()
    public List<Project> projectList() {
        List<Project> response = queryGateway.query((new GetAllProjectQuery()), ResponseTypes.multipleInstancesOf(Project.class)).join();
        return response;
    }

    ;

    @GetMapping("/{collaborators}")
    public List<Project> projectbyId(@PathVariable String collaborators) {
        var getbyid = new GetById(collaborators);
        List<Project> response = queryGateway.query(getbyid, ResponseTypes.multipleInstancesOf(Project.class)).join();
        return response;
    }
}
