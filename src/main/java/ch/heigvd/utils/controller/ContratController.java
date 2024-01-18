package ch.heigvd.utils.controller;

import ch.heigvd.utils.entity.Contrat;
import ch.heigvd.utils.entity.Membre;
import jakarta.persistence.EntityManager;

/***
 * Cette classe gère les opérations de persistance pour l'entité Contrat.
 * Elle est responsable de l'ajout de nouveaux contrats dans la base de données
 * et de la mise à jour des contrats existants.
 */
public class ContratController {
    static final EntityManager em = AppContextListener.createEntityManager();

    public static void create(Contrat contrat) {
        em.getTransaction().begin();
        em.persist(contrat);
        em.getTransaction().commit();
        em.refresh(contrat);
    }
}
