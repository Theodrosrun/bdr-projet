package ch.heigvd.pages;

import ch.heigvd.components.*;
import ch.heigvd.utils.controller.GeneralController;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "Home", value = "/home")
public class Home extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PageBuilder pageBuilder = new PageBuilder("Home", resp.getWriter());
        pageBuilder.add(Slider.doGet());
        pageBuilder.add(AboutUs.doGet());
        pageBuilder.add(Services.doGet());
        pageBuilder.add(Classes.doGet());
        pageBuilder.add(Experts.doGet());
        pageBuilder.add(Plans.doGet("MEMBERSHIP PLANS", new GeneralController().getCurrentAbosMuscu(), true));
        pageBuilder.add(Plans.doGet("TRAINING PLANS", new GeneralController().getCurrentAbosCours(), true));
        pageBuilder.add(Register.doGet());
        pageBuilder.add(FooterBanner.doGet());
        pageBuilder.close();
    }
}
