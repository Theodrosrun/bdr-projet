package ch.heigvd.utils.entity;

import jakarta.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "Contrat")
public class Contrat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int contratId;

    @Column(name = "membre_id", nullable = false)
    private int membreId;

    @Column(name = "date_debut", nullable = false)
    private Date dateDebut;

    @Column(name = "duree", nullable = false)
    private int duree;

    @Column(name = "frequence_paiement", nullable = false)
    private int frequencePaiement;

    public Contrat() {
    }

}

