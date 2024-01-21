package ch.heigvd.api;

import ch.heigvd.utils.controller.GeneralController;
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
        int id = Integer.parseInt(req.getParameter("id"));
        String username = req.getParameter("username");
        String nom = req.getParameter("nom");
        String prenom = req.getParameter("prenom");
        int mode_paiement = Integer.parseInt(req.getParameter("mode_paiement"));

        if (username == null || username.isEmpty()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Le nom d'utilisateur est requis");
            return;
        }

        GeneralController controller = new GeneralController();

        // Requête compte
        final String queryCompte = "UPDATE compte SET moyen_paiement_pref_id = ? WHERE username = ?;";
        int resultCompte = controller.executeUpdate(queryCompte, mode_paiement, username);
        if (resultCompte > 0) {
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().write("Compte mis à jour avec succès");
        } else {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erreur lors de la mise à jour");
        }

        // Requête personne
        final String queryPersonne = "UPDATE personne SET nom = ?, prenom = ? WHERE id = ?;";
        int resultPersonne = controller.executeUpdate(queryPersonne, nom, prenom, id);
        if (resultPersonne > 0) {
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().write("personne mis à jour avec succès");
        } else {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erreur lors de la mise à jour");
        }

    }
}
