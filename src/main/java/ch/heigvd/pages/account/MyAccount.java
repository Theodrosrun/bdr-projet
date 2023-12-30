package ch.heigvd.pages.account;

import ch.heigvd.components.*;
import ch.heigvd.utils.controller.GeneralController;
import ch.heigvd.utils.structure.Account;
import ch.heigvd.utils.structure.UserType;
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
        addPayingMethod(pageBuilder, account);
        addSubscription(pageBuilder, account);
        addCourses(pageBuilder, account);
        addBills(pageBuilder, account);
        addMembersUnpaid(pageBuilder, account);
        pageBuilder.close();
    }

    private void addPayingMethod(PageBuilder pageBuilder, Account account) throws IOException {
        if (account.getPayingMethodId() == null){
            return;
        }
        HashMap<String, String> method = new GeneralController().getPayingMethods(account.getPayingMethodId());
        if (method != null) {
            pageBuilder.add(
                    PayingMethod.doGet(method));
        }
    }
    private void addSubscription(PageBuilder pageBuilder, Account account) throws IOException {
        List<HashMap<String, String>> subscriptions = new GeneralController().getSubscriptions(account.getId());
        if (!subscriptions.isEmpty()) {
            pageBuilder.add(
                    Plans.doGet("My subscriptions",
                            new GeneralController().getSubscriptions(account.getId()),
                            false));
        }
    }

    private void addMembersUnpaid(PageBuilder pageBuilder, Account account) throws IOException {
        if (account.getUserType() != UserType.PersonnelAdministratif) {
            return;
        }
        String[] columns = {"compte_id", "abo_id", "contrat_id", "facture_id", "montant", "date_echeance"};
        List<HashMap<String, String>> members = new GeneralController().getMembersUnpaid();
        if (!members.isEmpty()) {
            pageBuilder.add(
                    Table.doGet(List.of(columns), members, "Members unpaid"));
        }
    }

    private void addBills(PageBuilder pageBuilder, Account account) throws IOException {
        String[] columns = {"abo_id", "contrat_id", "facture_id", "montant", "date_echeance"};
        List<HashMap<String, String>> bills = new GeneralController().getBills(account.getId(), columns);
        if (!bills.isEmpty()) {
            pageBuilder.add(Table.doGet(List.of(columns), bills, "My bills"));
        }
    }

    private void addCourses(PageBuilder pageBuilder, Account account) throws IOException {
        List<HashMap<String, String>> courses;
        String[] columns = {"jour", "heure", "description", "typecours", "salle_id", "abo_id"};
        if (account.getUserType() == UserType.Instructeur) {
            courses = new GeneralController().getInstructorWeekCourses(account.getId(), columns);
        } else if (account.getUserType() == UserType.Membre) {
            courses = new GeneralController().getMemberCourses(account.getId(), columns);
        } else {
            return;
        }
        if (!courses.isEmpty()) {
            pageBuilder.add(
                    Table.doGet(List.of(columns), courses, "Next courses"));
        }
    }
}
