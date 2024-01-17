package ch.heigvd.utils.controller;

import ch.heigvd.utils.entity.ContratAbonnement;
import jakarta.persistence.EntityManager;

/***
 * Cette classe gère les opérations de persistance pour l'entité ContratAbonnement.
 * Elle est responsable de l'ajout de nouveaux contrats d'abonnement dans la base de données
 * et de la mise à jour des contrats d'abonnement existants.
 */
public class ContratAbonnementController {
    static final EntityManager em = AppContextListener.createEntityManager();

    public static void create(ContratAbonnement contratAbonnement) {
        em.getTransaction().begin();
        em.persist(contratAbonnement);
        em.getTransaction().commit();
        em.refresh(contratAbonnement);
    }
}
