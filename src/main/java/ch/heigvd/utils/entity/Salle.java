package ch.heigvd.utils.entity;
import jakarta.persistence.*;
@Entity
@Table(name = "Salle")
public class Salle {

    @Id
    @Column(name = "salle_id", nullable = false)
    private String salleId;

    @Id
    @Column(name = "fitness_id", nullable = false)
    private int fitnessId;

    @Column(name = "capacite_max", nullable = false)
    private int capaciteMax;

    @Column(name = "surface", length = 255)
    private String surface;


    public Salle() {
    }

}