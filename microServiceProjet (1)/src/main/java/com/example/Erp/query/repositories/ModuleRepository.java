package com.example.Erp.query.repositories;

import com.example.Erp.commonApi.enums.tacheStatus;
import com.example.Erp.query.entities.Module;
import com.example.Erp.query.entities.Project;
import com.example.Erp.query.entities.Tache;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ModuleRepository extends JpaRepository<Module, String > {
    List<Module> findByProject(Project project);

}
