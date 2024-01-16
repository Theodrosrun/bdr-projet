package ch.heigvd.utils.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

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
