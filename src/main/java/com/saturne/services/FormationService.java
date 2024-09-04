package com.saturne.services;

import com.saturne.dto.FormationDTO;
import com.saturne.dto.FormationMapper;
import com.saturne.entities.Formation;
import com.saturne.dto.ThemeDTO;
import com.saturne.entities.Theme;
import com.saturne.dto.SessionDTO;
import com.saturne.entities.Session;
import com.saturne.dto.ChapitreDTO;
import com.saturne.entities.Chapitre;
import com.saturne.exceptions.TrainingNotFoundException;
import com.saturne.repositories.FormationRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@ApplicationScoped
public class FormationService {

  //injection de dépendance
  @Inject
  private FormationRepository formationRepo;

  @Inject
  private FormationMapper mapper;

  //Ajouter une formation
  @Transactional
  public FormationDTO addFormation(FormationDTO fDTO) {
    //        if (f.getTitref().toLowerCase().contains("java")) {
    //            f.getThemes().add(new Theme("Java"));
    ////            f.getThemes().add(new Theme("POO"));
    //        } else if (f.getTitref().toLowerCase().contains("web")) {
    //            f.getThemes().add(new Theme("Web"));
    //        } else {
    //            f.getThemes().add(new Theme("UNDEFINED"));
    //        }
    Formation formation = mapper.toEntity(fDTO);
    formationRepo.persist(formation);
    return mapper.toDTO(formation);
  }

  @Transactional
  public List<FormationDTO> findAllFormations() {
    return formationRepo.listAll()
            .stream()
            .map(this::toDTO)
            .collect(Collectors.toList());
  }

  @Transactional
  public FormationDTO updateFormation(long id, FormationDTO fDTO) {
    Formation formation = mapper.toEntity(fDTO);
    formationRepo.persist(formation);
    return mapper.toDTO(formation);
  }

  @Transactional
  public FormationDTO findFormationById(long id) {
    Formation formation = formationRepo.findFormationByIdFormation(id)
            .orElseThrow(() -> new TrainingNotFoundException("Formation not found with id: " + id));
    return mapper.toDTO(formation);
  }

  @Transactional
  public List<FormationDTO> findByKeyword(String keyword) {
    return formationRepo
      .findAll()
      .stream()
      .filter(f ->
        ((f.getReference()).toUpperCase()).contains(keyword.toUpperCase()) ||
        ((f.getTitref()).toUpperCase()).contains(keyword.toUpperCase()) ||
        ((f.getLieu()).toUpperCase()).contains(keyword.toUpperCase()) ||
        ((f.getObjectif()).toUpperCase()).contains(keyword.toUpperCase()) ||
        ((f.getPrerequis()).toUpperCase()).contains(keyword.toUpperCase()) ||
        ((f.getProgrammeDetaille()).toUpperCase()).contains(keyword.toUpperCase()) ||
        ((f.getPublicVise()).toUpperCase()).contains(keyword.toUpperCase())
      )
            .map(this::toDTO)
            /*|| ((f.getThemes().foreach()????.contains(keyword.toLowerCase())*/
      .collect(Collectors.toList());
    //.orElseThrow(() -> new TrainingNotFoundException("Training by keyword " + keyword + "was not found"));
  }

  @Transactional
  public FormationDTO findFormationByReference(String ref) {
    Formation formation = formationRepo
            .findFormationByReference(ref)
            .orElseThrow(() -> new TrainingNotFoundException("Training by reference " + ref + "was not found"));
    return mapper.toDTO(formation);
  }
  @Transactional
  public void deleteFormation(long id) {
    formationRepo.deleteFormationByIdFormation(id);
  }


