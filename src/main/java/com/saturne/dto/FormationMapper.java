package com.saturne.dto;

import com.saturne.entities.Formation;
import jakarta.enterprise.context.ApplicationScoped;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "cdi")
@ApplicationScoped
public interface FormationMapper {

    // --- DAO to DTO
    @Mapping(target= "themes")
    @Mapping(target= "chapitres")
    @Mapping(target = "sessions", source = "sessions")
    FormationDTO toDTO(Formation formation);

    @Mapping(target = "themes", source = "themes")
    @Mapping(target = "chapitres", source = "chapitres")
    @Mapping(target = "sessions", source = "sessions")
    Formation toEntity(FormationDTO formationDTO);

}
