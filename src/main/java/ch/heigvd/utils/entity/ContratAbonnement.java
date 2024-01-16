package ch.heigvd.utils.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "ContratAbonnement")
public class ContratAbonnement {

    @Id
    @Column(name = "contrat_id", nullable = false)
    private int contratId;

    @Id
    @Column(name = "abo_id", nullable = false, length = 255)
    private String aboId;

    public ContratAbonnement() {
    }

    public ContratAbonnement(int contratId, String aboId) {
        this.contratId = contratId;
        this.aboId = aboId;
    }
}