package ch.heigvd.utils.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "MoyenPaiement")
@Getter
public class MoyenPaiement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "moyen_paiement_id", nullable = false)
    private int moyenPaiementId;

    @Column(name = "type_moyen_paiement", nullable = false)
    private String typeMoyenPaiement;

    @Column(name = "compte_id", nullable = false)
    private String compteId;

    @Column(name = "info", nullable = false)
    private String info;


    public MoyenPaiement() {
    }

}

