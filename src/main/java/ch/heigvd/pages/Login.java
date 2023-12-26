package ch.heigvd.pages;

import ch.heigvd.components.*;
import ch.heigvd.utils.controller.GeneralController;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "Login", value = "/login")
public class Login extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PageBuilder pageBuilder = new PageBuilder("Login", resp.getWriter());
        pageBuilder.add(LoginForm.doGet());
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
}
