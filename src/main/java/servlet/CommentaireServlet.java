package servlet;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mongoPojo.CommentairePojo;

import jakarta.servlet.annotation.*;
import validation.ValidateurResultat;

import java.io.IOException;
import java.util.List;

import static model.Commentaire.ajouterCommentaire;
import static model.Commentaire.getListeCommentaires;

/**
 * Servlet pour gérer les commentaires.
 * get et post sont supportés.
 */
@WebServlet(name = "CommentaireServlet", value = "/commentaire/*")
public class CommentaireServlet extends HttpServlet {

  private final Gson gson = new Gson();

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String pathInfo = request.getPathInfo();
    Integer id = ServletUtils.extraireEtValiderId(pathInfo, response, false);
    if (id == null) {
      return; // Une réponse a déjà été envoyée par extraireEtValiderId
    }

    if (id > 0) { // Un ID a été spécifié
      CommentairePojo commentairePojo = model.Commentaire.getCommentaireById(id);
      if (commentairePojo == null) {
        ServletUtils.envoyerReponseJson(response, HttpServletResponse.SC_NOT_FOUND, "{\"error\":\"Commentaire non trouvé.\"}");
        return;
      }
      ServletUtils.envoyerReponseJson(response, HttpServletResponse.SC_OK, gson.toJson(commentairePojo));
    } else { // Aucun ID spécifié, retourner tous les commentaires
      List<CommentairePojo> commentairePojos = getListeCommentaires();
      ServletUtils.envoyerReponseJson(response, HttpServletResponse.SC_OK, gson.toJson(commentairePojos));
    }
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String jsonBody = ServletUtils.lireCorpsRequete(request);
    try {
      ValidateurResultat validateurResultat = ajouterCommentaire(jsonBody);
      if (validateurResultat.isValid()) {
        ServletUtils.envoyerReponseJson(response, HttpServletResponse.SC_CREATED, "{\"message\":\"Commentaire créé avec succès\"}");
      } else {
        ServletUtils.envoyerReponseJson(response, HttpServletResponse.SC_BAD_REQUEST, "{\"error\":\"Données de commentaire invalides.\"}");
      }
    } catch (JsonSyntaxException e) {
      ServletUtils.envoyerReponseJson(response, HttpServletResponse.SC_BAD_REQUEST, "{\"error\":\"Format de données incorrect.\"}");
    }
  }

  @Override
  protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
    CommentairePojo commentairePojo = ServletUtils.extraireEtValiderCommentaire(request, response);
    if (commentairePojo == null) {
      return; // La méthode utilitaire a déjà géré la réponse
    }

    model.Commentaire.deleteCommentaire(commentairePojo.getId());
    ServletUtils.envoyerReponseJson(response, HttpServletResponse.SC_OK, "{\"message\":\"Commentaire supprimé avec succès\"}");
  }

  @Override
  protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
    CommentairePojo commentairePojo = ServletUtils.extraireEtValiderCommentaire(request, response);
    if (commentairePojo == null) {
      return; // La méthode utilitaire a déjà géré la réponse
    }

    String jsonBody = ServletUtils.lireCorpsRequete(request);
    try {
      model.Commentaire.updateCommentaire(commentairePojo.getId(), jsonBody);
      ServletUtils.envoyerReponseJson(response, HttpServletResponse.SC_OK, "{\"message\":\"Commentaire modifié avec succès\"}");
    } catch (JsonSyntaxException e) {
      ServletUtils.envoyerReponseJson(response, HttpServletResponse.SC_BAD_REQUEST, "{\"error\":\"Format de données incorrect.\"}");
    }
  }
}
