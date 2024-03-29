package ch.heigvd.api;

import ch.heigvd.utils.controller.GeneralController;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AdminDelete", value = "/admindelete")
public class AdminDelete extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");

        if (username == null || username.isEmpty()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Le nom d'utilisateur est requis");
            return;
        }

        final String query = "DELETE FROM compte WHERE username = ?;";

        GeneralController controller = new GeneralController();
        int result = controller.executeUpdate(query, username);

        if (result > 0) {
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().write("Compte supprimé avec succès");
        } else {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Aucun username associé n'a été trouvé");
        }
    }
}
