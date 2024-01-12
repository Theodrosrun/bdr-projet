package ch.heigvd.pages;

import ch.heigvd.components.PageBuilder;
import ch.heigvd.components.RegisterForm;
import ch.heigvd.utils.controller.GeneralController;
import ch.heigvd.utils.db.SQLManager;
import ch.heigvd.utils.web.CookieManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ch.heigvd.utils.structure.Table;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/***
 * Variable isConnected dans menu.ftlh ?
 */
@WebServlet(name = "Register", value = "/register")
public class Register extends HttpServlet {

    /***
     * Cette méthode est utilisée pour gérer les requêtes HTTP de type GET.
     * Elle permet au servlet de récupérer des informations à partir de l'URL.
     *
     * @param req an {@link HttpServletRequest} object that contains the request the client has made of the servlet
     * @param resp an {@link HttpServletResponse} object that contains the response the servlet sends to the client
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (CookieManager.isLogged(req)) {  // si enregistré redirection immédiate sur /myaccount
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

    /***
     * Cette fonction ne marche pas pour l'instant .... il faut analyser plutôt comment faire un insert stupide ds la BDD
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        // Récupérer les paramètres du formulaire
        List<Object> personValues = new ArrayList<>();
        List<Object> contractValues = new ArrayList<>();
        List<Object> contratAbonnementValues = new ArrayList<>();
        List<Object> paymentMethodValues = new ArrayList<>();
        for (String personParam : RegisterForm.PERSON_PARAM_NAMES) {
            personValues.add(req.getParameter(personParam));
        }
        for (String contractParam : RegisterForm.CONTRACT_PARAM_NAMES) {
            contractValues.add(req.getParameter(contractParam));
        }
        for (String memberContractParam : RegisterForm.MEMBER_CONTRACT_PARAM_NAMES) {
            contratAbonnementValues.add(req.getParameter(memberContractParam));
        }
        for (String paymentMethodParam : RegisterForm.PAYMENT_METHOD_PARAM_NAMES) {
            paymentMethodValues.add(req.getParameter(paymentMethodParam));
        }

        try {
            GeneralController generalController = new GeneralController();
            List<String> personneColumns = generalController.getColumns(Table.Personne.name(),
                    "column_default IS NULL AND is_nullable = 'NO'");
            int personneId = (int) generalController.insert(Table.Personne.name(), personneColumns, personValues, "id");
            String compteId =  (String) generalController.insert(Table.Membre.name(), List.of("id"), List.of(personneId), "compte_id");
            List<String> contratColumns = generalController.getColumns(Table.Contrat.name(),
                    "is_nullable = 'NO' AND column_name <> 'contrat_id'");
            contractValues.add(0, compteId);
            int contratId = (int) generalController.insert(Table.Abonnement.name(), contratColumns, contractValues, "contrat_id");
            List<String> contratAbonnementColumns = generalController.getColumns(Table.ContratAbonnement.name(),
                    "is_nullable = 'NO'");
            contratAbonnementValues.add(0, contratId);
            generalController.insert(Table.ContratAbonnement.name(), contratAbonnementColumns, contratAbonnementValues, null);


        } catch (Exception e) {
            // Gestion des exceptions ou des erreurs lors de l'insertion
            e.printStackTrace();
            resp.sendRedirect("/register?error=db_error");
        }
    }
}
