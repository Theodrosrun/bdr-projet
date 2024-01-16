package ch.heigvd.pages;

import ch.heigvd.utils.controller.AppContextListener;
import ch.heigvd.utils.controller.PersonneController;
import ch.heigvd.utils.entity.Personne;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@WebServlet("/TestDB")
public class TestDBServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Réponse au format texte

        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();

        try {
            // Créer une factory d'EntityManager pour notre unité de persistance
            EntityManager em = AppContextListener.createEntityManager();
            Personne personne = new Personne("nom", "prenom", new Date(), "mail", "numero", "rue", "1", "ville", 1, "pays");
            PersonneController.create(personne);

            // Fermer l'EntityManager et la factory
            em.close();
        } catch (Exception e) {
            e.printStackTrace(out);
            out.println("Échec de la connexion à la base de données.");
        }
    }
}
