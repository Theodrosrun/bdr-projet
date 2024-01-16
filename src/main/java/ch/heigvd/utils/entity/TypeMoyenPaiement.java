package ch.heigvd.utils.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "TypeMoyenPaiement")
public class TypeMoyenPaiement {

    @Id
    @Column(name = "nom", nullable = false)
    private String nom;

    public TypeMoyenPaiement() {
    }

}
