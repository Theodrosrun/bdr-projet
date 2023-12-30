package ch.heigvd.pages;

import ch.heigvd.components.PageBuilder;
import ch.heigvd.components.RegisterForm;
import ch.heigvd.utils.structure.Account;
import ch.heigvd.utils.web.CookieManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "Register", value = "/register")
public class Register extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (CookieManager.isLogged(req)) {
            resp.sendRedirect("/myaccount");
            return;
        }
        PageBuilder pageBuilder = new PageBuilder("Register", req, resp);
        pageBuilder.add(RegisterForm.doGet(req));
        pageBuilder.add("<div class=\"text-center mt-4\">\n" +
                "                <p class=\"text-gray-500 text-sm\">\n" +
                "                    Already have an account?\n" +
                "                    <a href=\"/login\" class=\"font-medium text-indigo-600 hover:text-indigo-500\">\n" +
                "                        Sign in\n" +
                "                    </a>\n" +
                "                </p>\n" +
                "            </div>");
        pageBuilder.close();
    }
}
