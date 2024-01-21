package ch.heigvd.pages.account;

import ch.heigvd.components.*;
import ch.heigvd.utils.controller.MembreController;
import ch.heigvd.utils.entity.Employe;
import ch.heigvd.utils.entity.Membre;
import ch.heigvd.utils.structure.UserType;
import ch.heigvd.utils.view.AccountView;
import ch.heigvd.utils.web.CookieManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "MyAccountAdmin", value = "/myaccountadmin")
public class MyAccountAdmin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!CookieManager.isLogged(req)) {
            resp.sendRedirect("/login");
            return;
        }
        Cookie usernameCookie = CookieManager.getCookie(req, "username");
        Cookie userTypeCookie = CookieManager.getCookie(req, "userType");
        if (usernameCookie == null || userTypeCookie == null) {
            resp.sendRedirect("/login");
            return;
        }

        if (!userTypeCookie.getValue().equals(UserType.Administrateur.name())) {
            resp.sendRedirect("/myaccount");
            return;
        }

        Employe employe = MembreController.getEmployee(usernameCookie.getValue());
        AccountView accountView = MembreController.getAccountView(employe.getId());

        PageBuilder pageBuilder = new PageBuilder(accountView.getUsername(), req, resp);
        pageBuilder.add(Title.doGet("My admin account"));
        pageBuilder.add(AccountComponent.doGet(accountView));
        pageBuilder.add(AccountList.doGet());
        pageBuilder.add(CoursCreation.doGet());
        pageBuilder.add(MachineCreation.doGet());
        pageBuilder.close();
    }
}
