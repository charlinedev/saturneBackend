package com.saturne.entities;

import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/*
 * Formations
 */
@Entity
@Table(name = "trainings")
@NamedNativeQuery(
  name = "Formation.FindTrainingByReference",
  //query="SELECT * FROM trainings WHERE ref=?",
  query = "SELECT * FROM trainings WHERE ref=?",
  //query="SELECT idTraining, ref, location, interTraining, duration,requirements, goal, targetAudience, details  FROM trainings WHERE ref=?",
  resultClass = Formation.class
)
@DynamicUpdate
public class Formation {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "idTraining")
  private long idFormation;

  @Column(name = "ref")
  private String reference;

  @Column(name = "title")
  private String titref;

  @Column(name = "location")
  private String lieu;

  @Column(name = "interTraining")
  private boolean interFormation;

  @Column(name = "duration")
  private int duree;

  @Column(name = "requirements")
  private String prerequis;

  @Column(name = "goal")
  private String objectif;

  @Column(name = "targetAudience")
  private String publicVise;

  @Column(name = "details")
  private String programmeDetaille;

  //* Formations --> * Themes
  @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
  @JoinTable(
    name = "trainings_themes",
    joinColumns = @JoinColumn(name = "trainings_ID"),
    inverseJoinColumns = @JoinColumn(name = "themes_ID")
  )
  private Set<Theme> themes = new HashSet<Theme>();

  // 1 formation <--> *chapitres //composition
  @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "formation")
  private Set<Chapitre> chapitres = new HashSet<Chapitre>();

  // 1 formation <--> * sessions
  @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "formation", fetch = FetchType.EAGER)
  private Set<Session> sessions = new HashSet<Session>();

  // 1 formation --> 1 preTest
  @OneToOne(cascade = CascadeType.PERSIST/*, fetch = FetchType.LAZY*/)
  @JoinColumn(name = "idTest")
  private PreTest pretest = new PreTest();

  public Formation() {}

  public Formation(
    String reference,
    String titref,
    String lieu,
    boolean interFormation,
    int duree,
    String prerequis,
    String objectif,
    String publicVise,
    String programmeDetaille
  ) {
    this.reference = reference;
    this.titref = titref;
    this.lieu = lieu;
    this.interFormation = interFormation;
    this.duree = duree;
    this.prerequis = prerequis;
    this.objectif = objectif;
    this.publicVise = publicVise;
    this.programmeDetaille = programmeDetaille;
  }

  public long getIdFormation() {
    return idFormation;
  }

  public void setIdFormation(long idFormation) {
    this.idFormation = idFormation;
  }

  public String getReference() {
    return reference;
  }

  public void setReference(String reference) {
    this.reference = reference;
  }

  public String getTitref() {
    return titref;
  }

  public void setTitref(String titref) {
    this.titref = titref;
  }

  public String getLieu() {
    return lieu;
  }

  public void setLieu(String lieu) {
    this.lieu = lieu;
  }

  public boolean getInterFormation() {
    return interFormation;
  }

  public void setInterFormation(boolean interFormation) {
    this.interFormation = interFormation;
  }

  public int getDuree() {
    return duree;
  }

  public void setDuree(int duree) {
    this.duree = duree;
  }

  public String getPrerequis() {
    return prerequis;
  }

  public void setPrerequis(String prerequis) {
    this.prerequis = prerequis;
  }

  public String getObjectif() {
    return objectif;
  }

  public void setObjectif(String objectif) {
    this.objectif = objectif;
  }

  public String getPublicVise() {
    return publicVise;
  }

  public void setPublicVise(String publicVise) {
    this.publicVise = publicVise;
  }

  public String getProgrammeDetaille() {
    return programmeDetaille;
  }

  public void setProgrammeDetaille(String programmeDetaille) {
    this.programmeDetaille = programmeDetaille;
  }

  public Set<Chapitre> getChapitres() {
    return chapitres;
  }

  public void setChapitres(Set<Chapitre> chapitres) {
    this.chapitres = chapitres;
  }

  public Set<Theme> getThemes() {
    return themes;
  }

  public void setThemes(Set<Theme> themes) {
    this.themes = themes;
  }

  public PreTest getPretest() {
    return pretest;
  }

  public void setPretest(PreTest pretest) {
    this.pretest = pretest;
  }

  public Set<Session> getSessions() {
    return sessions;
  }

  public void setSessions(Set<Session> sessions) {
    this.sessions = sessions;
  }

  @Override
  public String toString() {
    return (
      "Formation [idFormation=" +
      idFormation +
      ", reference=" +
      reference +
      ", titref=" +
      titref +
      ", lieu=" +
      lieu +
      ", interFormation=" +
      interFormation +
      ", duree=" +
      duree +
      ", prerequis=" +
      prerequis +
      ", objectif=" +
      objectif +
      ", publicVise=" +
      publicVise +
      ", programmeDetaille=" +
      programmeDetaille +
      "]"
    );
  }

  @Override
  public int hashCode() {
    return Objects.hash(duree, idFormation, interFormation, lieu, objectif, prerequis, programmeDetaille, publicVise, reference, titref);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    Formation other = (Formation) obj;
    return (
      duree == other.duree &&
      idFormation == other.idFormation &&
      Objects.equals(interFormation, other.interFormation) &&
      Objects.equals(lieu, other.lieu) &&
      Objects.equals(objectif, other.objectif) &&
      Objects.equals(prerequis, other.prerequis) &&
      Objects.equals(programmeDetaille, other.programmeDetaille) &&
      Objects.equals(publicVise, other.publicVise) &&
      Objects.equals(reference, other.reference) &&
      Objects.equals(titref, other.titref)
    );
  }
}
