package com.saturne.entities;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "themes")
public class Theme { // implements Serializable{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "idTheme")
  private long idTheme;

  @Column(name = "themeName")
  private String nomTheme;

  //1 theme <--> * sous-themes
  @OneToMany //( mappedBy="theme")//, insert=false , update=false )
  private Set<Theme> sousTheme = new HashSet<Theme>();

  //    //* sous-themes <--> 1 theme
  //    @ManyToOne
  //    @JoinColumn(name="idTheme")
  //	private Theme theme = new Theme();

  public Theme() {}

  //constructeur
  public Theme(String nomTheme) {
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

  public Set<Theme> getSousTheme() {
    return sousTheme;
  }

  public void setSousTheme(Set<Theme> sousTheme) {
    this.sousTheme = sousTheme;
  }

  //	public Theme getTheme() {
  //		return theme;
  //	}
  //
  //	public void setTheme(Theme theme) {
  //		this.theme = theme;
  //	}

  @Override
  public String toString() {
    return "Theme [idTheme=" + idTheme + ", nomTheme=" + nomTheme + "]";
  }

  @Override
  public int hashCode() {
    return Objects.hash(idTheme, nomTheme);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    Theme other = (Theme) obj;
    return idTheme == other.idTheme && Objects.equals(nomTheme, other.nomTheme);
  }
}
