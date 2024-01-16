package ch.heigvd.utils.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "Membre")
public class Membre {

    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "compte_id", unique = true)
    private String compteId;

    public Membre() {
    }

    public Membre(@Positive int id) {
        this.id = id;
    }

}
