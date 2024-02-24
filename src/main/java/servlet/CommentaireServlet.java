package servlet;

import com.google.gson.Gson;
import mongoPojo.CommentairePojo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import static model.Commentaire.ajouterCommentaire;
import static model.Commentaire.getListeCommentaires;

//@WebServlet(name = "CommentaireServlet", urlPatterns = {"/CommentaireServlet"})
public class CommentaireServlet extends HttpServlet {

  private final Gson gson = new Gson();

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // Lit le corps de la requête pour obtenir le JSON envoyé
    StringBuilder jsonBody = new StringBuilder();
    String line;
    try (BufferedReader reader = request.getReader()) {
      while ((line = reader.readLine()) != null) {
        jsonBody.append(line);
      }
    }

    // Convertit le JSON reçu en objet Commentaire
    CommentairePojo commentairePojo = gson.fromJson(jsonBody.toString(), CommentairePojo.class);

    // Simule l'ajout du commentaire à une source de données
    ajouterCommentaire(commentairePojo);

    response.setStatus(HttpServletResponse.SC_CREATED);
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");
    response.getWriter().write("{\"message\":\"Commentaire créé avec succès\"}");
    response.getWriter().flush();
  }

}
