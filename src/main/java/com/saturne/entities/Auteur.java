package com.saturne.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "authors")
public class Auteur extends PanacheEntityBase {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "idAuthor")
  private long idAuteur;

  @Column(name = "firstName")
  private String prenomA;

  @Column(name = "lastName")
  private String nomA;

  public Auteur() {}

  public Auteur(String prenomA, String nomA) {
    this.prenomA = prenomA;
    this.nomA = nomA;
  }

  public Auteur(long idAuteur, String prenomA, String nomA) {
    this.idAuteur = idAuteur;
    this.prenomA = prenomA;
    this.nomA = nomA;
  }

  public long getIdAuteur() {
    return idAuteur;
  }

  public void setIdAuteur(long idAuteur) {
    this.idAuteur = idAuteur;
  }

  public String getPrenomA() {
    return prenomA;
  }

  public void setPrenomA(String prenomA) {
    this.prenomA = prenomA;
  }

  public String getNomA() {
    return nomA;
  }

  public void setNomA(String nomA) {
    this.nomA = nomA;
  }

  @Override
  public String toString() {
    return ("Auteur [idAuteur=" + idAuteur + ", prenomA=" + prenomA + ", nomA=" + nomA + "]");
  }

  @Override
  public int hashCode() {
    return Objects.hash(idAuteur, nomA, prenomA);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    Auteur other = (Auteur) obj;
    return (idAuteur == other.idAuteur && Objects.equals(nomA, other.nomA) && Objects.equals(prenomA, other.prenomA));
  }
}
