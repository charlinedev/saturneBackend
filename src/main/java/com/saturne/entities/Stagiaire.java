package com.saturne.entities;

import jakarta.persistence.*;

/*
 * Stagiaires
 */
@Entity
@Table(name = "trainees")
public class Stagiaire { //  implements Serializable{??

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "idTrainee")
  private long idStagiaire;

  @Column(name = "lastName")
  private String nom;

  @Column(name = "firstName")
  private String prenom;

  @Column(name = "address")
  private String adresse;

  @Column(name = "email")
  private String email;

  @Column(name = "phoneNumber")
  private String numTelephone;

  @Column(name = "password")
  private String mpass;

  @Column(name = "company")
  private boolean entreprise;

  @Column(name = "contactInfo")
  private String coordonneesEntre;

  public Stagiaire() {}

  public Stagiaire(
    String nom,
    String prenom,
    String adresse,
    String email,
    String numTelephone,
    boolean entreprise,
    String coordonneesEntre
  ) {
    this.nom = nom;
    this.prenom = prenom;
    this.adresse = adresse;
    this.email = email;
    this.numTelephone = numTelephone;
    this.entreprise = entreprise;
    this.coordonneesEntre = coordonneesEntre;
  }

  public long getIdStagiaire() {
    return idStagiaire;
  }

  public void setIdStagiaire(long idStagiaire) {
    this.idStagiaire = idStagiaire;
  }

  public String getNom() {
    return nom;
  }

  public void setNom(String nom) {
    this.nom = nom;
  }

  public String getPrenom() {
    return prenom;
  }

  public void setPrenom(String prenom) {
    this.prenom = prenom;
  }

  public String getAdresse() {
    return adresse;
  }

  public void setAdresse(String adresse) {
    this.adresse = adresse;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getNumTelephone() {
    return numTelephone;
  }

  public void setNumTelephone(String numTelephone) {
    this.numTelephone = numTelephone;
  }

  public String getMpass() {
    return mpass;
  }

  public void setMpass(String mpass) {
    this.mpass = mpass;
  }

  public boolean getEntreprise() {
    return entreprise;
  }

  public void setEntreprise(boolean entreprise) {
    this.entreprise = entreprise;
  }

  public String getCoordonneesEntre() {
    return coordonneesEntre;
  }

  public void setCoordonneesEntre(String coordonnees) {
    this.coordonneesEntre = coordonnees;
  }

  @Override
  public String toString() {
    return (
      "Stagiaire [idStagiaire=" +
      idStagiaire +
      ", nom=" +
      nom +
      ", prenom=" +
      prenom +
      ", adresse=" +
      adresse +
      ", email=" +
      email +
      ", numTelephone=" +
      numTelephone +
      ", entreprise=" +
      entreprise +
      ", coordonneesEntre=" +
      coordonneesEntre +
      "]"
    );
  }
}
