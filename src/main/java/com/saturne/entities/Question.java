package com.saturne.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "questions")
public class Question { // implements Serializable{??

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "idQuestion")
  private long idQuestion;

  @Column(name = "questContent")
  private String contenuQ;

  @Column(name = "idTheme")
  private String idTheme;

  // * questions <--> 1 pretset
  @ManyToOne/*(cascade=CascadeType.PERSIST)*///#!TODO: check cascade!!
  @JoinColumn(name = "idTest")
  private PreTest preTest = new PreTest();

  public Question() {}

  public Question(String contenuQ, String idTheme) {
    this.contenuQ = contenuQ;
    this.idTheme = idTheme;
  }

  public long getIdQuestion() {
    return idQuestion;
  }

  public void setIdQuestion(long idQuestion) {
    this.idQuestion = idQuestion;
  }

  public String getContenuQ() {
    return contenuQ;
  }

  public void setContenuQ(String contenuQ) {
    this.contenuQ = contenuQ;
  }

  public String getIdTheme() {
    return idTheme;
  }

  public void setIdTheme(String idTheme) {
    this.idTheme = idTheme;
  }

  public PreTest getPreTest() {
    return preTest;
  }

  public void setPreTest(PreTest preTest) {
    this.preTest = preTest;
  }

  @Override
  public String toString() {
    return ("Question [idQuestion=" + idQuestion + ", contenuQ=" + contenuQ + ", idTheme=" + idTheme + "]");
  }
}
