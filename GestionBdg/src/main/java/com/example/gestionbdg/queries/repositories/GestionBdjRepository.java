package com.example.gestionbdg.queries.repositories;



import com.example.gestionbdg.queries.entities.GestionBdjEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface GestionBdjRepository extends JpaRepository<GestionBdjEntity,String> {
    GestionBdjEntity findByBdgId(String bdgId);
}
