package com.saturne.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "evalSessions")
public class EvalSession { // implements Serializable{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "idEval")
  private long idEval;

  @Column(name = "evalTrainer")
  private int evalFormateur; //=>  formateur

  @Column(name = "evalContent")
  private int evalContenu; //=> formation

  @Column(name = "pedagogy")
  private int pedagogie; // =>formateur

  @Column(name = "fieldMaster")
  private int maitrisedomaine; // => formateur

  @Column(name = "availibility")
  private int disponibilite; // =>formateur

  @Column(name = "questionsResponse")
  private int reponsesQuestions; //réponses aux questions =>formateur

  @Column(name = "recommandation")
  private boolean recommandation; //=> recommandation de la formation

  //* eval session --> 1 stagiaire
  @ManyToOne //(cascade=CascadeType.PERSIST)// ou CascadeType.ALL?? //#!TODO: vérifier!! cascade type
  @JoinColumn(name = "idTrainee")
  private Stagiaire trainee;

  public EvalSession() {}

  public EvalSession(
    int evalFormateur,
    int evalContenu,
    int pedagogie,
    int maitrisedomaine,
    int disponibilite,
    int reponsesQuestions,
    boolean recommandation
  ) {
    this.evalFormateur = evalFormateur;
    this.evalContenu = evalContenu;
    this.pedagogie = pedagogie;
    this.maitrisedomaine = maitrisedomaine;
    this.disponibilite = disponibilite;
    this.reponsesQuestions = reponsesQuestions;
    this.recommandation = recommandation;
  }

  public int getEvalFormateur() {
    return evalFormateur;
  }

  public void setEvalFormateur(int evalFormateur) {
    this.evalFormateur = evalFormateur;
  }

  public int getEvalContenu() {
    return evalContenu;
  }

  public void setEvalContenu(int evalContenu) {
    this.evalContenu = evalContenu;
  }

  public int getPedagogie() {
    return pedagogie;
  }

  public void setPedagogie(int pedagogie) {
    this.pedagogie = pedagogie;
  }

  public int getMaitrisedomaine() {
    return maitrisedomaine;
  }

  public void setMaitrisedomaine(int maitrisedomaine) {
    this.maitrisedomaine = maitrisedomaine;
  }

  public int getDisponibilite() {
    return disponibilite;
  }

  public void setDisponibilite(int disponibilite) {
    this.disponibilite = disponibilite;
  }

  //getter/setter pour la référence de stagiaire

  public int getReponsesQuestions() {
    return reponsesQuestions;
  }

  public void setReponsesQuestions(int reponsesQuestions) {
    this.reponsesQuestions = reponsesQuestions;
  }

  public boolean isRecommandation() {
    return recommandation;
  }

  public void setRecommandation(boolean recommandation) {
    this.recommandation = recommandation;
  }

  public Stagiaire getTrainee() {
    return trainee;
  }

  public void setTrainee(Stagiaire trainee) {
    this.trainee = trainee;
  }

  @Override
  public String toString() {
    return (
      "EvalSession [idEval=" +
      idEval +
      ", evalFormateur=" +
      evalFormateur +
      ", evalContenu=" +
      evalContenu +
      ", pedagogie=" +
      pedagogie +
      ", maitrisedomaine=" +
      maitrisedomaine +
      ", disponibilite=" +
      disponibilite +
      ", reponsesQuestions=" +
      reponsesQuestions +
      ", recommandation=" +
      recommandation +
      "]"
    );
  }
}
