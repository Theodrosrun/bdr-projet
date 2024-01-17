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
        if (CookieManager.mustLogin(req)) {
            resp.sendRedirect("/login");
            return;
        }
        Cookie usernameCookie = CookieManager.getCookie(req, "username");
        Cookie passwordCookie = CookieManager.getCookie(req, "password");
        if (usernameCookie == null || passwordCookie == null)
            return;
        Account account = Account.from(usernameCookie.getValue(), passwordCookie.getValue());
        if (account == null)
            return;
        PageBuilder pageBuilder = new PageBuilder(account.getUsername(), req, resp);
        pageBuilder.add(Title.doGet("My account"));
        pageBuilder.add(AccountComponent.doGet(account)); // affichage de toutes les caractéristiques du compte

        addPayingMethod(pageBuilder, account);
        addSubscription(pageBuilder, account);
        addCourses(pageBuilder, account);
        addBills(pageBuilder, account);
        addMembersUnpaid(pageBuilder, account);

        pageBuilder.close();
    }

    /***
     * Affichage du moyen de payement
     * @param pageBuilder la page web où va être inscrit l'information
     * @param account le compte connecté
     */
    private void addPayingMethod(PageBuilder pageBuilder, Account account) throws IOException {
        if (account.getPayingMethodId() == null) { // le getter existe automatiquement
            return;
        }
        HashMap<String, String> method = new GeneralController().getPayingMethods(account.getPayingMethodId());
        if (method != null) {
            pageBuilder.add(PayingMethod.doGet(method));
        }
    }

    /**
     * Affichage de la liste de tous les abonnements du membre
     * @param pageBuilder la page web où va être inscrit l'information
     * @param account le compte connecté
     */
    private void addSubscription(PageBuilder pageBuilder, Account account) throws IOException {
        List<HashMap<String, String>> subscriptions = new GeneralController().getPlans(account.getId());
        if (!subscriptions.isEmpty()) {
            pageBuilder.add(Plans.doGet("My subscriptions",
                            new GeneralController().getPlans(account.getId()),
                            false));
        }
    }

    /***
     * Affichage des membres qui ne paient pas seulement si la personne appartient au personnel administratif
     * @param pageBuilder la page web où va être inscrit l'information
     * @param account le compte connecté
     */
    private void addMembersUnpaid(PageBuilder pageBuilder, Account account) throws IOException {
        if (account.getUserType() != UserType.PersonnelAdministratif) { // le getter existe automatiquement
            return;
        }
        String[] columns = {"compte_id", "abo_id", "contrat_id", "facture_id", "montant", "date_echeance"};
        List<HashMap<String, String>> members = new GeneralController().getMembersUnpaid();
        if (!members.isEmpty()) {
            pageBuilder.add(Table.doGet(List.of(columns), members, "Members unpaid")); // à l'image de "MEMBERSHIP PLANS" de /home
        }
    }

    /***
     * Récupération des informations sur les factures relatives au membre
     * @param pageBuilder la page web où va être inscrit l'information
     * @param account le compte connecté
     */
    private void addBills(PageBuilder pageBuilder, Account account) throws IOException {
        String[] columns = {"abonnement", "facture", "montant", "echeance"};
        List<HashMap<String, String>> bills = new GeneralController().getBills(account.getId(), columns);
        if (!bills.isEmpty()) {
            pageBuilder.add(Table.doGet(List.of(columns), bills, "My bills"));
        }
    }

    /***
     * Affichage des cours en fonction du type de la personne (membre ou instructeur)
     * @param pageBuilder la page web où va être inscrit l'information
     * @param account le compte connecté
     */
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
            pageBuilder.add(Table.doGet(List.of(columns), courses, "Next courses"));
        }
    }
}
