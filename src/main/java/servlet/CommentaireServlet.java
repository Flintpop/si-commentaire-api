package servlet;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mongoPojo.CommentairePojo;

import jakarta.servlet.annotation.*;
import validation.CommentaireValidateur;
import validation.ValidateurResultat;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import static model.Commentaire.ajouterCommentaire;
import static model.Commentaire.getListeCommentaires;

@WebServlet(name = "CommentaireServlet", value = "/commentaire")
public class CommentaireServlet extends HttpServlet {

  private final Gson gson = new Gson();

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    // Simule la récupération des commentaires depuis une source de données
    List<CommentairePojo> commentairePojos = getListeCommentaires();

    // Convertit la liste des commentaires en JSON
    String commentairesJson = gson.toJson(commentairePojos);

    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");
    response.getWriter().write(commentairesJson);
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

}
