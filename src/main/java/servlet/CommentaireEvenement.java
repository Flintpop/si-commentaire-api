package servlet;

import com.google.gson.Gson;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mongoPojo.CommentairePojo;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "CommentaireEvenement", urlPatterns = "/commentaire/evenement/*")
public class CommentaireEvenement extends HttpServlet {

  private final Gson gson = new Gson();

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");

    // Utilisation de extraireEtValiderId pour récupérer et valider l'ID de l'événement
    Integer eventId = ServletUtils.extraireEtValiderId(request.getPathInfo(), response, true);
    if (eventId == null || eventId < 0) {
      // La validation de l'ID a échoué et une réponse a déjà été envoyée
      return;
    }

    // Utilisation de l'ID validé pour récupérer les commentaires
    ArrayList<CommentairePojo> commentairePojos = model.Commentaire.getCommentairesByEvenementId(eventId);
    if (commentairePojos == null || commentairePojos.isEmpty()) {
      ServletUtils.envoyerReponseJson(response, HttpServletResponse.SC_NOT_FOUND, "{\"error\":\"Évènement non trouvé, ou pas de commentaires pour cet évènement.\"}");
      return;
    }

    ServletUtils.envoyerReponseJson(response, HttpServletResponse.SC_OK, gson.toJson(commentairePojos));
  }
}
