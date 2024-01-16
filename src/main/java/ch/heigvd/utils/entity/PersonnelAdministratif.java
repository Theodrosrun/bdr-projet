package ch.heigvd.utils.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "PersonnelAdministratif")
public class PersonnelAdministratif {

    @Id
    @Column(name = "padministratif_id", nullable = false)
    private int padministratifId;
}