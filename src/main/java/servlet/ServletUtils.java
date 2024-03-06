package servlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;

import mongoPojo.CommentairePojo;

public class ServletUtils {
  public static String lireCorpsRequete(HttpServletRequest request) throws IOException {
    StringBuilder jsonBody = new StringBuilder();
    try (BufferedReader reader = request.getReader()) {
      String line;
      while ((line = reader.readLine()) != null) {
        jsonBody.append(line);
      }
    }
    return jsonBody.toString();
  }

  public static Integer extraireEtValiderId(String pathInfo, HttpServletResponse response, boolean expectedId) throws IOException {
    if (pathInfo == null || pathInfo.length() <= 1) {
      if (expectedId) {
        ServletUtils.envoyerReponseJson(response, HttpServletResponse.SC_BAD_REQUEST, "{\"error\":\"L'ID du commentaire est manquant ou invalide.\"}");
      }
      return -1;
    }

    try {
      int id = Integer.parseInt(pathInfo.substring(1));
      if (id < 0) {
        ServletUtils.envoyerReponseJson(response, HttpServletResponse.SC_BAD_REQUEST, "{\"error\":\"L'ID du commentaire doit être un entier positif.\"}");
        return null;
      }
      return id;
    } catch (NumberFormatException e) {
      ServletUtils.envoyerReponseJson(response, HttpServletResponse.SC_BAD_REQUEST, "{\"error\":\"Format de l'ID invalide, n'est pas un chiffre.\"}");
      return null;
    }
  }

  /**
   * Envoie une réponse JSON avec le code d'état HTTP spécifié.
   *
   * @param response   La réponse HTTP.
   * @param statusCode Le code d'état HTTP.
   * @param message    Le message à envoyer.
   * @throws IOException Si une erreur d'entrée/sortie survient.
   */
  public static void envoyerReponseJson(HttpServletResponse response, int statusCode, String message) throws IOException {
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");
    response.setStatus(statusCode);
    response.getWriter().write(message);
    response.getWriter().flush();
  }


  public static CommentairePojo extraireEtValiderCommentaire(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String pathInfo = request.getPathInfo();
    Integer id = extraireEtValiderId(pathInfo, response, true);
    if (id == null) {
      return null; // Une réponse a déjà été envoyée par extraireEtValiderId
    }

    CommentairePojo commentairePojo = model.Commentaire.getCommentaireById(id);
    if (commentairePojo == null) {
      envoyerReponseJson(response, HttpServletResponse.SC_NOT_FOUND, "{\"error\":\"Commentaire non trouvé.\"}");
      return null;
    }

    return commentairePojo;
  }
}
