package com.saturne.dto;

public class ChapitreDTO {
    private long idChap;
    private String nomChapitre;

    // Constructeurs, getters, et setters
    public ChapitreDTO() {}

    public ChapitreDTO(long idChap, String nomChapitre) {
        this.idChap = idChap;
        this.nomChapitre = nomChapitre;
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

}
