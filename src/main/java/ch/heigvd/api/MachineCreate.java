package ch.heigvd.api;

import ch.heigvd.utils.controller.GeneralController;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "MachineCreate", value = "/machinescreate")
public class MachineCreate extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int fitnessId = Integer.parseInt(req.getParameter("fitness_id"));
        String salleId = req.getParameter("salle_id");
        String etat = req.getParameter("etat");
        String type_machine = req.getParameter("type_machine");

        final String query = "INSERT INTO machine (fitness_id, salle_id, etat, type_machine) VALUES (?, ?, ?, ?);";

        GeneralController controller = new GeneralController();
        int result = controller.executeUpdate(query, fitnessId, salleId, etat, type_machine);

        if (result > 0) {
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.sendRedirect("/myaccountadmin");
        } else {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erreur lors de l'ajout du cours");
        }
    }
}
