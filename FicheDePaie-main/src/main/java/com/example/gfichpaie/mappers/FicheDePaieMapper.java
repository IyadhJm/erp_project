package com.example.gfichpaie.mappers;


import com.example.gfichpaie.aggregates.FicheDePaie;
import com.example.gfichpaie.queries.dtos.FDPResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel="spring")
public interface FicheDePaieMapper {
    FDPResponseDTO ficheDePaieToFicheDePaieDTO(FicheDePaie ficheDePaie);
}
