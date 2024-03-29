package ch.heigvd.pages.account;

import ch.heigvd.components.*;
import ch.heigvd.utils.controller.MembreController;
import ch.heigvd.utils.entity.Membre;
import ch.heigvd.utils.entity.MoyenPaiement;
import ch.heigvd.utils.structure.UserType;
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
@WebServlet(name = "MyAccount", value = "/myaccount")
public class MyAccount extends HttpServlet {

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
        Cookie userTypeCookie = CookieManager.getCookie(req, "userType");
        if (usernameCookie == null || userTypeCookie == null) {
            resp.sendRedirect("/login");
            return;
        }
        boolean isAdmnin = userTypeCookie.getValue().equals(UserType.Administrateur.name());
        if (isAdmnin) {
            resp.sendRedirect("/myaccountadmin");
            return;
        }
        Membre membre = MembreController.getMembre(usernameCookie.getValue());
        AccountView accountView = MembreController.getAccountView(membre.getId());
        PageBuilder pageBuilder = new PageBuilder(accountView.getUsername(), req, resp);
        pageBuilder.add(Title.doGet("My account"));
        pageBuilder.add(AccountComponent.doGet(accountView));
        addPayingMethod(pageBuilder, accountView);
        addSubscription(pageBuilder, accountView);
        addCourses(pageBuilder, accountView);
        addBills(pageBuilder, accountView);
        pageBuilder.close();
    }

    /***
     * Affichage du moyen de payement
     * @param pageBuilder la page web où va être inscrit l'information
     * @param accountView le compte connecté
     */
    private void addPayingMethod(PageBuilder pageBuilder, AccountView accountView) throws IOException {
        if (accountView.getMoyen_paiement_pref_id().isEmpty()) {
            return;
        }
        MoyenPaiement method = MembreController.getMoyenPayment(Integer.parseInt(accountView.getMoyen_paiement_pref_id()));
        if (method != null) {
            pageBuilder.add(PayingMethod.doGet(method));
        }
    }

    /**
     * Affichage de la liste de tous les abonnements du membre
     * @param pageBuilder la page web où va être inscrit l'information
     * @param accountView le compte connecté
     */
    private void addSubscription(PageBuilder pageBuilder, AccountView accountView) throws IOException {
        List<MembreAbonnementView> subscriptions = MembreController.getAbonnementsView(Integer.parseInt(accountView.getId()));
        if (!subscriptions.isEmpty()) {
            pageBuilder.add(Plans.doGet("My subscriptions", subscriptions, false));
        }
    }


    /***
     * Récupération des informations sur les factures relatives au membre
     * @param pageBuilder la page web où va être inscrit l'information
     * @param account le compte connecté
     */
    private void addBills(PageBuilder pageBuilder, AccountView account) throws IOException {
        List<?> bills = MembreController.getMembreFactureView(Integer.parseInt(account.getId()));
        if (!bills.isEmpty()) {
            pageBuilder.add(
                    Table.doGet(
                            Arrays.stream(
                                    MembreFactureView.class.getDeclaredFields())
                                    .map(Field::getName)
                                    .toList(), bills, "My bills")
            );
        }
    }

    /***
     * Affichage des cours en fonction du type de la personne (membre ou instructeur)
     * @param pageBuilder la page web où va être inscrit l'information
     * @param account le compte connecté
     */
    private void addCourses(PageBuilder pageBuilder, AccountView account) throws IOException {
        List<?> courses = MembreController.getMemberCourseWeekView(Integer.parseInt(account.getId()));
        List<String> fields = new ArrayList<>(
                Arrays.stream(MemberCourseWeekView.class.getSuperclass().getDeclaredFields())
                .map(Field::getName)
                .toList());
        fields.addAll(Arrays.stream(Arrays.stream(MemberCourseWeekView.class.getDeclaredFields())
                                .map(Field::getName)
                                .toArray(String[]::new))
                .toList());
        if (!courses.isEmpty()) {
            pageBuilder.add(Table.doGet(fields, courses, "My courses"));
        }
    }
}
