package validation;

import java.util.ArrayList;

public class IdValidateur implements Validateur<String> {

  @Override
  public ValidateurResultat valider(String input) {
    ArrayList<String> errors = new ArrayList<>();
    boolean isValid = true;

    if (input == null) {
      errors.add("L'id du commentaire est manquant.");
      return new ValidateurResultat(false, errors);
    }

    if (input.isEmpty()) {
      errors.add("L'id du commentaire est vide.");
      return new ValidateurResultat(false, errors);
    }

    int id;

    try {
      id = Integer.parseInt(input);
    } catch (NumberFormatException e) {
      errors.add("L'id du commentaire doit être un entier.");
      return new ValidateurResultat(false, errors);
    }

    if (id < 0) {
      errors.add("L'id du commentaire doit être un entier positif.");
      return new ValidateurResultat(false, errors);
    }

    return new ValidateurResultat(isValid, errors);
  }

}
