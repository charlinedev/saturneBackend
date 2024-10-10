package com.saturne.services;

import com.saturne.dto.FormationDTO;
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
import jakarta.inject.Named;

import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;


import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@ApplicationScoped
public class FormationService {

  //injection de dépendance
  @Inject
  private FormationRepository formationRepo;

  //Ajouter une formation
  @Transactional
  public FormationDTO addFormation(FormationDTO fDTO) {

    Formation formation = toEntity(fDTO);
    if (formation == null) {
      throw new IllegalStateException("L'entité Formation est null après la conversion.");
    }
    // Vérifier si l'entité existe déjà
    if (formation.getIdFormation() != 0) {
      Formation existingFormation = formationRepo.findById(formation.getIdFormation());
      if (existingFormation != null) {
        return toDTO(existingFormation);
      }
    }

    // Persist la formation en premier
    if (fDTO.getIdFormation() == 0 ) {
      formationRepo.persist(formation);
      formationRepo.flush();
    }
    // Assurer que la formation a bien un ID généré
    if (formation.getIdFormation() == 0) {
      throw new IllegalStateException("La formation n'a pas été persistée correctement.");
    }

    // Associer les sessions / thèmes / chapitres (si présents) à la formation persistée
    if (fDTO.getSessions() != null) {
      Set<Session> sessions = fDTO.getSessions().stream()
              .map(sessionDTO -> {
                Session session = new Session();
                session.setIdSession(sessionDTO.getIdSession());
                session.setFormation(formation);
                return session;
              })
              .collect(Collectors.toSet());
      formation.setSessions(sessions);
    }
    if (fDTO.getThemes() != null) {
      Set<Theme> themes = fDTO.getThemes().stream()
              .map(themeDTO -> {
                Theme theme = new Theme();
                theme.setIdTheme(themeDTO.getIdTheme());
                theme.setFormation(formation);
                return theme;
              })
              .collect(Collectors.toSet());
      formation.setThemes(themes);
    }
    if (fDTO.getChapitres() != null) {
      List<Chapitre> chapitres = fDTO.getChapitres().stream()
              .map(chapitreDTO -> {
                Chapitre chapitre = new Chapitre();
                chapitre.setNomChapitre(chapitreDTO.getNomChapitre());
                chapitre.setFormation(formation);
                return chapitre;
              })
              .collect(Collectors.toList());
      // Persister chaque chapitre après avoir associé la formation
      //for (Chapitre chapitre : chapitres) {
      //  formationRepo.persist(chapitre);
      //}
      formation.setChapitres(chapitres);
    }

    formationRepo.persist(formation);

    // Recharger la formation pour s'assurer qu'elle est bien persistée
    Formation persistedFormation = formationRepo.findById(formation.getIdFormation());
    if (persistedFormation == null) {
      throw new IllegalStateException("La formation n'a pas été persistée correctement.");
    }

    return toDTO(formation);
  }

  @Transactional
  public List<FormationDTO> findAllFormations() {
    return formationRepo.listAll()
            .stream()
            .map(this::toDTO)
            .collect(Collectors.toList());
  }

  @Transactional
  public FormationDTO updateFormation(long idFormation, FormationDTO fDTO) {
    // Trouver l'entité existante
    Formation formationExistante = formationRepo.findFormationByIdFormation(idFormation)
            .orElseThrow(() -> new EntityNotFoundException("Formation non trouvée pour l'ID: " + idFormation));

    // Mettre à jour les champs de l'entité avec les valeurs du DTO
    formationExistante.setReference(fDTO.getReference());
    formationExistante.setTitref(fDTO.getTitref());
    formationExistante.setLieu(fDTO.getLieu());
    formationExistante.setDuree(fDTO.getDuree());
    formationExistante.setPrerequis(fDTO.getPrerequis());
    formationExistante.setObjectif(fDTO.getObjectif());
    formationExistante.setPublicVise(fDTO.getPublicVise());
    formationExistante.setProgrammeDetaille(fDTO.getProgrammeDetaille());

    formationRepo.getEntityManager().merge(formationExistante);

    return toDTO(formationExistante);
  }

