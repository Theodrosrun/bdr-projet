package ch.heigvd.utils.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Abonnement")
public class Abonnement {

    @Id
    private String aboId;

    @Column(name = "prix", nullable = false, precision = 6, scale = 2)
    private float prix;

    @Column(name = "type_abonnement", nullable = false, length = 255)
    private String typeAbonnement;

    @Column(name = "disponibilite")
    private Boolean disponibilite;


    public Abonnement() {
        // Initialiser la disponibilité par défaut à true
        this.disponibilite = true;
    }

}
