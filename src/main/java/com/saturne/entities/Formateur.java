package com.saturne.entities;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "Trainers")
public class Formateur { // implements Serializable{??

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "idTrainer")
  private long idFormateur;

  @Column(name = "cv")
  private String cv;

  // One To Many
  //référence vers sessions : 1 formateur peut participer à plusieurs sessions de formation=> collection de sessions dans Formateur
  @OneToMany(/*cascade=CascadeType.PERSIST,*/mappedBy = "formateur") //#!TODO: check cascadeType!!!
  private Set<Session> listeSessions = new HashSet<Session>(); //la liste des sessions auxquelles il participe

  public Formateur() {}

  public Formateur(String cv) {
    this.cv = cv;
  }

  public long getIdFormateur() {
    return idFormateur;
  }

  public void setIdFormateur(long idFormateur) {
    this.idFormateur = idFormateur;
  }

  public String getCv() {
    return cv;
  }

  public void setCv(String cv) {
    this.cv = cv;
  }

  public Set<Session> getListeSessions() {
    return listeSessions;
  }

  public void setListeSessions(Set<Session> listeSessions) {
    this.listeSessions = listeSessions;
  }

  @Override
  public String toString() {
    return "Formateur [idFormateur=" + idFormateur + ", cv=" + cv + "]";
  }

  @Override
  public int hashCode() {
    return Objects.hash(cv, idFormateur);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    Formateur other = (Formateur) obj;
    return Objects.equals(cv, other.cv) && idFormateur == other.idFormateur;
  }
}
