package ch.heigvd.api;

import ch.heigvd.utils.controller.GeneralController;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "CoursCreate", value = "/courscreate")
public class CoursCreate extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SimpleDateFormat formatterDate = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat formatterHour = new SimpleDateFormat("HH:mm:ss");

        java.sql.Date jourSql = null;
        java.sql.Time heureSql = null;

        try {
            java.util.Date jour = formatterDate.parse(req.getParameter("jour"));
            jourSql = new java.sql.Date(jour.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        try {
            java.util.Date heure = formatterHour.parse(req.getParameter("heure") + ":00");
            heureSql = new java.sql.Time(heure.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        int duree = Integer.parseInt(req.getParameter("duree"));
        String description = req.getParameter("description");
        String typeCours = req.getParameter("typecours");
        int fitnessId = Integer.parseInt(req.getParameter("fitness_id"));
        String salleId = req.getParameter("salle_id");
        String aboId = req.getParameter("abo_id");

        final String query = "INSERT INTO cours (jour, heure, duree, description, typecours, fitness_id, salle_id, abo_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";

        GeneralController controller = new GeneralController();
        int result = controller.executeUpdate(query, jourSql, heureSql, duree, description, typeCours, fitnessId, salleId, aboId);

        if (result > 0) {
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().write("Nouveau cours disponible !");
        } else {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erreur lors de l'ajout du cours");
        }
    }
}
