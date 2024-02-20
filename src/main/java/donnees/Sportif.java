/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package donnees;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author ercar
 */
@Entity
@Table(name = "sportif")
@NamedQueries({
        @NamedQuery(name = "Sportif.findAll", query = "SELECT s FROM Sportif s"),
        @NamedQuery(name = "Sportif.findByCodeSportif", query = "SELECT s FROM Sportif s WHERE s.codeSportif = :codeSportif"),
        @NamedQuery(name = "Sportif.findByNom", query = "SELECT s FROM Sportif s WHERE s.nom = :nom"),
        @NamedQuery(name = "Sportif.findByRue", query = "SELECT s FROM Sportif s WHERE s.rue = :rue"),
        @NamedQuery(name = "Sportif.findByVille", query = "SELECT s FROM Sportif s WHERE s.ville = :ville"),
        @NamedQuery(name = "Sportif.findByCodePostal", query = "SELECT s FROM Sportif s WHERE s.codePostal = :codePostal")})
public class Sportif implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "code_sportif")
  private Integer codeSportif;
  @Column(name = "nom")
  private String nom;
  @Column(name = "rue")
  private String rue;
  @Column(name = "ville")
  private String ville;
  @Column(name = "code_postal")
  private String codePostal;
  @JoinTable(name = "pratique", joinColumns = {
          @JoinColumn(name = "code_sportif", referencedColumnName = "code_sportif")}, inverseJoinColumns = {
          @JoinColumn(name = "code_discipline", referencedColumnName = "code_discipline")})
  @ManyToMany
  private Set<Discipline> disciplines;

  public Sportif() {
  }

  public Sportif(Integer codeSportif) {
    this.codeSportif = codeSportif;
  }

  public Integer getCodeSportif() {
    return codeSportif;
  }

  public void setCodeSportif(Integer codeSportif) {
    this.codeSportif = codeSportif;
  }

  public String getNom() {
    return nom;
  }

  public void setNom(String nom) {
    this.nom = nom;
  }

  public String getRue() {
    return rue;
  }

  public void setRue(String rue) {
    this.rue = rue;
  }

  public String getVille() {
    return ville;
  }

  public void setVille(String ville) {
    this.ville = ville;
  }

  public String getCodePostal() {
    return codePostal;
  }

  public void setCodePostal(String codePostal) {
    this.codePostal = codePostal;
  }

  public Set<Discipline> getDisciplineSet() {
    return disciplines;
  }

  public void setDisciplineSet(Set<Discipline> disciplineSet) {
    this.disciplines = disciplineSet;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (codeSportif != null ? codeSportif.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Sportif)) {
      return false;
    }
    Sportif other = (Sportif) object;
    if ((this.codeSportif == null && other.codeSportif != null) || (this.codeSportif != null && !this.codeSportif.equals(other.codeSportif))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "data.Sportif[ codeSportif=" + codeSportif + " ]";
  }

}
