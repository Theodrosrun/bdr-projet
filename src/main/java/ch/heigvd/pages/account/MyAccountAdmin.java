package ch.heigvd.pages.account;

import ch.heigvd.components.*;
import ch.heigvd.utils.controller.GeneralController;
import ch.heigvd.utils.controller.MembreController;
import ch.heigvd.utils.entity.Membre;
import ch.heigvd.utils.entity.MoyenPaiement;
import ch.heigvd.utils.view.AccountView;
import ch.heigvd.utils.view.MemberCourseWeekView;
import ch.heigvd.utils.view.MembreAbonnementView;
import ch.heigvd.utils.view.MembreFactureView;
import ch.heigvd.utils.web.CookieManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/***
 * Affichage de la page utilisateur
 */
@WebServlet(name = "MyAccountAdmin", value = "/myaccountadmin")
public class MyAccountAdmin extends HttpServlet {

    /***
     * Cette méthode est utilisée pour gérer les requêtes HTTP de type GET.
     * Elle permet au servlet de récupérer des informations à partir de l'URL.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!CookieManager.isLogged(req)) {
            resp.sendRedirect("/login");
            return;
        }

        Cookie usernameCookie = CookieManager.getCookie(req, "username");

        if (usernameCookie == null) {
            resp.sendRedirect("/login");
            return;
        }

        Membre membre = MembreController.getMembre(usernameCookie.getValue());
        AccountView accountView = MembreController.getAccountView(membre.getId());

        PageBuilder pageBuilder = new PageBuilder(accountView.getUsername(), req, resp);
        pageBuilder.add(Title.doGet("My admin account"));
        pageBuilder.add(AccountComponent.doGet(accountView));
        pageBuilder.add(AccountList.doGet());
        pageBuilder.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
