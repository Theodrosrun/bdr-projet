package ch.heigvd.utils.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Table(name = "Progression")
public class Progression {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "progression_id", nullable = false)
    private int progressionId;

    @Column(name = "date", nullable = false)
    private Date date;

    @Column(precision = 4)
    private BigDecimal poids;

    @Column(name = "membre_id", nullable = false)
    private int membreId;

    public Progression() {
    }

}

