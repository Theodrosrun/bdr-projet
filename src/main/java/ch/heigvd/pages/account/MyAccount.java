package ch.heigvd.pages.account;

import ch.heigvd.components.AccountComponent;
import ch.heigvd.components.PageBuilder;
import ch.heigvd.components.Plans;
import ch.heigvd.components.Title;
import ch.heigvd.utils.controller.GeneralController;
import ch.heigvd.utils.structure.Account;
import ch.heigvd.utils.web.CookieManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@WebServlet(name = "MyAccount", value = "/myaccount")
public class MyAccount extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (CookieManager.mustLogin(req)) {
            resp.sendRedirect("/login");
            return;
        }
        Cookie usernameCookie =  CookieManager.getCookie(req, "username");
        Cookie passwordCookie = CookieManager.getCookie(req, "password");
        Account account = Account.from(usernameCookie.getValue(), passwordCookie.getValue());
        PageBuilder pageBuilder = new PageBuilder(account.getUsername(), req, resp);
        pageBuilder.add(Title.doGet("My account"));
        pageBuilder.add(AccountComponent.doGet(account));
        List<HashMap<String, String>> subscriptions = new GeneralController().getSubscriptions(account.getId());
        if (!subscriptions.isEmpty()) {
            pageBuilder.add(
                    Plans.doGet("My subscriptions",
                            new GeneralController().getSubscriptions(account.getId()),
                            false));
        }
        pageBuilder.close();
    }
}
