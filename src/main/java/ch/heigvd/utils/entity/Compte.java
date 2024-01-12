package ch.heigvd.utils.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "Compte")
public class Compte {

    @Id
    private String username;

    @Column(name = "mot_de_passe", nullable = false)
    private String motDePasse;

    @Column(name = "moyen_paiement_pref_id")
    private Integer moyenPaiementPrefId;

    @Column(name = "date_de_creation", nullable = false)
    private Date dateDeCreation;

    public Compte() {
    }
}
