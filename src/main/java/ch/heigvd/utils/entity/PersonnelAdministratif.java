package ch.heigvd.utils.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "PersonnelAdministratif")
public class PersonnelAdministratif {

    @Id
    private int padministratifId;
}