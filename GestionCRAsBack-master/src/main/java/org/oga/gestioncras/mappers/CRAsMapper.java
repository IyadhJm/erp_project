package org.oga.gestioncras.mappers;


import org.mapstruct.Mapper;
import org.oga.gestioncras.queries.dtos.CRAsResponseDTO;
import org.oga.gestioncras.queries.entities.CRAs;

@Mapper(componentModel="spring")
public interface CRAsMapper {
    CRAsResponseDTO CRAsToCRAsDTO(CRAs crAs);
}
