package ch.heigvd.utils.controller;

import ch.heigvd.utils.entity.Personne;
import jakarta.persistence.EntityManager;

public class PersonneController {
    static final EntityManager em = AppContextListener.createEntityManager();
    public static void create(Personne personne) {
        em.getTransaction().begin();
        em.persist(personne);
        em.getTransaction().commit();
        em.refresh(personne);
    }
}
