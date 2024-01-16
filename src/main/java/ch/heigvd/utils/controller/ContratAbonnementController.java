package ch.heigvd.utils.controller;

import ch.heigvd.utils.entity.ContratAbonnement;
import jakarta.persistence.EntityManager;

public class ContratAbonnementController {
    static final EntityManager em = AppContextListener.createEntityManager();

    public static void create(ContratAbonnement contratAbonnement) {
        em.getTransaction().begin();
        em.persist(contratAbonnement);
        em.getTransaction().commit();
        em.refresh(contratAbonnement);
    }
}
