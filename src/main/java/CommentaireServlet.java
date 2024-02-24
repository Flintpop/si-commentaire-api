import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mongoPojo.CommentairePojo;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import static model.Commentaire.getListeCommentaires;

@WebServlet(name = "commentaireServlet", value = "/commentaire-servlet")
public class CommentaireServlet extends HttpServlet {

  public void init() {
  }

  private final Gson gson = new Gson();

  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    // Simule la récupération des commentaires depuis une source de données
    List<CommentairePojo> commentairePojos = getListeCommentaires();

    // Convertit la liste des commentaires en JSON
    String commentairesJson = gson.toJson(commentairePojos);

    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");
    response.getWriter().write(commentairesJson);
    response.getWriter().flush();
  }

  public void destroy() {
  }
}