package ch.heigvd.api;

import ch.heigvd.utils.controller.*;
import ch.heigvd.utils.entity.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;

@WebServlet(name = "RegisterMember", value = "/registermember")
public class RegisterMember extends HttpServlet {
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
                    req.getParameter("country"));
            PersonneController.create(personne);
            Membre membre = new Membre(personne.getId());
            MembreController.create(membre);
            Contrat contrat = new Contrat(
                    personne.getId(),
                    formatter.parse(req.getParameter("startDate")),
                    Integer.parseInt(req.getParameter("duration")),
                    Integer.parseInt(req.getParameter("frequency")));
            ContratController.create(contrat);
            ContratAbonnement contratAbonnement = new ContratAbonnement(
                    contrat.getContratId(),
                    req.getParameter("plan"));
            ContratAbonnementController.create(contratAbonnement);
            MoyenPaiement moyenPaiement = new MoyenPaiement(
                    req.getParameter("paymentMethod"),
                    membre.getCompteId(),
                    req.getParameter("paymentInformation"));
            MoyenPaiementController.create(moyenPaiement);
            MembreController.setMoyenPaiement(
                    MembreController.getCompte(membre.getCompteId()), moyenPaiement);
            resp.sendRedirect("/login");
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect("/register?error=db_error");
        }
    }
}