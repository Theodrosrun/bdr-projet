package ch.heigvd.pages;

import ch.heigvd.utils.web.CookieManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/***
 * Déconnexion
 */
@WebServlet(name = "Logout", value = "/logout")
public class Logout extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CookieManager.deleteCookie(resp, req, "username");
        resp.sendRedirect("/login"); // redirection immédiate sur /home
    }
}
