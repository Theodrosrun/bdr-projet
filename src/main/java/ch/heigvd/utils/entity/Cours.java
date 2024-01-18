package ch.heigvd.utils.entity;

import jakarta.persistence.*;
import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name = "Cours")
public class Cours {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cours_id", nullable = false)
    private int coursId;

    @Column(name = "jour", nullable = false, columnDefinition = "DATE")
    private Date jour;

    @Column(name = "heure", nullable = false)
    private Time heure;

    @Column(name = "duree", nullable = false)
    private int duree;

    @Column(name = "description", length = 255)
    private String description;

    @Column(name = "typeCours", nullable = false, length = 255)
    private String typeCours;

    @Column(name = "fitness_id", nullable = false)
    private int fitnessId;

    @Column(name = "salle_id", nullable = false, length = 255)
    private String salleId;

    @Column(name = "abo_id", nullable = false, length = 255)
    private String aboId;

    public Cours() {
    }

}

