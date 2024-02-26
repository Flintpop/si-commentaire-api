package servlet;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mongoPojo.CommentairePojo;

import jakarta.servlet.annotation.*;
import routes.Routes;
import validation.IdValidateur;
import validation.ValidateurResultat;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import static model.Commentaire.ajouterCommentaire;
import static model.Commentaire.getListeCommentaires;

/**
 * Servlet pour gérer les commentaires.
 * get et post sont supportés.
 */
@WebServlet(name = "CommentaireServlet", value = Routes.commentaire)
public class CommentaireServlet extends HttpServlet {

  private final Gson gson = new Gson();

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    // Simule la récupération des commentaires depuis une source de données
    // On vérifie si request a un parametre id pour obtenir seulement un commentaire pour cet id
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");

    // Si le paramètre id est présent, retourne le commentaire correspondant
    if (request.getParameter("id") != null) {
      servletGetCommentaireById(request, response);
      return;
    }

    List<CommentairePojo> commentairePojos = getListeCommentaires();

    // Convertit la liste des commentaires en JSON
    String commentairesJson = gson.toJson(commentairePojos);

    response.setStatus(HttpServletResponse.SC_OK);
    response.getWriter().write(commentairesJson);
    response.getWriter().flush();
  }

  // TODO: À tester
  private void servletGetCommentaireById(HttpServletRequest request, HttpServletResponse response) throws IOException {
    System.out.println("Le paramètre id est présent et est : " + request.getParameter("id") + " de type" +
            " : " + request.getParameter("id").getClass());
    IdValidateur idValidateur = new IdValidateur();
    ValidateurResultat validationResult = idValidateur.valider(request.getParameter("id"));
    if (!validationResult.isValid()) {
      response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      response.getWriter().write("{\"error\":\"" + validationResult.getErrorMessages() + "\"}");
      response.getWriter().flush();
      return;
    }

    int id = Integer.parseInt(request.getParameter("id"));
    System.out.println("L'id du commentaire est : " + id + " et converti au type : int");
    CommentairePojo commentairePojo = model.Commentaire.getCommentaireById(id);

    // Si le commentaire n'existe pas, retourne une erreur 404
    if (commentairePojo == null) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      response.getWriter().write("{\"error\":\"Commentaire non trouvé.\"}");
      response.getWriter().flush();
      return;
    }

    String commentaireJson = gson.toJson(commentairePojo);
    response.setStatus(HttpServletResponse.SC_OK);
    response.getWriter().write(commentaireJson);
    response.getWriter().flush();
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    // Lit le corps de la requête pour obtenir le JSON envoyé
    StringBuilder jsonBody = new StringBuilder();
    String line;
    try (BufferedReader reader = request.getReader()) {
      while ((line = reader.readLine()) != null) {
        jsonBody.append(line);
      }
    }

    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");

    try {
      // Validation du commentairePojo ici
      ValidateurResultat validateurResultat = ajouterCommentaire(jsonBody.toString());


      if (validateurResultat.isValid()) {
        response.setStatus(HttpServletResponse.SC_CREATED);
        response.getWriter().write("{\"message\":\"Commentaire créé avec succès\"}");
      } else {
        // Gérer les erreurs de validation ici
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        response.getWriter().write("{\"error\":\"Données de commentaire invalides.\"}");
      }
    } catch (JsonSyntaxException e) {
      response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      response.getWriter().write("{\"error\":\"Format de données incorrect.\"}");
    }
    response.getWriter().flush();
  }

  // delete un single commentaire de part son id s'il existe, sinon erreur
  // TODO: À tester
  @Override
  protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");

    IdValidateur idValidateur = new IdValidateur();
    ValidateurResultat validationResult = idValidateur.valider(request.getParameter("id"));
    if (!validationResult.isValid()) {
      response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      response.getWriter().write("{\"error\":\"" + validationResult.getErrorMessages() + "\"}");
      response.getWriter().flush();
      return;
    }

    int id = Integer.parseInt(request.getParameter("id"));
    CommentairePojo commentairePojo = model.Commentaire.getCommentaireById(id);
    if (commentairePojo == null) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      response.getWriter().write("{\"error\":\"Commentaire non trouvé.\"}");
      response.getWriter().flush();
      return;
    }

    model.Commentaire.deleteCommentaire(id);
    response.setStatus(HttpServletResponse.SC_OK);
    response.getWriter().write("{\"message\":\"Commentaire supprimé avec succès\"}");
    response.getWriter().flush();
  }

}
