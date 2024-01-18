package ch.heigvd.utils.controller;

import ch.heigvd.utils.entity.Compte;
import ch.heigvd.utils.entity.Membre;
import ch.heigvd.utils.entity.MoyenPaiement;
import ch.heigvd.utils.view.AccountView;
import ch.heigvd.utils.view.MemberCourseWeekView;
import ch.heigvd.utils.view.MembreAbonnementView;
import ch.heigvd.utils.view.MembreFactureView;
import jakarta.persistence.EntityManager;
import java.util.List;

/***
 * Cette classe gère les opérations de persistance pour l'entité Membre.
 * Elle est responsable de l'ajout de nouveaux membres dans la base de données
 * et de la mise à jour des membres existants.
 */
public class MembreController {
    static final EntityManager em = AppContextListener.createEntityManager();

    public static void create(Membre membre) {
        em.getTransaction().begin();
        em.persist(membre);
        em.getTransaction().commit();
        em.refresh(membre);
    }

    public static List<MembreAbonnementView> getAbonnementsView(int m_id) {
        @SuppressWarnings("unchecked")
        List<MembreAbonnementView> objects = em.createNativeQuery(
                "SELECT mv from my_amazing_fitness.MembreAbonnementView mv WHERE membre_id = '%d'".formatted(m_id),
                        MembreAbonnementView.class)
                .getResultList();
        return objects;
    }

    public static AccountView getAccountView(int m_id) {
        @SuppressWarnings("unchecked")
        List<AccountView> objects = em.createNativeQuery(
                "SELECT av from my_amazing_fitness.AccountView av WHERE id = '%d'".formatted(m_id),
                        AccountView.class)
                .getResultList();
        return objects.get(0);
    }

    public static MoyenPaiement getMoyenPayment(int p_method_id) {
        return em.find(MoyenPaiement.class, p_method_id);
    }

    public static Membre getMembre(int m_id) {
        return em.find(Membre.class, m_id);
    }

    public static Compte getCompte(String username) {
        return em.find(Compte.class, username);
    }

    public static Membre getMembre(String username) {
        return em.createQuery("SELECT m FROM Membre m WHERE m.compteId = :username", Membre.class)
                .setParameter("username", username)
                .getSingleResult();
    }

    public static List getMembreFactureView(int m_id) {
        return  em.createNativeQuery(
                "SELECT mv from my_amazing_fitness.MembreFactureView mv WHERE membre_id = '%d'".formatted(m_id),
                        MembreFactureView.class)
                .getResultList();
    }

    public static List getMemberCourseWeekView(int m_id) {
        return  em.createNativeQuery(
                "SELECT mv from my_amazing_fitness.MemberCourseWeekView mv WHERE membre_id = '%d'".formatted(m_id),
                        MemberCourseWeekView.class)
                .getResultList();
    }

    public static void setMoyenPaiement(Compte compte, MoyenPaiement moyenPaiement) {
        em.getTransaction().begin();
        compte.setMoyenPaiementPrefId(moyenPaiement.getMoyenPaiementId());
        em.getTransaction().commit();
    }


    public static boolean goodPassword(int m_id, String password) {
        return true;
    }
}
