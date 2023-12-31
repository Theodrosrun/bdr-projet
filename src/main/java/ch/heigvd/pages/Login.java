package ch.heigvd.pages;

import ch.heigvd.components.*;
import ch.heigvd.utils.structure.Account;
import ch.heigvd.utils.web.CookieManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/***
 * Variable isConnected dans menu.ftlh ?
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
        Account account = Account.from(username, password); // comparaison avec le compte associé
        if (account == null) {
            resp.sendRedirect("/login?error=1");
            return;
        }
        CookieManager.setCookie(resp, req, "username", username);
        CookieManager.setCookie(resp, req, "password", password);
        resp.sendRedirect("/myaccount");
    }
}
