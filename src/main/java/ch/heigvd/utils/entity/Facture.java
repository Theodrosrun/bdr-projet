package ch.heigvd.utils.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Table(name = "Facture")
public class Facture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int factureId;

    @Id
    @Column(name = "contrat_id", nullable = false)
    private int contratId;

    @Column(name = "montant", nullable = false, precision = 8, scale = 2)
    private BigDecimal montant;

    @Column(name = "date_echeance", nullable = false)
    private Date dateEcheance;

    @Column(name = "payment_id")
    private Integer paymentId;

    public Facture() {
    }

}
