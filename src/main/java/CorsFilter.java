import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

//@WebFilter("/*")
public class CorsFilter implements Filter {

  @Override
  public void init(FilterConfig filterConfig) {}

  @Override
  public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
    System.out.println("CORS Filter executed"); // Log pour débogage
    HttpServletResponse response = (HttpServletResponse) res;
    HttpServletRequest request = (HttpServletRequest) req;

    // Autorise l'accès de n'importe quelle origine
    response.setHeader("Access-Control-Allow-Origin", "*");

    // Autorise les méthodes HTTP courantes et plus
    response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT, PATCH, HEAD");

    // Période pendant laquelle la réponse au pré-vol peut être mise en cache
    response.setHeader("Access-Control-Max-Age", "3600");

    // Autorise tous les headers
    response.setHeader("Access-Control-Allow-Headers", "*");

    // Pour les requêtes préliminaires OPTIONS, renvoie simplement le statut OK sans passer la requête plus loin
    if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
      response.setStatus(HttpServletResponse.SC_OK);
    } else {
      chain.doFilter(req, res);
    }
  }

  @Override
  public void destroy() {}
}
