package com.saturne.dto;

public class ThemeDTO {

    private long idTheme;
    private String nomTheme;

    // Constructors, getters, and setters
    public ThemeDTO() {}

    public ThemeDTO(String nomTheme) {
        this.nomTheme = nomTheme;
    }

    public ThemeDTO(long idTheme, String nomTheme) {
        this.idTheme = idTheme;
        this.nomTheme = nomTheme;
    }

    public long getIdTheme() {
        return idTheme;
    }

    public void setIdTheme(long idTheme) {
        this.idTheme = idTheme;
    }

    public String getNomTheme() {
        return nomTheme;
    }

    public void setNomTheme(String nomTheme) {
        this.nomTheme = nomTheme;
    }

    public void setName(String nomTheme) {
        this.nomTheme = nomTheme;
    }

}
