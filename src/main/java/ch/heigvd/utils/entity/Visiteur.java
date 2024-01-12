package ch.heigvd.utils.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Visiteur")
public class Visiteur {

    @Id
    private int visiteurId;

    @Column(name = "fitness_id", nullable = false)
    private int fitnessId;

    @Column(name = "visite_effectuee")
    private boolean visiteEffectuee;

}
