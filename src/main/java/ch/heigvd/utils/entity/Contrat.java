package ch.heigvd.utils.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Positive;
import lombok.Getter;

import java.util.Date;

@Entity
@Table(name = "Contrat")
@Getter
public class Contrat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contrat_id", nullable = false)
    private int contratId;

    @Column(name = "membre_id", nullable = false)
    private int membreId;

    @Column(name = "date_debut", nullable = false, columnDefinition = "DATE")
    private Date dateDebut;

    @Column(name = "duree", nullable = false)
    private int duree;

    @Column(name = "frequence_paiement", nullable = false)
    private int frequencePaiement;

    public Contrat() {
    }

    public Contrat(@Positive int membreId, @FutureOrPresent Date dateDebut,
                   @Positive int duree, @Positive int frequencePaiement) {
        this.membreId = membreId;
        this.dateDebut = dateDebut;
        this.duree = duree;
        this.frequencePaiement = frequencePaiement;
    }

}

