package ch.heigvd.pages;

import ch.heigvd.components.*;
import ch.heigvd.utils.controller.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/***
 * Liste des classes
 */
@WebServlet(name = "ClassesTimetable", value = "/classestimetable")
public class ClassesTimetable extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PageBuilder pageBuilder = new PageBuilder("ClassesTimetable", req, resp);
        pageBuilder.add(Classes.doGet());
        pageBuilder.add(Experts.doGet());
        pageBuilder.add(TimeTable.doGet(MainController.getHoraireCoursView()));
        pageBuilder.close();
    }

}
