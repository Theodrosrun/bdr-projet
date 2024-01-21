package ch.heigvd.pages;

import ch.heigvd.components.*;
import ch.heigvd.utils.controller.MembreController;
import ch.heigvd.utils.entity.Administrateur;
import ch.heigvd.utils.entity.Compte;
import ch.heigvd.utils.entity.Employe;
import ch.heigvd.utils.entity.Membre;
import ch.heigvd.utils.structure.UserType;
import ch.heigvd.utils.view.AccountView;
import ch.heigvd.utils.web.CookieManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/***
 * Connexion
 */
@WebServlet(name = "Login", value = "/login")
public class Login extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (CookieManager.isLogged(req)) {
            resp.sendRedirect("/myaccount");
            return;
        }
        PageBuilder pageBuilder = new PageBuilder("Login", req, resp);
        pageBuilder.add(LoginForm.doGet());
        if (req.getParameter("error") != null) {
            pageBuilder.add("<div class=\"text-center mt-4\">\n" +
                    "                <p class=\"text-red-500 text-sm\">\n" +
                    "                    Username or password incorrect\n" +
                    "                </p>\n" +
                    "            </div>");
        }
        pageBuilder.add("<div class=\"text-center mt-4\">\n" +
                "                <p class=\"text-gray-500 text-sm\">\n" +
                "                    Don't have an account yet?\n" +
                "                    <a href=\"/register\" class=\"font-medium text-indigo-600 hover:text-indigo-500\">\n" +
                "                        Sign up\n" +
                "                    </a>\n" +
                "                </p>\n" +
                "            </div>");
        pageBuilder.close();
    }

    /***
     * Cette méthode est utilisée pour gérer les requêtes HTTP de type POST.
     * Pour qu'elle marche, il faut mettre les réelles informations présentes dans la table Compte
     *
     * @param req an {@link HttpServletRequest} object that contains the request the client has made of the servlet
     * @param resp an {@link HttpServletResponse} object that contains the response the servlet sends to the client
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username").trim();
        String password = req.getParameter("password").trim();
        Compte account = MembreController.getCompte(username);
        if (account == null || !account.getMotDePasse().equals(password)) {
            resp.sendRedirect("/login?error=1");
            return;
        }
        int personneId;
        try {
            personneId = MembreController.getMembre(account.getUsername()).getId();
        } catch (Exception e) {
            personneId = MembreController.getEmployee(account.getUsername()).getId();
        }
        AccountView accountView = MembreController.getAccountView(personneId);
        if (accountView.getUserType().equals(UserType.Administrateur.name())) {
            CookieManager.setCookie(resp, req, "username", username);
            CookieManager.setCookie(resp, req, "userType", accountView.getUserType());
            resp.sendRedirect("/myaccountadmin");
            return;
        }
        if(accountView.getUserType().equals(UserType.Membre.name())) {
            CookieManager.setCookie(resp, req, "username", username);
            CookieManager.setCookie(resp, req, "userType", accountView.getUserType());
            resp.sendRedirect("/myaccount");
            return;
        }
        resp.sendRedirect("/login?error=1");
    }
}
