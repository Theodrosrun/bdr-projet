package ch.heigvd.pages;

import ch.heigvd.components.PageBuilder;
import ch.heigvd.components.RegisterForm;
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
        Integer zipCode = Integer.parseInt(req.getParameter("zipCode"));
        String country = req.getParameter("country");
        String dateOfBirth = req.getParameter("dateOfBirth");
        Integer numero = Integer.parseInt(req.getParameter("numero"));

        // Vérification si les champs requis sont vides
        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || phoneNumber.isEmpty()
                || city.isEmpty() || street.isEmpty()  || country.isEmpty() || dateOfBirth.isEmpty() ) {
            resp.sendRedirect("/register?error=fields_empty");
            return;
        }

        try {
            SQLManager sqlManager = new SQLManager(
                    "bdr",
                    "bdr",
                    "jdbc:postgresql://localhost:5432/bdr",
                    "my_amazing_fitness");

            // Création de la liste de colonnes et de valeurs pour l'insertion
            List<String> columns = Arrays.asList("nom", "prenom", "dateNaissance", "adresseMail", "numeroTelephone", "numero", "rue", "ville", "NPA", "pays");
            List<Object> values = Arrays.asList(firstName, lastName, dateOfBirth, email, phoneNumber, numero, street, city, zipCode, country);

            sqlManager.insert(Table.Personne.name(), columns, values);

        } catch (Exception e) {
            // Gestion des exceptions ou des erreurs lors de l'insertion
            e.printStackTrace();
            resp.sendRedirect("/register?error=db_error");
        }
        resp.sendRedirect("/home");
    }
}
