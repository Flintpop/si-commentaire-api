package validation;

public interface Validateur<T> {
  ValidateurResultat valider(T input);
}