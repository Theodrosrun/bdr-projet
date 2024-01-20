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
        String username = req.getParameter("username");
        int mode_paiement = Integer.parseInt(req.getParameter("mode_paiement"));

        if (username == null || username.isEmpty()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Le nom d'utilisateur est requis");
            return;
        }

        final String query = "UPDATE compte SET moyen_paiement_pref_id = ? WHERE username = ?;";

        GeneralController controller = new GeneralController();
        int result = controller.executeUpdate(query, mode_paiement, username);

        if (result > 0) {
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().write("Compte mis à jour avec succès");
        } else {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erreur lors de la mise à jour");
        }
    }

}
