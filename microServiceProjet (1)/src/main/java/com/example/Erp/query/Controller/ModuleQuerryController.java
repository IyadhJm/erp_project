package com.example.Erp.query.Controller;

import com.example.Erp.commonApi.queries.ModuleQueries.GetAllModuleQuery;
import com.example.Erp.commonApi.queries.ModuleQueries.GetByProject;
import com.example.Erp.commonApi.queries.ProjectQueries.GetById;
import com.example.Erp.query.entities.Module;
import com.example.Erp.query.entities.Project;
import com.example.Erp.query.repositories.ProjectRepository;
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
@RequestMapping("/query/modules")
@AllArgsConstructor
@Slf4j


public class ModuleQuerryController {
    private QueryGateway queryGateway;
    private ProjectRepository projectRepository;

    @GetMapping()
    public List<Module> moduleList(){
        List<Module> response = queryGateway.query((new GetAllModuleQuery()), ResponseTypes.multipleInstancesOf(Module.class)).join();
        return response;
    };

   /* @GetMapping("/{id}")
    public Module modulebyId(@PathVariable String id){
        var getbyid = new GetById(id);
        Module response = queryGateway.query(getbyid, ResponseTypes.instanceOf(Module.class)).join();
        return response;
    };*/

    @GetMapping("/{projectId}")
    public List<Module> ModuleByProject(@PathVariable String projectId) {
        Optional<Project> projectOptional = projectRepository.findById(projectId);
        if (projectOptional.isPresent()) {
            Project project = projectOptional.get();
            GetByProject query = new GetByProject(project);
            List<Module> response = queryGateway.query(query, ResponseTypes.multipleInstancesOf(Module.class)).join();
            return response;
        } else {
            throw new EntityNotFoundException("Project not found with id: " + projectId);
        }
    }
}
