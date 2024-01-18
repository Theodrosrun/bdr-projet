package ch.heigvd.utils.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "TypeCours")
public class TypeCours {

    @Id
    @Column(name = "nom", nullable = false)
    private String nom;

    @Column(name = "instructeur_id", nullable = false)
    private int instructeurId;

    public TypeCours() {
    }

}
