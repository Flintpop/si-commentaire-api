package mongoPojo;

import java.util.Objects;

// Classe interne pour simuler l'entité Commentaire
public class CommentairePojo {
  private int id;
  private int evenementId;

  private int auteurId;

  private String dateMessage;

  public CommentairePojo() {
  }

  public CommentairePojo(int id, int evenementId, int auteurId, String dateMessage, String texte) {
    this.id = id;
    this.evenementId = evenementId;
    this.auteurId = auteurId;
    this.dateMessage = dateMessage;
    this.texte = texte;
  }

  private String texte;
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getEvenementId() {
    return evenementId;
  }

  public void setEvenementId(int evenementId) {
    this.evenementId = evenementId;
  }

  public int getAuteurId() {
    return auteurId;
  }

  public void setAuteurId(int auteurId) {
    this.auteurId = auteurId;
  }

  public String getDateMessage() {
    return dateMessage;
  }

  public void setDateMessage(String dateMessage) {
    this.dateMessage = dateMessage;
  }

  public String getTexte() {
    return texte;
  }

  public void setTexte(String texte) {
    this.texte = texte;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    CommentairePojo that = (CommentairePojo) o;
    return id == that.id && evenementId == that.evenementId && auteurId == that.auteurId && Objects.equals(dateMessage, that.dateMessage) && Objects.equals(texte, that.texte);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, evenementId, auteurId, dateMessage, texte);
  }

  @Override
  public String toString() {
    return "Commentaire{" +
            "id=" + id +
            ", evenementId=" + evenementId +
            ", auteurId=" + auteurId +
            ", dateMessage='" + dateMessage + '\'' +
            ", texte='" + texte + '\'' +
            '}';
  }
}
