package com.saturne.dto;

import java.util.Set;

public class FormationDTO {
    private long idFormation;
    private String reference;
    private String titref;
    private String lieu;
    private boolean interFormation;
    private int duree;
    private String prerequis;
    private String objectif;
    private String publicVise;
    private String programmeDetaille;

    private Set<ThemeDTO> themes;
    private Set<ChapitreDTO> chapitres;
    private Set<SessionDTO> sessions;
    // Constructors
    public FormationDTO() {}

    public FormationDTO (long idFormation, String reference, String titref, String lieu,
                        boolean interFormation, int duree, String prerequis,
                        String objectif, String publicVise, String programmeDetaille, Set<ThemeDTO> themes, Set<ChapitreDTO> chapitres, Set<SessionDTO> sessions) {
        this.idFormation = idFormation;
        this.reference = reference;
        this.titref = titref;
        this.lieu = lieu;
        this.interFormation = interFormation;
        this.duree = duree;
        this.prerequis = prerequis;
        this.objectif = objectif;
        this.publicVise = publicVise;
        this.programmeDetaille = programmeDetaille;
        this.themes = themes;
        this.chapitres = chapitres;
        this.sessions = sessions;

    }

    // Getters and Setters
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

    public Set<ThemeDTO> getThemes() {
        return themes;
    }

    public void setThemes(Set<ThemeDTO> themes) {
        this.themes = themes;
    }

    public Set<ChapitreDTO> getChapitres() {
        return chapitres;
    }

    public void setChapitres(Set<ChapitreDTO> chapitres) {
        this.chapitres = chapitres;
    }

    public Set<SessionDTO> getSessions() {
        return sessions;
    }

    public void setSessions(Set<SessionDTO> sessions) {
        this.sessions = sessions;
    }
}
