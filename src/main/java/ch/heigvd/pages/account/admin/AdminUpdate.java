package ch.heigvd.pages.account.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AdminUpdate", value = "/adminupdate")
public class AdminUpdate extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String nom = req.getParameter("nom");
        String prenom = req.getParameter("prenom");

        if (username == null || username.isEmpty()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Le nom d'utilisateur est requis");
            return;
        }

        System.out.println(username + nom + prenom);

//        if (isDeleted) {
//            resp.setStatus(HttpServletResponse.SC_OK);
//            resp.getWriter().write("Compte supprimé avec succès");
//        } else {
//            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erreur lors de la suppression");
//        }
    }
}
