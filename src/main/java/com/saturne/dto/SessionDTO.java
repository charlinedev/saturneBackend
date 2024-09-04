package com.saturne.dto;

import java.time.LocalDate;

public class SessionDTO {

    private long idSession;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private String lieu;
    private float prix;


    // Constructors, getters, and setters
    public SessionDTO() {}

    public SessionDTO(long idSession, LocalDate dateDebut, LocalDate dateFin, String lieu) {
        this.idSession = idSession;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.lieu = lieu;
        this.prix = prix;
    }

    public long getIdSession() {
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
}
