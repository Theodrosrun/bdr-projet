package ch.heigvd.utils.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Membre")
public class Membre {

    @Id
    private int id;

    @Column(name = "compte_id", unique = true)
    private String compteId;

    public Membre() {
    }

}