  // Méthode de conversion d'une entité Formation en FormationDTO
  private FormationDTO toDTO(Formation formation) {
    // Conversion de l'entité Formation en DTO
    return new FormationDTO(
            formation.getIdFormation(),
            formation.getReference(),
            formation.getTitref(),
            formation.getLieu(),
            formation.getInterFormation(),
            formation.getDuree(),
            formation.getPrerequis(),
            formation.getObjectif(),
            formation.getPublicVise(),
            formation.getProgrammeDetaille(),
            convertThemesToDTOs(formation.getThemes()),
            convertChapitresToDTOs(formation.getChapitres()),
            convertSessionsToDTOs(formation.getSessions())
            );
  }
  // Méthode de conversion d'un DTO en entité Formation
  public Formation toEntity(FormationDTO dto) {
    Formation formation = new Formation();
    formation.setIdFormation(dto.getIdFormation());
    formation.setReference(dto.getReference());
    formation.setTitref(dto.getTitref());
    formation.setLieu(dto.getLieu());
    formation.setInterFormation(dto.getInterFormation());
    formation.setDuree(dto.getDuree());
    formation.setPrerequis(dto.getPrerequis());
    formation.setObjectif(dto.getObjectif());
    formation.setPublicVise(dto.getPublicVise());
    formation.setProgrammeDetaille(dto.getProgrammeDetaille());

    // Vous pouvez également mapper les ensembles de DTO vers les entités correspondantes
    formation.setThemes(dto.getThemes().stream().map(this::mapThemeDTOToEntity).collect(Collectors.toSet()));
    formation.setChapitres(dto.getChapitres().stream().map(this::mapChapitreDTOToEntity).collect(Collectors.toSet()));
    formation.setSessions(dto.getSessions().stream().map(this::mapSessionDTOToEntity).collect(Collectors.toSet()));

    return formation;
    }

  // Méthodes de conversion auxiliaires pour les sous-objets (Themes, Chapitres, Sessions, etc.)
  private Theme mapThemeDTOToEntity(ThemeDTO dto) {
    Theme theme = new Theme();
    theme.setIdTheme(dto.getIdTheme());
    theme.setNomTheme(dto.getNomTheme());
    // autres propriétés
    return theme;
  }

  private Chapitre mapChapitreDTOToEntity(ChapitreDTO dto) {
    Chapitre chapitre = new Chapitre();
    chapitre.setIdChap(dto.getIdChap());
    chapitre.setNomChapitre(dto.getNomChapitre());
    return chapitre;
  }

  private Session mapSessionDTOToEntity(SessionDTO dto) {
    Session session = new Session();
    session.setIdSession(dto.getIdSession());
    session.setDateDebut(dto.getDateDebut());
    session.setDateFin(dto.getDateFin());
    session.setLieu(dto.getLieu());
    session.setPrix(dto.getPrix());
    return session;
  }

  // Convert Set<Theme> to Set<ThemeDTO>
  private Set<ThemeDTO> convertThemesToDTOs(Set<Theme> themes) {
    return themes.stream()
            .map(this::toThemeDTO)
            .collect(Collectors.toSet());
  }

  // Convert Set<Chapitre> to Set<ChapitreDTO>
  private Set<ChapitreDTO> convertChapitresToDTOs(Set<Chapitre> chapitres) {
    return chapitres.stream()
            .map(this::toChapitreDTO)
            .collect(Collectors.toSet());
  }

  // Convert Set<Session> to Set<SessionDTO>
  private Set<SessionDTO> convertSessionsToDTOs(Set<Session> sessions) {
    return sessions.stream()
            .map(this::toSessionDTO)
            .collect(Collectors.toSet());
  }

  // Convert Theme to ThemeDTO
  private ThemeDTO toThemeDTO(Theme theme) {
    return new ThemeDTO(theme.getIdTheme(), theme.getNomTheme());
  }

  // Convert Chapitre to ChapitreDTO
  private ChapitreDTO toChapitreDTO(Chapitre chapitre) {
    return new ChapitreDTO(chapitre.getIdChap(), chapitre.getNomChapitre());
  }

  // Convert Session to SessionDTO
  private SessionDTO toSessionDTO(Session session) {
    return new SessionDTO(session.getIdSession(), session.getDateDebut(), session.getDateFin(), session.getLieu());
  }

}
