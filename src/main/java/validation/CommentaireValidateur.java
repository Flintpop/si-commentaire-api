package validation;

import mongoPojo.CommentairePojo;

import java.util.ArrayList;

public class CommentaireValidateur implements Validateur<CommentairePojo> {
  @Override
  public ValidateurResultat valider(CommentairePojo input) {
    // Valide le commentaire ici,
    ArrayList<String> errorMessages = new ArrayList<>();
    boolean isValid = true;

    if (input.getTexte() == null || input.getTexte().isEmpty()) {
      errorMessages.add("Le texte du commentaire ne peut pas être vide");
      isValid = false;
    }

    if (input.getTexte().length() > 1000) {
      errorMessages.add("Le texte du commentaire ne peut pas dépasser 1000 caractères");
      isValid = false;
    }

    if (input.getAuteurId() < 0) {
      errorMessages.add("L'id de l'auteur ne peut pas être négatif");
      isValid = false;
    }

    if (input.getDateMessage() == null || input.getDateMessage().isEmpty()) {
      errorMessages.add("La date du message ne peut pas être vide");
      isValid = false;
    }

    if (input.getDateMessage().length() > 100) {
      errorMessages.add("La date du message ne peut pas dépasser 100 caractères");
      isValid = false;
    }

    if (input.getEvenementId() < 0) {
      errorMessages.add("L'id de l'événement ne peut pas être négatif");
      isValid = false;
    }

    if (!isValid) {
      return new ValidateurResultat(false, errorMessages);
    } else {
      return new ValidateurResultat(true, null);
    }

  }
}
