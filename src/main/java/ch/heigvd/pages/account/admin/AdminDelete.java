package ch.heigvd.pages.account.admin;

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

        int result = new GeneralController().delete("DELETE FROM compte WHERE username = " + username);

//        if (isDeleted) {
//            resp.setStatus(HttpServletResponse.SC_OK);
//            resp.getWriter().write("Compte supprimé avec succès");
//        } else {
//            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erreur lors de la suppression");
//        }
    }
}
