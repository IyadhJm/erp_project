package com.example.Erp.query.repositories;

import com.example.Erp.query.entities.Branche;
import com.example.Erp.query.entities.Module;
import com.example.Erp.query.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BranchRepository extends JpaRepository<Branche, String > {
    List<Branche> findByProject(Project project);

}
