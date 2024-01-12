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
import java.util.Arrays;
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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Récupérer les paramètres du formulaire
        String firstName = req.getParameter("name");
        String lastName = req.getParameter("lastname");
        String email = req.getParameter("email");
        String phoneNumber = req.getParameter("mobile");
        String city = req.getParameter("city");
        String street = req.getParameter("street");
        String country = req.getParameter("country");
        String dateOfBirth = req.getParameter("dateOfBirth");

        int zipCode;
        int numero;

        // Vérification si les champs numériques peuvent être convertis en entier
        try {
            zipCode = Integer.parseInt(req.getParameter("zipCode"));
            numero = Integer.parseInt(req.getParameter("numero"));
        } catch (NumberFormatException e) {
            // Gérer le cas où la conversion en entier échoue
            resp.sendRedirect("/register?error=numeric_fields_invalid");
            return;
        }

        // Vérification si les champs requis sont vides
        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || phoneNumber.isEmpty()
                || city.isEmpty() || street.isEmpty()  || country.isEmpty() || dateOfBirth.isEmpty()
                || firstName.contains("'") || lastName.contains("'") || email.contains("'")
                || phoneNumber.contains("'") || city.contains("'") || street.contains("'")
                || country.contains("'") || dateOfBirth.contains("'")) {
            resp.sendRedirect("/register?error=fields_empty");
            return;
        }

        try {
            // Création de la liste de colonnes et de valeurs pour l'insertion
            List<String> columns = Arrays.asList("nom", "prenom", "dateNaissance", "adresseMail", "numeroTelephone", "numero", "rue", "ville", "NPA", "pays");
            List<Object> values = Arrays.asList(firstName, lastName, dateOfBirth, email, phoneNumber, numero, street, city, zipCode, country);

            int personneId = new GeneralController().insert(Table.Personne.name(), columns, values);

            // Création de la liste de colonnes et de valeurs pour l'insertion
            List<String> columnsMembre = List.of("id");
            List<Object> valuesMembre = List.of(personneId);

            new GeneralController().insert(Table.Membre.name(), columnsMembre, valuesMembre);

            // Création de la liste de colonnes et de valeurs pour l'insertion d'un contrat
            List<String> columnsContrat = Arrays.asList("membre_id", "date_debut", "date_fin", "frequence_paiement");
            List<Object> valuesContrat = Arrays.asList(personneId, "CURRENT_DATE", "2025-01-01", 1); // frequence de paiement à questionner et date de fin aussi

            // Insertion du contrat et récupération de son identifiant
            int contrat_id = new GeneralController().insert(Table.Contrat.name(), columnsContrat, valuesContrat);

            // Création de la liste de colonnes et de valeurs pour l'insertion d'un moyen de paiement
            List<String> columnsMoyenPaiement = Arrays.asList("type_moyen_paiement", "compte_id", "info");
            List<Object> valuesMoyenPaiement = Arrays.asList("Carte credit", firstName + "_" + lastName, "1111-5678-9012-3456"); // info à donner et type moyen de paiement aussi

            // Insertion du moyen de paiement
            new GeneralController().insert(Table.MoyenPaiement.name(), columnsMoyenPaiement, valuesMoyenPaiement);

        } catch (Exception e) {
            // Gestion des exceptions ou des erreurs lors de l'insertion
            e.printStackTrace();
            resp.sendRedirect("/register?error=db_error");
        }
    }
}
