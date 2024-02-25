package validation;

import java.util.Collections;
import java.util.List;

public class ValidateurResultat {
  private final boolean valid;
  private final List<String> errorMessages;

  public ValidateurResultat(boolean isValid, List<String> errorMessages) {
    this.valid = isValid;
    this.errorMessages = errorMessages;
  }

  public static ValidateurResultat ok() {
    return new ValidateurResultat(true, Collections.emptyList());
  }

  public static ValidateurResultat fail(List<String> errorMessages) {
    return new ValidateurResultat(false, errorMessages);
  }

  public boolean isValid() {
    return valid;
  }

  public List<String> getErrorMessages() {
    return errorMessages;
  }
}
