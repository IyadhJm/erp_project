package com.example.gestionbdg.queries.dtos;

import com.example.gestionbdg.queries.entities.GestionBdjEntity;
import com.example.gestionbdg.queries.repositories.GestionBdjRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetCJMQueryDTO {
    private String id;
}
