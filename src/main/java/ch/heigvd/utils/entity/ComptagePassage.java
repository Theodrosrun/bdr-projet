package ch.heigvd.utils.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "ComptagePassage")
public class ComptagePassage {

    @Id
    @Column(name = "fitness_id", nullable = false)
    private int fitnessId;

    @Id
    @Column(name = "jour", nullable = false)
    private int jour;

    @Id
    @Column(name = "heure", nullable = false)
    private int heure;

    @Column(name = "nombre_personnes")
    private int nombrePersonnes;


    public ComptagePassage() {
    }

}
