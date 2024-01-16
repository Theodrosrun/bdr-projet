package ch.heigvd.utils.entity;

import jakarta.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "Payement")
public class Payement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payement_id", nullable = false)
    private int payementId;

    @Column(name = "facture_id", nullable = false)
    private int factureId;

    @Column(name = "contrat_id", nullable = false)
    private int contratId;

    @Column(name = "date_payement", nullable = false)
    private Date datePayement;

    @Column(name = "moyen_paiement_id", nullable = false)
    private int moyenPaiementId;

    public Payement() {
    }

}
