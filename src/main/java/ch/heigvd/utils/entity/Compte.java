package ch.heigvd.utils.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

import java.util.Date;

@Entity
@Table(name = "Compte")
@Getter
public class Compte {

    @Id
    @Column(name = "username", length = 255)
    private String username;
    @Column(name = "mot_de_passe", nullable = false)
    private String motDePasse;
    @Column(name = "moyen_paiement_pref_id")
    private Integer moyenPaiementPrefId;
    @Column(name = "date_de_creation", nullable = false, columnDefinition = "DATE")
    private Date dateDeCreation;

    public Compte() {
    }
}
