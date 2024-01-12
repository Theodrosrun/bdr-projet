package ch.heigvd.utils.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "MyAmazingFitness")
public class MyAmazingFitness {

    @Id
    @Column(name = "fitness_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int fitnessId;
    @Column(name = "numero", nullable = false)
    private int numero;

    @Column(name = "rue", nullable = false)
    private String rue;

    @Column(name = "ville", nullable = false)
    private String ville;

    @Column(name = "NPA", nullable = false)
    private int npa;

    @Column(name = "creation_date", nullable = false)
    private Date creationDate;

    public MyAmazingFitness() {
    }

}
