package ch.heigvd.utils.controller;

import ch.heigvd.utils.entity.Contrat;
import ch.heigvd.utils.entity.Membre;
import jakarta.persistence.EntityManager;

public class ContratController {
    static final EntityManager em = AppContextListener.createEntityManager();

    public static void create(Contrat contrat) {
        em.getTransaction().begin();
        em.persist(contrat);
        em.getTransaction().commit();
        em.refresh(contrat);
    }
}
