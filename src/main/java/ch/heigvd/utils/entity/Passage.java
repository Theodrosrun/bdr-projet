package ch.heigvd.utils.entity;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "Passage")
public class Passage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int passageId;

    @Column(name = "membre_id", nullable = false)
    private int membreId;

    @Column(name = "fitness_id", nullable = false)
    private int fitnessId;

    @Column(name = "timestamp", nullable = false)
    private Timestamp timestamp;


    public Passage() {
    }

}
