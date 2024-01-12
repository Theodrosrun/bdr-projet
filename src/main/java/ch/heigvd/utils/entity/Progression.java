package ch.heigvd.utils.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Table(name = "Progression")
public class Progression {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int progressionId;

    private Date date;

    @Column(precision = 4, scale = 1)
    private BigDecimal poids;

    @Column(name = "membre_id", nullable = false)
    private int membreId;

    public Progression() {
    }

}