  @Transactional
  public FormationDTO findFormationById(long idFormation) {
    Formation formation = formationRepo.findFormationByIdFormation(idFormation)
            .orElseThrow(() -> new TrainingNotFoundException("Formation not found with id: " + idFormation));
    return toDTO(formation);
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
    return toDTO(formation);
  }
  @Transactional
  public void deleteFormation(long idFormation) {
    formationRepo.deleteFormationByIdFormation(idFormation);
  }


  // Conversion d'une entité Formation en FormationDTO
  private FormationDTO toDTO(Formation formation) {
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
  // Conversion d'un DTO en entité Formation
  public Formation toEntity(FormationDTO fDTO) {
    Formation formation = new Formation();
    formation.setIdFormation(fDTO.getIdFormation());
    formation.setReference(fDTO.getReference());
    formation.setTitref(fDTO.getTitref());
    formation.setLieu(fDTO.getLieu());
    formation.setInterFormation(fDTO.getInterFormation());
    formation.setDuree(fDTO.getDuree());
    formation.setPrerequis(fDTO.getPrerequis());
    formation.setObjectif(fDTO.getObjectif());
    formation.setPublicVise(fDTO.getPublicVise());
    formation.setProgrammeDetaille(fDTO.getProgrammeDetaille());

    // Mapper les DTO vers les entités correspondantes
    formation.setThemes(fDTO.getThemes().stream().map(this::mapThemeDTOToEntity).collect(Collectors.toSet()));
    formation.setChapitres(fDTO.getChapitres().stream().map(this::mapChapitreDTOToEntity).collect(Collectors.toList()));
    formation.setSessions(fDTO.getSessions().stream().map(this::mapSessionDTOToEntity).collect(Collectors.toSet()));

    return formation;
    }

  // Conversion pour les sous-objets => Themes, Chapitres, Sessions
  private Theme mapThemeDTOToEntity(ThemeDTO dto) {
    Theme theme = new Theme();
    theme.setIdTheme(dto.getIdTheme());
    theme.setNomTheme(dto.getNomTheme());

    return theme;
  }

  private Chapitre mapChapitreDTOToEntity(ChapitreDTO dto) {
    Chapitre chapitre = new Chapitre();
    chapitre.setNomChapitre(dto.getNomChapitre());
    return chapitre;
  }

  private Session mapSessionDTOToEntity(SessionDTO dto) {
    Session session = new Session();
    session.setDateDebut(dto.getDateDebut());
    session.setDateFin(dto.getDateFin());
    session.setLieu(dto.getLieu());
    session.setPrix(dto.getPrix());
    return session;
  }

  // Conversion Set
  private Set<ThemeDTO> convertThemesToDTOs(Set<Theme> themes) {
    return themes.stream()
            .map(this::toThemeDTO)
            .collect(Collectors.toSet());
  }
  private List<ChapitreDTO> convertChapitresToDTOs(List<Chapitre> chapitres) {
    return chapitres.stream()
            .map(this::toChapitreDTO)
            .collect(Collectors.toList());
  }
  private Set<SessionDTO> convertSessionsToDTOs(Set<Session> sessions) {
    return sessions.stream()
            .map(this::toSessionDTO)
            .collect(Collectors.toSet());
  }

  // Conversion entité au DTO
  private ThemeDTO toThemeDTO(Theme theme) {
    return new ThemeDTO(theme.getIdTheme(), theme.getNomTheme());
  }

  private ChapitreDTO toChapitreDTO(Chapitre chapitre) {
    return new ChapitreDTO(chapitre.getIdChap(), chapitre.getNomChapitre());
  }

  private SessionDTO toSessionDTO(Session session) {
    return new SessionDTO(session.getIdSession(), session.getDateDebut(), session.getDateFin(), session.getLieu());
  }

}
