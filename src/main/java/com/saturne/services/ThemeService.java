package com.saturne.services;

import com.saturne.dto.FormationMapper;
import com.saturne.dto.ThemeDTO;
import com.saturne.dto.ThemeMapper;
import com.saturne.entities.Theme;
import com.saturne.exceptions.TrainingNotFoundException;
import com.saturne.repositories.ThemeRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class ThemeService {

  @Inject
  private ThemeRepository themeRepo;

  @Inject
  private ThemeMapper mapper;

  @Transactional
  public Theme addTheme(Theme t) {
    themeRepo.persist(t);
    return t;
  }

  @Transactional
  public List<Theme> findAllThemes() {
    return themeRepo.listAll();
  }

  @Transactional
  public ThemeDTO updateTheme(ThemeDTO themeDTO) {
    Theme theme = mapper.toEntity(themeDTO);
    themeRepo.persist(theme);
    return mapper.toDTO(theme);
  }

  @Transactional
  public ThemeDTO findThemeById(long id) {
    Theme theme = themeRepo.findThemeByIdTheme(id).orElseThrow(() -> new TrainingNotFoundException("Theme by id " + id + " was not found"));
    return mapper.toDTO(theme);
  }

  @Transactional
  public void deleteTheme(long id) {
    themeRepo.deleteThemeByIdTheme(id);
  }
}
