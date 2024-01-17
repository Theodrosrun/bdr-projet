package ch.heigvd.utils.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

/**
 * Cette classe Java, nommée Abonnement, représente une entité liée à la gestion des abonnements
 * dans le contexte d'une application Java utilisant la Java Persistence API (JPA). Cette entité
 * est annotée avec @Entity, indiquant qu'elle peut être stockée dans une base de données, et elle
 * est associée à une table nommée "Abonnement". Les attributs de l'entité sont mappés sur des colonnes
 * de la table, avec aboId comme clé primaire.
 */
@Entity
@Table(name = "Abonnement")
public class Abonnement {

    @Id
    @Column(name = "abo_id", nullable = false)
    private String aboId;

    @Column(name = "prix", nullable = false)
    private BigDecimal prix;

    @Column(name = "type_abonnement", nullable = false)
    private String typeAbonnement;

    @Column(name = "disponibilite")
    private Boolean disponibilite;




    public Abonnement() {
        // Initialiser la disponibilité par défaut à true
        this.disponibilite = true;
    }

}
