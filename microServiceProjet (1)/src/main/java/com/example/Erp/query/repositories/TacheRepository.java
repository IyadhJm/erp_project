package com.example.Erp.query.repositories;

import com.example.Erp.commonApi.enums.tacheStatus;
import com.example.Erp.query.entities.Project;
import com.example.Erp.query.entities.Tache;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TacheRepository extends JpaRepository<Tache, String > {
    List<Tache> findTacheByProjectAndStatus(Project project, tacheStatus status);

}
