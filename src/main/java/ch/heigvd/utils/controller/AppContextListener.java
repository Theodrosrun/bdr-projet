package ch.heigvd.utils.controller;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

/**
 * AppContextListener est un ServletContextListener qui gère le cycle de vie des entités JPA.
 * Une entité JPA (Java Persistence API) est une classe Java associée à une table de base de données,
 * elle est utilisée pour représenter et gérer les données persistantes.
 * dans le contexte de l'application web.
 */
@WebListener
public class AppContextListener implements ServletContextListener {
    private static EntityManagerFactory emf;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        emf = Persistence.createEntityManagerFactory("myAmazingFitness");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        if (emf != null) {
            emf.close();
        }
    }

    public static EntityManager createEntityManager() {
        if (emf != null) {
            return emf.createEntityManager();
        }
        throw new IllegalStateException("Context is not initialized yet.");
    }
}
