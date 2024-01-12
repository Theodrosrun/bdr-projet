package ch.heigvd.utils.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "TypeAbonnement")
public class TypeAbonnement {

    @Id
    private String nom;


    public TypeAbonnement() {

    }

}

