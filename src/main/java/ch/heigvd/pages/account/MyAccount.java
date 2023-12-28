package ch.heigvd.pages.account;

import ch.heigvd.components.AccountComponent;
import ch.heigvd.components.PageBuilder;
import ch.heigvd.components.Title;
import ch.heigvd.utils.structure.Account;
import ch.heigvd.utils.web.CookieManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "MyAccount", value = "/myaccount")
public class MyAccount extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie usernameCookie =  CookieManager.getCookie(req, "username");
        Cookie passwordCookie = CookieManager.getCookie(req, "password");
        if (usernameCookie == null || passwordCookie == null) {
            resp.sendRedirect("/login");
            return;
        }
        Account account = Account.from(usernameCookie.getValue(), passwordCookie.getValue());
        if (account == null) {
            resp.sendRedirect("/login");
            return;
        }
        PageBuilder pageBuilder = new PageBuilder(account.getUsername(), resp.getWriter());
        pageBuilder.add(Title.doGet("My account"));
        pageBuilder.add(AccountComponent.doGet(account));
        pageBuilder.close();
    }
}
