package com.saturne.services;

import com.saturne.dto.ThemeDTO;
import com.saturne.entities.Formation;
import com.saturne.entities.Theme;
import com.saturne.exceptions.TrainingNotFoundException;
import com.saturne.repositories.ThemeRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class ThemeService {

  @Inject
  private ThemeRepository themeRepo;

  @Transactional
  public ThemeDTO addTheme(ThemeDTO tDTO) {

    Theme theme = toEntity(tDTO);
    themeRepo.persist(theme);
    return toDTO(theme);
  }

  @Transactional
  public List<ThemeDTO> findAllThemes() {

    return themeRepo.listAll().stream()
            .map(this::toDTO)
            .collect(Collectors.toList());
  }

  @Transactional
  public ThemeDTO updateTheme(ThemeDTO themeDTO) {
    Theme theme = themeRepo.findThemeByIdTheme(themeDTO.getIdTheme())
            .orElseThrow(() -> new TrainingNotFoundException("Theme not found with id: " + themeDTO.getIdTheme()));
    theme.setNomTheme(themeDTO.getNomTheme());
    themeRepo.persist(theme);
    return toDTO(theme);
  }

  @Transactional
  public ThemeDTO findThemeById(long id) {
    Theme theme = themeRepo.findThemeByIdTheme(id).orElseThrow(() -> new TrainingNotFoundException("Theme by id " + id + " was not found"));
    return toDTO(theme);
  }

  @Transactional
  public void deleteTheme(long id) {
    themeRepo.deleteThemeByIdTheme(id);
  }

  // Conversion d'un DTO en entité
  private Theme toEntity(ThemeDTO dto) {
    Theme theme = new Theme();
    theme.setIdTheme(dto.getIdTheme());
    theme.setNomTheme(dto.getNomTheme());
    return theme;
  }

  // Conversion d'une entité en DTO
  private ThemeDTO toDTO(Theme theme) {
    return new ThemeDTO(theme.getIdTheme(), theme.getNomTheme());
  }


}
