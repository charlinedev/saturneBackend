package com.saturne.dto;

import com.saturne.dto.ThemeDTO;
import com.saturne.entities.Theme;
import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface ThemeMapper {

    ThemeDTO toDTO(Theme theme);

    Theme toEntity(ThemeDTO themeDTO);
}