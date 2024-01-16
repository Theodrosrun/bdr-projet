package ch.heigvd.pages;

import ch.heigvd.components.PageBuilder;
import ch.heigvd.components.RegisterForm;
import ch.heigvd.utils.controller.*;
import ch.heigvd.utils.db.SQLManager;
import ch.heigvd.utils.entity.Contrat;
import ch.heigvd.utils.entity.ContratAbonnement;
import ch.heigvd.utils.entity.Membre;
import ch.heigvd.utils.entity.Personne;
import ch.heigvd.utils.web.CookieManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ch.heigvd.utils.structure.Table;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static ch.heigvd.components.RegisterForm.PERSON_PARAM_NAMES;

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
     * Réception des données renseignées par l'utilisateur qui souhaite s'enregistrer.
     * Réalisation d'une insertion dans la BDD dans différentes tables.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Personne personne = new Personne(
                    req.getParameter("lastname"),
                    req.getParameter("name"),
                    formatter.parse(req.getParameter("dateOfBirth")),
                    req.getParameter("email"),
                    req.getParameter("mobile"),
                    req.getParameter("street"),
                    req.getParameter("number"),
                    req.getParameter("city"),
                    Integer.parseInt(req.getParameter("zipCode")),
                    req.getParameter("country")
            );
            PersonneController.create(personne);
            Membre membre = new Membre(personne.getId());
            MembreController.create(membre);
            Contrat contrat = new Contrat(
                    personne.getId(),
                    formatter.parse(req.getParameter("startDate")),
                    Integer.parseInt(req.getParameter("duration")),
                    Integer.parseInt(req.getParameter("frequency"))
            );
            ContratController.create(contrat);
            ContratAbonnement contratAbonnement = new ContratAbonnement(
                    contrat.getContratId(),
                    req.getParameter("plan")
            );
            ContratAbonnementController.create(contratAbonnement);

        } catch (Exception e) {
            // Gestion des exceptions ou des erreurs lors de l'insertion
            e.printStackTrace();
            resp.sendRedirect("/register?error=db_error");
        }
    }
}
