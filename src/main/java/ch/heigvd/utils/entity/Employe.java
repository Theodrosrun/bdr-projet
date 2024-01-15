package ch.heigvd.utils.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "Employe")
public class Employe {

    @Id
    private int id;

    @Column(name = "fitness_id", nullable = false)
    private int fitnessId;

    @Column(name = "compte_id", unique = true)
    private String compteId;

    @Column(name = "salaire", nullable = false, precision = 8, scale = 2)
    private float salaire;

}
