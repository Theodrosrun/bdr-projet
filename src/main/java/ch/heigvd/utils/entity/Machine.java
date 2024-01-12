package ch.heigvd.utils.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Machine")
public class Machine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int machineId;

    @Column(name = "fitness_id", nullable = false)
    private int fitnessId;

    @Column(name = "salle_id", length = 255)
    private String salleId;

    @Column(name = "etat", length = 255)
    private String etat;

    @Column(name = "type_machine", nullable = false, length = 255)
    private String typeMachine;


    public Machine() {
    }

}
