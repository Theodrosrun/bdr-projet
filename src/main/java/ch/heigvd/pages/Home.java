package ch.heigvd.pages;

import ch.heigvd.components.*;
import ch.heigvd.utils.controller.GeneralController;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/***
 * Page principale
 */
@WebServlet(name = "Home", value = "/home")
public class Home extends HttpServlet {

    /***
     * Cette méthode est utilisée pour gérer les requêtes HTTP de type GET.
     * Elle permet au servlet de récupérer des informations à partir de l'URL.
     * Affichage universel
     * Un onglet logout apparaîtra lors de la connexion
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PageBuilder pageBuilder = new PageBuilder("Home", req, resp);
        pageBuilder.add(Slider.doGet());
        pageBuilder.add(AboutUs.doGet());
        pageBuilder.add(Services.doGet());
        pageBuilder.add(Classes.doGet());
        pageBuilder.add(Experts.doGet());
        //pageBuilder.add(Plans.doGet("MEMBERSHIP PLANS", new GeneralController().getGymPlans(), true));
        pageBuilder.add(RegisterForm.doGet(req));
        pageBuilder.add(TimeTable.doGet());
        pageBuilder.add(HistogramChart.doGet());
        pageBuilder.add(FooterBanner.doGet());
        pageBuilder.close();
    }
}
