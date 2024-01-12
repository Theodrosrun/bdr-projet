package ch.heigvd.utils.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Administrateur")
public class Administrateur {

    @Id
    private int administrateurId;
}
