package ch.heigvd.pages;

import ch.heigvd.components.*;
import ch.heigvd.utils.controller.GeneralController;
import ch.heigvd.utils.structure.Account;
import ch.heigvd.utils.web.CookieManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@WebServlet(name = "Instructors", value = "/instructeurs")
public class Instructors extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Cookie usernameCookie = CookieManager.getCookie(req, "username");
        Cookie passwordCookie = CookieManager.getCookie(req, "password");
        if (usernameCookie == null || passwordCookie == null)  {
            resp.sendRedirect("/login?error=1");
            return;
        }
        Account account = Account.from(usernameCookie.getValue(), passwordCookie.getValue());
        if (account == null) return;

        PageBuilder pageBuilder = new PageBuilder(account.getUsername(), req, resp);
        pageBuilder.add(Title.doGet("Experts"));
        addInfo(pageBuilder);
        pageBuilder.add(Experts.doGet());
        pageBuilder.close();
    }

    private void addInfo(PageBuilder pageBuilder) throws IOException {

            String[] columns = {
            "cours_id",
            "jour",
            "heure",
            "description",
            "instructeur_id",
            "typecours",
            "salle_id",
    };

        List<HashMap<String, String>> instructors = new GeneralController().getInstructors();

        for (HashMap<String, String> employee : instructors) {

            List<HashMap<String, String>> liste = new GeneralController().getInstructorWeekCourses(Integer.parseInt(employee.get("instructeur_id")), columns);

            if (!liste.isEmpty()) {
                pageBuilder.add(Table.doGet(List.of(columns), liste, "Informations"));
            }
        }
    }
}



