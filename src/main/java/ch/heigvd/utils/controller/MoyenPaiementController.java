package ch.heigvd.utils.controller;
import ch.heigvd.utils.entity.MoyenPaiement;
import jakarta.persistence.EntityManager;

public class MoyenPaiementController {
    static final EntityManager em = AppContextListener.createEntityManager();

    public static void create(MoyenPaiement moyenPaiement) {
        em.getTransaction().begin();
        em.persist(moyenPaiement);
        em.getTransaction().commit();
        em.refresh(moyenPaiement);
    }

}
