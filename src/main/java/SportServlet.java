import donnees.Sportif;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

@WebServlet(name = "sportServlet", value = "/sport-servlet")
public class SportServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public List<Sportif> getListeSportifs() {
        // requï¿½te JPQL pour rï¿½cupï¿½rer les sportifs dans la BDD
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SportsPU");
        EntityManager em = emf.createEntityManager();
        Query requete = em.createQuery("SELECT s FROM Sportif s");
        return (List<Sportif>) requete.getResultList();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String operation = request.getParameter("operation");
        if (operation.equals("listeSportif")) {
            // rï¿½cupï¿½re la liste des sportifs et l'associe ï¿½ la requï¿½te HTTP
            request.setAttribute("sportifs", this.getListeSportifs());
            // forwarde la requï¿½te ï¿½ la page JSP
            getServletConfig().getServletContext().getRequestDispatcher("/afficheSportifs.jsp")
                    .forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}