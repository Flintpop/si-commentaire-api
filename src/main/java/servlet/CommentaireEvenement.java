package servlet;

import com.google.gson.Gson;
import routes.Routes;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet pour gérer les commentaires des événements.
 * get est utilisé pour récupérer les commentaires d'un événement.
 */
@WebServlet(name = "CommentaireEvenement", value = Routes.evenementCommentaire)
public class CommentaireEvenement extends HttpServlet {

  private final Gson gson = new Gson();

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

  }

}
