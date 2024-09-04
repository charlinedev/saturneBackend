package com.saturne.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

/*
 * Sessions
 */
@Entity
@Table(name = "sessions")
public class Session { // implements Serializable{??

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "idSession")
  private long idSession;

  @Column(name = "dateStart")
  private LocalDate dateDebut;

  @Column(name = "dateEnd")
  private LocalDate dateFin;

  @Column(name = "location")
  private String lieu;

  @Column(name = "price")
  private float prix;

  @ManyToOne
  @JoinColumn(name = "idClassroom")
  private Salle salle;

  @JsonIgnoreProperties("sessions")
  @ManyToOne(optional = true)
  @JoinColumn(name = "idTraining")
  private Formation formation;

  @ManyToOne
  @JoinColumn(name = "idTrainer")
  private Formateur formateur;

  @OneToMany
  @JoinColumn(name = "pivotEvalSession")
  private Set<EvalSession> evalSessions;

  @ManyToMany
  @JoinTable(
    name = "sessionTrainee",
    joinColumns = { @JoinColumn(name = "idSession") },
    inverseJoinColumns = { @JoinColumn(name = "idTrainee") }
  )
  private Set<Stagiaire> stagiaires;

  public Session() {}

  public Session(LocalDate dateDebut, LocalDate dateFin, String lieu, float prix) {
    this.dateDebut = dateDebut;
    this.dateFin = dateFin;
    this.lieu = lieu;
    this.prix = prix;
  }

  public Long getIdSession() {
    return idSession;
  }

  public void setIdSession(long idSession) {
    this.idSession = idSession;
  }

  public LocalDate getDateDebut() {
    return dateDebut;
  }

  public void setDateDebut(LocalDate dateDebut) {
    this.dateDebut = dateDebut;
  }

  public LocalDate getDateFin() {
    return dateFin;
  }

  public void setDateFin(LocalDate dateFin) {
    this.dateFin = dateFin;
  }

  public String getLieu() {
    return lieu;
  }

  public void setLieu(String lieu) {
    this.lieu = lieu;
  }

  public float getPrix() {
    return prix;
  }

  public void setPrix(float prix) {
    this.prix = prix;
  }

  public Salle getSalle() {
    return salle;
  }

  public void setSalle(Salle salle) {
    this.salle = salle;
  }

  public Formation getFormation() {
    return formation;
  }

  public void setFormation(Formation formation) {
    this.formation = formation;
  }

  public Formateur getFormateur() {
    return formateur;
  }

  public void setFormateur(Formateur formateur) {
    this.formateur = formateur;
  }

  public Set<EvalSession> getEvalSessions() {
    return evalSessions;
  }

  public void setEvalSessions(Set<EvalSession> evalSessions) {
    this.evalSessions = evalSessions;
  }

  public Set<Stagiaire> getStagiaires() {
    return stagiaires;
  }

  public void setStagiaires(Set<Stagiaire> stagiaires) {
    this.stagiaires = stagiaires;
  }

  @Override
  public String toString() {
    return (
      "Session [idSession=" + idSession + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin + ", lieu=" + lieu + ", prix=" + prix + "]"
    );
  }

  @Override
  public int hashCode() {
    return Objects.hash(dateDebut, dateFin, idSession, lieu, prix);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    Session other = (Session) obj;
    return (
      Objects.equals(dateDebut, other.dateDebut) &&
      Objects.equals(dateFin, other.dateFin) &&
      idSession == other.idSession &&
      Objects.equals(lieu, other.lieu) &&
      Float.floatToIntBits(prix) == Float.floatToIntBits(other.prix)
    );
  }
}
