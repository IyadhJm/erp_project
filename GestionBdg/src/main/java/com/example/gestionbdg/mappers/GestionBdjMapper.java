package com.example.gestionbdg.mappers;




import com.example.gestionbdg.aggregates.GestionBdj;
import com.example.gestionbdg.dtos.GestionBdgDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel="spring")
public interface GestionBdjMapper {
    GestionBdgDTO GESTION_BDGToFicheDePaieDTO(GestionBdj gestionBdj);
}
