package ch.heigvd.utils.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Administrateur")
public class Administrateur {

    @Id
    @Column(name = "administrateur_id", nullable = false)
    private int administrateurId;
}
