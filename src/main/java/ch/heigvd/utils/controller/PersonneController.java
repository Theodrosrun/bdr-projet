package ch.heigvd.utils.controller;

import ch.heigvd.utils.entity.Personne;
import jakarta.persistence.EntityManager;

/***
 * Cette classe gère les opérations de persistance pour l'entité Personne.
 * Elle est responsable de l'ajout de nouvelles personnes dans la base de données
 * et de la mise à jour des personnes existantes.
 */
public class PersonneController {
    static final EntityManager em = AppContextListener.createEntityManager();
    public static void create(Personne personne) {
        em.getTransaction().begin();
        em.persist(personne);
        em.getTransaction().commit();
        em.refresh(personne);
    }
}
