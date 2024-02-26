package servlet;

import com.google.gson.Gson;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mongoPojo.CommentairePojo;
import routes.Routes;
import validation.IdValidateur;
import validation.ValidateurResultat;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Servlet pour gérer les commentaires des événements.
 * get est utilisé pour récupérer les commentaires d'un événement.
 */
@WebServlet(name = "CommentaireEvenement", value = Routes.evenementCommentaire)
public class CommentaireEvenement extends HttpServlet {

  private final Gson gson = new Gson();

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    IdValidateur idValidateur = new IdValidateur();
    ValidateurResultat validationResult = idValidateur.valider(request.getParameter("id"));
    if (!validationResult.isValid()) {
      response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      response.getWriter().write("{\"error\":\"" + validationResult.getErrorMessages() + "\"}");
      response.getWriter().flush();
      return;
    }

    int id = Integer.parseInt(request.getParameter("id"));
    ArrayList<CommentairePojo> commentairePojos = model.Commentaire.getCommentairesByEvenementId(id);
    if (commentairePojos == null || commentairePojos.isEmpty()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      response.getWriter().write("{\"error\":\"Évènement non trouvé, ou pas de commentaires pour cet " +
              "évènements\"}");
      response.getWriter().flush();
      return;
    }

    response.setStatus(HttpServletResponse.SC_OK);
    response.getWriter().write(gson.toJson(commentairePojos));
    response.getWriter().flush();
  }

}
