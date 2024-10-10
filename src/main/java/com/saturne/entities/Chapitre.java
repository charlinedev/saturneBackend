package com.saturne.entities;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "chapters")
public class Chapitre {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "idChapter")
  private long idChap;

  @Column(name = "name")
  private String nomChapitre;

  @Column(name = "content")
  private String contenu;

  @ManyToOne(cascade = CascadeType.PERSIST)
  @JoinColumn(name = "idFormation", nullable = false)
  private Formation formation;

  public Chapitre() {}

  public Chapitre(long idChap, String nomChapitre, String contenu) {
    this.idChap = idChap;
    this.nomChapitre = nomChapitre;
    this.contenu = contenu;
  }

  public long getIdChap() {
    return idChap;
  }

  public void setIdChap(long idChap) {
    this.idChap = idChap;
  }

  public String getNomChapitre() {
    return nomChapitre;
  }

  public void setNomChapitre(String nomChapitre) {
    this.nomChapitre = nomChapitre;
  }

  public String getContenu() {
    return contenu;
  }

  public void setContenu(String contenu) {
    this.contenu = contenu;
  }

  public Formation getFormation() {
    return formation;
  }

  public void setFormation(Formation formation) {
    this.formation = formation;
  }

  @Override
  public String toString() {
    return ("Chapitre [idChap=" + idChap + ", nomChapitre=" + nomChapitre + ", contenu=" + contenu + "]");
  }

  @Override
  public int hashCode() {
    return Objects.hash(contenu, idChap, nomChapitre);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    Chapitre other = (Chapitre) obj;
    return (Objects.equals(contenu, other.contenu) && idChap == other.idChap && Objects.equals(nomChapitre, other.nomChapitre));
  }
}
